package com.yourevent.mobilevideoinvitation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by imjalpreet on 13-10-2014.
 */
public class ShowScript extends Activity {
    TextView ScriptType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_script);
        ScriptType = (TextView) findViewById(R.id.ScriptType);
        Bundle extras = getIntent().getExtras();
        String eventName = extras.getString(StaggeredGridActivity.EVENT_NAME);
        String[] Data = eventName.split(" ");
        setTitle(Data[0]);
        //String scriptType = extras.getString(StaggeredGridActivity.EXTRA_MESSAGE);
        ScriptType.setText(Data[1]);
    }
}
