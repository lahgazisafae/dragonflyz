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

/**
 * Created by Amyrah on 4/5/17.
 */

public class Return extends Fragment implements View.OnClickListener {

    DataStorage ds;
    Button submit;

    int day;
    int month;
    int year;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment

        View v = inflater.inflate(R.layout.fragment_return,container, false);
        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment setTimeFragment = new SetTime();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, setTimeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });



        DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();

        return v;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }
}
