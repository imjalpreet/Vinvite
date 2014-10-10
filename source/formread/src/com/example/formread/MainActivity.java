package com.example.formread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by raj on 9/10/14.
 */
public class MainActivity extends Activity {
    String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("                   Event Details");
  //      Intent intent = getIntent();
    //    String message = intent.getStringExtra(StaggeredGridActivityFragment.EXTRA_MESSAGE);
        TextView t = (TextView)findViewById(R.id.eventName);
      //  t.setText(events[Integer.parseInt(message)]);
    }

}
