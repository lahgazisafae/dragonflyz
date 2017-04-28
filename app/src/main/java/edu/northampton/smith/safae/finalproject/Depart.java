package edu.northampton.smith.safae.finalproject;

import java.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Amyrah on 4/4/17.
 */

public class Depart extends Fragment implements View.OnClickListener {

    private Calendar c;

    private String date;
    private EditText place;
    private DatePicker datePicker;
    private Button submit;
    private Date currDate;
 

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_depart,container, false);
        place = (EditText) v.findViewById(R.id.place);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        c = Calendar.getInstance();
        c.get(Calendar.DATE);
        datePicker.setMinDate(c.get(Calendar.DATE)+ c.get(Calendar.MONTH)+c.get(Calendar.YEAR));

        currDate = new Date();

        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String location = place.getText().toString();
                Date d = new Date(datePicker.getYear()-1900, datePicker.getMonth() , datePicker.getDayOfMonth());
                SimpleDateFormat dform = new SimpleDateFormat("MM-dd-yyyy");
                date = dform.format(d);



                if (location.matches("")){
                    place.setError("Invalid Location!");
                } else if (d.getDate()<currDate.getDate()){
                    Toast.makeText(getActivity(),"Invalid date!",Toast.LENGTH_SHORT).show();
                }

                else {
                    Fragment returnFragment = new Return();
                    Bundle bundle = new Bundle();
                    bundle.putString("date_departure", date);
                    bundle.putString("location", location);
                    returnFragment.setArguments(bundle);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, returnFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
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
