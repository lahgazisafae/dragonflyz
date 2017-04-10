package edu.northampton.smith.safae.finalproject;


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

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Amyrah on 4/4/17.
 */

public class Depart extends Fragment implements View.OnClickListener {


    private Button submit;
    private String location;
    private String date;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment

        View v = inflater.inflate(R.layout.fragment_depart,container, false);
        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment returnFragment = new Return();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, returnFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        EditText place = (EditText) v.findViewById(R.id.place);
        location = place.getText().toString();
        DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);

        Date d = new Date(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
        SimpleDateFormat dform = new SimpleDateFormat("MM-dd-yyyy");
        date = dform.format(d);
        return v;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }
}
