package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 13/10/14.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class EnterEventDetails extends Activity implements View.OnClickListener{
    private String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    public static String event;
    public static String message;
    public final static String EXTRA = "";
    private EditText name;
    private EditText venue;
    private EditText date;
    private EditText time;
    private ImageButton btnTime;
    private ImageButton btnDate;
    private ImageView Event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        //actionBar.setBackgroundDrawable(new ColorDrawable(R.color.orange));
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Event Details");
        Intent intent = getIntent();
        assert intent.getExtras()!=null;
        if(intent.getExtras()!=null) {
            message = intent.getStringExtra(SelectEventFragment.EXTRA_MESSAGE);
            event = intent.getStringExtra(SelectEventFragment.EXTRA_MESSAGE);
        }
        //TextView t = (TextView)findViewById(R.id.eventName);
        //t.setText(events[Integer.parseInt(message)]);
        Event = (ImageView) findViewById(R.id.whatsApp);
        if(events[Integer.parseInt(message)].equalsIgnoreCase("birthday")){
            Event.setImageResource(R.drawable.screen3_birthday);
        }
        else if(events[Integer.parseInt(message)].equalsIgnoreCase("anniversary")){
            Event.setImageResource(R.drawable.screen3_anniversary);
        }
        else if(events[Integer.parseInt(message)].equalsIgnoreCase("reception")){
            Event.setImageResource(R.drawable.screen3_reception);
        }
        else if(events[Integer.parseInt(message)].equalsIgnoreCase("wedding")){
            Event.setImageResource(R.drawable.screen3_wedding);
        }
        else if(events[Integer.parseInt(message)].equalsIgnoreCase("house warming")){
            Event.setImageResource(R.drawable.screen3_housewarming);
        }
        else if(events[Integer.parseInt(message)].equalsIgnoreCase("engagement")){
            Event.setImageResource(R.drawable.screen3_engagement);
        }
        Button contButton = (Button) findViewById(R.id.continueEventDetails);
        name = (EditText) findViewById(R.id.etName);
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
                    name.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(EnterEventDetails.this, "Name Field cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (venue.getText().toString().trim().length() == 0) {
                    venue.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(EnterEventDetails.this, "Venue Field cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (date.getText().toString().trim().length() == 0) {
                    date.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(EnterEventDetails.this, "Date Field cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (date.getText().toString().trim().length() != 10) {
                    Toast.makeText(EnterEventDetails.this, "Invalid Date", Toast.LENGTH_SHORT).show();
                } else if (time.getText().toString().trim().length() == 0) {
                    time.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(EnterEventDetails.this, "Time Field cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (time.getText().toString().trim().length() != 5) {
                    Toast.makeText(EnterEventDetails.this, "Invalid Time", Toast.LENGTH_SHORT).show();
                } else {
                    i.putExtra(EXTRA, events[Integer.parseInt(event)] + "_" + name.getText() + "_" + venue.getText() + "_" + date.getText() + "_" + time.getText());
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
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            time.setText(getAppropriateString(hourOfDay) + ":" + getAppropriateString(minute));
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
        else if (v == btnDate){
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            date.setText(getAppropriateString(dayOfMonth) + "-"
                                    + getAppropriateString(monthOfYear + 1) + "-" + getAppropriateString(year));
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
    }
    // Converts a single digit integer to a double digit integer by appending 0 in front
    // and then converting this number to a string
    public String getAppropriateString(int input){
        String tmp = "0";
        String str = Integer.toString(input);
        if (str.length() == 1)
            tmp += str;
        else
            tmp = str;
        return tmp;
    }
}