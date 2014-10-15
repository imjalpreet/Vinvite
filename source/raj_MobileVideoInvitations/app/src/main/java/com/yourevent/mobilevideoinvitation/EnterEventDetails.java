package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 13/10/14.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EnterEventDetails extends Activity implements View.OnClickListener{
    String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    Button contButton;
    public String event;
    public final static String EXTRA = "";
    EditText name;
    EditText description;
    EditText venue;
    EditText date;
    EditText time;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageButton btnTime, btnDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Event Details");
        Intent intent = getIntent();
        String message = intent.getStringExtra(SelectEventFragment.EXTRA_MESSAGE);
        event = intent.getStringExtra(SelectEventFragment.EXTRA_MESSAGE);
        TextView t = (TextView)findViewById(R.id.eventName);
        t.setText(events[Integer.parseInt(message)]);
        contButton = (Button)findViewById(R.id.continueEventDetails);
        name = (EditText) findViewById(R.id.etName);
        description = (EditText) findViewById(R.id.etDescription);
        venue = (EditText) findViewById(R.id.etVenue);
        date = (EditText) findViewById(R.id.etDate);
        time = (EditText) findViewById(R.id.etTime);
        btnTime = (ImageButton) findViewById(R.id.ibTime);
        btnDate = (ImageButton) findViewById(R.id.ibDate);
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent("android.intent.action.STAGGEREDGRIDACTIVITY");
            if (name.getText().toString().trim().length() == 0) {
                Toast.makeText(EnterEventDetails.this, "Name Field cannot be Empty", Toast.LENGTH_SHORT).show();
                name.setHintTextColor(getResources().getColor(R.color.red));
            }
            else if (venue.getText().toString().trim().length() == 0) {
                venue.setHintTextColor(getResources().getColor(R.color.red));
                Toast.makeText(EnterEventDetails.this, "Venue Field cannot be Empty", Toast.LENGTH_SHORT).show();
            }
            else if (date.getText().toString().trim().length() == 0) {
                date.setHintTextColor(getResources().getColor(R.color.red));
                Toast.makeText(EnterEventDetails.this, "Date Field cannot be Empty", Toast.LENGTH_SHORT).show();
            }
            else if (time.getText().toString().trim().length() == 0) {
                name.setTextColor(getResources().getColor(R.color.red));
                Toast.makeText(EnterEventDetails.this, "Time Field cannot be Empty", Toast.LENGTH_SHORT).show();
            }
            else {
                i.putExtra(EXTRA, events[Integer.parseInt(event)] + " " + name.getText() + " " + description.getText() + " " + venue.getText() + " " + date.getText() + " " + time.getText());
                startActivity(i);
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnTime){
            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
        else if (v == btnDate){
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            date.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
    }
}