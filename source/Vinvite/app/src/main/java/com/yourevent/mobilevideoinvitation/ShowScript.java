package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.io.File;

/**
 * Created by imjalpreet on 13-10-2014.
 */

public class ShowScript extends Activity {
    TextView ScriptType;
    EditText Script;
    private int i;
    private int tmpi=0;
    String script="";
    private ImageView Event;
    private static String[] Data;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_script);
        //ScriptType = (TextView) findViewById(R.id.ScriptType);
        String eventName=null;
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            eventName = extras.getString(StaggeredGridActivity.EVENT_NAME);
        assert eventName != null;
        Data = eventName.split("_");
        Log.d("Data Length: ", Data[4]);
        setTitle(Data[1]);
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setTitle("Invitation Script");
        //actionBar.hide();
        //ScriptType.setText(Data[0]);
        Event = (ImageView) findViewById(R.id.imageView1);
        if(Data[0].equalsIgnoreCase("formal"))
            Event.setImageResource(R.drawable.screen45_formal);
        else if(Data[0].equalsIgnoreCase("friends"))
            Event.setImageResource(R.drawable.screen45_friends);
        else
            Event.setImageResource(R.drawable.screen45_family);

        Script = (EditText) findViewById(R.id.tvFinalScript);
        final String[] line={"Come and join us for some Birthday fun. ", "It’s "+Data[2].split(" ")[0]+"’s Birthday!", "We will be waiting for you at "+Data[3]+" on "+Data[4]+" and "+Data[5]+". ", "Do Join us for some family time!!"};
        final String[] templine={"","","","","","","","","","","","",""};
        for (String tmpline: line){
            script+=tmpline+" \n\n ";
        }
        Script.setText(script);
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String User=currentUser.getObjectId();

        File direct = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" );

        if(!direct.exists()) {
            if(direct.mkdir()); //directory is created;
        }

        File direct1 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User);

        if(!direct1.exists()) {
            if(direct1.mkdir()); //directory is created;
        }
        File direct2 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved");

        if(!direct2.exists()) {
            if(direct2.mkdir()); //directory is created;
        }
        File direct3 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/UnSaved");

        if(!direct3.exists()) {
            if(direct3.mkdir()); //directory is created;
        }

        File direct4 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/UnSaved");

        if(!direct4.exists()) {
            if(direct4.mkdir()); //directory is created;
        }



        Button contBut = (Button)findViewById(R.id.continueFinalScript);
        contBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.ANDROIDVIDEOCAPTURE");
                script= String.valueOf(Script.getText());
                Log.d("script",script);
                final String[] words=script.split("\\s+");
                Bundle b=new Bundle();
                for(i = 0; i<words.length;i++){
                    if((i+1)%6==0){
                        tmpi++;
                        templine[tmpi]="";
                    }
                    templine[tmpi] += words[i] + " ";
                }

                b.putStringArray("EXTRA_SCRIPT", templine);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

}
