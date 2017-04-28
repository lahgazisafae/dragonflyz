package edu.northampton.smith.safae.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Safae on 4/26/2017.
 */

public class DiaryEntry extends Fragment implements View.OnClickListener{

    DataSource ds;
    String diary_entry;
    long currentTripId;
    Trip currentTrip;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_journal_entry,container, false);
        ds = new DataSource(getActivity());
        ds.open();
//        currentTripId = getIntent.getLongExtra("id", -1);
        Button addDiaryBtn = (Button) v.findViewById(R.id.addDiaryBtn);
        final EditText diaryEntryTxt = (EditText) v.findViewById(R.id.journalEntry);
        diary_entry = diaryEntryTxt.getText().toString();
        addDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentTrip = ds.getTrip(currentTripId);

                ds.updateTrip("diary_entry", currentTrip.getDiary_entry() + " " + diary_entry, currentTripId);

//                System.out.println("/////////////////////////////////////////////////////////");
//                System.out.println("This is the table: " +ds.getTableAsString(ds.getDatabase(), "trips"));
//                System.out.println("/////////////////////////////////////////////////////////");

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
