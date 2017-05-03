package edu.northampton.smith.safae.finalproject;

import android.app.Activity;
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
        Intent getIntent = getIntent();
        currentTripId = getIntent.getLongExtra("id", -1);

        Button addDiaryBtn = (Button) findViewById(R.id.addDiaryBtn);
        final EditText diaryEntryTxt = (EditText) findViewById(R.id.journalEntry);

        addDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                diary_entry = diaryEntryTxt.getText().toString();

                currentTrip = ds.getTrip(currentTripId);
                System.out.println("diary entry text: " + diary_entry);
                System.out.println("old text: " + currentTrip.getDiary_entry());
                ds.updateTrip("diary_entry", currentTrip.getDiary_entry() + "\n" + diary_entry, currentTripId);
                String lulu = currentTrip.getDiary_entry();
                
                System.out.println("/////////////////////////////////////////////////////////");
                System.out.println("This is the table: " +ds.getTableAsString(ds.getDatabase(), "trips"));
                System.out.println("/////////////////////////////////////////////////////////");



                Intent backIntent = new Intent(DiaryEntryActivity.this, TripDetailsActivity.class);
                backIntent.putExtra("id", currentTripId);
                startActivity(backIntent);
                //finishActivity(Activity.RESULT_OK);

            }
        });
    }
}
