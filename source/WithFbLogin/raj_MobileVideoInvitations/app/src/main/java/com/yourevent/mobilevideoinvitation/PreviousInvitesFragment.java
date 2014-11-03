package com.yourevent.mobilevideoinvitation;

import android.app.Activity;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imjalpreet on 02-10-2014.
 */

public class PreviousInvitesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_previous_invitation, container, false);

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Videos");
        query.whereEqualTo("created_by", currentUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {

                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

         String User=currentUser.getObjectId();

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

//ONly one video is shown at present
        Log.d("User:",User);
        String n=Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/";
        Log.d("n:",n);
        RelativeLayout rl=(RelativeLayout)rootView.findViewById(R.id.layout);

        File a=new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/");
        for (File f : a.listFiles()) {
               String h=n + f.getName();
            Log.d("score",h);
            VideoView val = new VideoView(rootView.getContext());
            val.setVideoPath(h);
            val.seekTo(100);


            ((RelativeLayout) rl).addView(val);
               break;
            // do whatever you want with filename
        }











        return rootView;
    }


}
