package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 09-10-2014.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
public class StaggeredGridActivity extends Activity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "StaggeredGridActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private SampleAdapter mAdapter;
    private ArrayList<String> mData;
    public final static String EXTRA_MESSAGE = "com.yourevent.mobilevideoinvitation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgv);
        setTitle("          Select Your Script");
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        LayoutInflater layoutInflater = getLayoutInflater();
        //View header = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        //View footer = layoutInflater.inflate(R.layout.list_item_header_footer, null);
        //TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
        //TextView txtFooterTitle = (TextView) footer.findViewById(R.id.txt_title);
        //txtHeaderTitle.setText("Select Your Script");
        //txtFooterTitle.setText("THE FOOTER!");
        //mGridView.addHeaderView(header);
        //mGridView.addFooterView(footer);
        mAdapter = new SampleAdapter(this, R.id.txt_line1);
// do we have saved data?
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }
        if (mData == null) {
            mData = SampleData.generateSampleData();
        }
        for (String data : mData) {
            mAdapter.add(data);
        }
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_sgv_dynamic, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.col1:
                mGridView.setColumnCount(1);
                break;
            case R.id.col2:
                mGridView.setColumnCount(2);
                break;
            case R.id.col3:
                mGridView.setColumnCount(3);
                break;
        }*/
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
// our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                mHasRequestedMore = true;
                //onLoadMoreItems();
            }
        }
    }
    private void onLoadMoreItems() {
        final ArrayList<String> sampleData = SampleData.generateSampleData();
        for (String data : sampleData) {
            mAdapter.add(data);
        }
// stash all the data in our backing store
        mData.addAll(sampleData);
// notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, BackgroundScore.class);
        final ArrayList<String> sampleData = SampleData.generateSampleData();
        mData.addAll(sampleData);
        i.putExtra(EXTRA_MESSAGE, mData.get(position));
        startActivity(i);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(this, "Item Long Clicked: " + position, Toast.LENGTH_SHORT).show();
        return true;
    }
}