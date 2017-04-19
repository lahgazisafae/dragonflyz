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


    Trip currentTrip = null;

    private Calendar c;
    private Button submit;
    private String date;
    private EditText place;
    DatePicker datePicker;
    private Button submit;
 

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment

        View v = inflater.inflate(R.layout.fragment_depart,container, false);
        place = (EditText) v.findViewById(R.id.place);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        c = Calendar.getInstance();
        c.get(Calendar.DATE);
        datePicker.setMinDate(c.get(Calendar.DATE)+ c.get(Calendar.MONTH)+c.get(Calendar.YEAR));

        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String location = place.getText().toString();

                if (location.matches("")){
                    place.setError("Invalid Location!");
                }

                Date d = new Date(datePicker.getYear(), datePicker.getMonth() , datePicker.getDayOfMonth());
                SimpleDateFormat dform = new SimpleDateFormat("MM-dd-yyyy");
                date = dform.format(d);

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
        });

        return v;
    }
    public boolean isValidLocation(String location){

        if(location!= null && !location.matches("")) {
            System.out.println("and now location" + location);
            return true;
        }
        else
            return false;
    }
    public boolean isValidDate(){
        return true;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }
}
