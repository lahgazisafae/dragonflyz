package edu.northampton.smith.safae.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amyrah on 4/5/17.
 */

public class Return extends Fragment implements View.OnClickListener {


    private Button submit;
    private String date;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment

        View v = inflater.inflate(R.layout.fragment_return,container, false);
        submit = (Button) v.findViewById(R.id.submit);

        Bundle bundle = getArguments();


        final String date_departure = bundle.getString("date_departure");
        final String location = bundle.getString("location");



        DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        Date d = new Date(datePicker.getYear(), datePicker.getMonth() , datePicker.getDayOfMonth());
        SimpleDateFormat dform = new SimpleDateFormat("MM-dd-yyyy");
        date = dform.format(d);
//        ds.updateTrip("date_return", date, currentTripId);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(date!=null) {
                    Fragment setTimeFragment = new SetTime();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("date_return", date);
                    bundle1.putString("date_departure", date_departure);
                    bundle1.putString("location", location);
                    setTimeFragment.setArguments(bundle1);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, setTimeFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getActivity(),"Please enter return date!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        return v;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }
}
