package com.yourevent.mobilevideoinvitation;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.etsy.android.grid.StaggeredGridView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class PreviousInvitesFragment extends Fragment implements
        AbsListView.OnItemClickListener {
    private StaggeredGridView mGridView;
    public final static String EXTRA_MESSAGE = "";
    private SampleAdapter mAdapter;
    private ArrayList<String> mData;
    int vid_num;
    public String[] events=new String[100];
    public String name;
    public String User;
    ArrayList<String> stringArrayList = new ArrayList<String>();


    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        User=currentUser.getObjectId();
        // File direct2 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved");
        // File a=new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/");
        vid_num= new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/").listFiles().length;
        String path = Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/";
        File f = new File(path);
        File file[] = f.listFiles();
        for(int i=0;i<vid_num;i++)
        {
            // stringArrayList.set(i, file[i].getName());
            events[i]=file[i].getName();
        }


        // vid_num= a.length();



        setRetainInstance(true);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_select_script, container, false);
    }
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
        if (savedInstanceState == null) {
        }
        if (mAdapter == null) {
            mAdapter = new SampleAdapter(getActivity(), R.id.txt_line1);


        }
        if (mData == null) {
            mData = new ArrayList<String>(vid_num);
            Log.d("score", "Retrieved " + "asd" + " scores");
            for (int i = 0; i < vid_num; i++) {
                Log.d("score", "Retrieved " + i + " scores");
                // mData.add(stringArrayList.get(i));
                mData.add(events[i]);
            }

            // mData = SampleData.generateSampleData();
        }
        for (String data : mData) {


            mAdapter.add(data);
        }
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("score", "Position :" + position + " s");
        String name=events[position];
        Intent i;
        i = new Intent("android.intent.action.SHARESCREEN2");
        i.putExtra(EXTRA_MESSAGE, name);
        startActivity(i);
    }
}




/**
 * Created by imjalpreet on 02-10-2014.
 */
/*
public class PreviousInvitesFragment extends Fragment {
    public int vid_num=0;


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
                    vid_num=scoreList.size();
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
        File a=new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/");
        if(a.length() ==0&& vid_num!= 0){
            ParseObject videodownload = new ParseObject("Videos");
            videodownload.get("VideoFile");
        }


//ONly one video is shown at present
        Log.d("User:",User);
        String n=Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/";
        Log.d("n:",n);
        RelativeLayout rl=(RelativeLayout)rootView.findViewById(R.id.layout);

       // File a=new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved/");
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
*/