package edu.northampton.smith.safae.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DiaryEntryActivity extends AppCompatActivity {

    DataSource ds;
    String diary_entry;
    long currentTripId;
    Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        ds = new DataSource(this);
        ds.open();
//        currentTripId = getIntent.getLongExtra("id", -1);
        Intent getIntent = getIntent();
        currentTripId = getIntent.getLongExtra("id", -1);

        Button addDiaryBtn = (Button) findViewById(R.id.addDiaryBtn);
        final EditText diaryEntryTxt = (EditText) findViewById(R.id.journalEntry);
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
    }
}
