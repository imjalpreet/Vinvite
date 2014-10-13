package com.yourevent.mobilevideoinvitation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import com.etsy.android.grid.StaggeredGridView;
import java.util.ArrayList;

/**
 * Created by imjalpreet on 9/10/14.
 */

public class StaggeredGridActivity extends Activity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
    private static final String TAG = "StaggeredGridActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    private StaggeredGridView mGridView;
    private SelectScriptAdapter mAdapter;
    private ArrayList<String> mData;
    public String eventName;
    public final static String EXTRA_MESSAGE = "";
    public final static String EVENT_NAME = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_script);
        setTitle("          Select Your Script");
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new SelectScriptAdapter(this, R.id.txt_line1);
// do we have saved data?
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }
        if (mData == null) {
            mData = SelectScriptData.generateSampleData();
        }
        for (String data : mData) {
            mAdapter.add(data);
        }
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_DATA_KEY, mData);
    }
    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }
    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent i = new Intent(this, ShowScript.class);
        final ArrayList<String> sampleData = SelectScriptData.generateSampleData();
        mData.addAll(sampleData);
        Intent intent = getIntent();
        eventName = intent.getStringExtra(EnterEventDetails.EXTRA);
        Log.d(TAG, mData.get(position)+" "+eventName);
        Bundle extras = new Bundle();
        //extras.putString(EXTRA_MESSAGE, mData.get(position));
        extras.putString(EVENT_NAME, mData.get(position)+" "+eventName);
        i.putExtras(extras);
        startActivity(i);
    }
}