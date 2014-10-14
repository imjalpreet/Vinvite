package com.yourevent.mobilevideoinvitation;

/**
 * Created by raj on 10/10/14.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import com.etsy.android.grid.StaggeredGridView;
public class BackgroundScore extends Activity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
    private static final String TAG = "StaggeredGridActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    private StaggeredGridView mGridView;
    private BackgroundAdapter mAdapter;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_score);
        //Next 3 lines will be used
        //Bundle extras = getIntent().getExtras();
        //String event = extras.getString(StaggeredGridActivity.EXTRA_MESSAGE);
        //setTitle("Background Score - "+event);
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new BackgroundAdapter(this, R.id.txt_line1);
// do we have saved data?
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }
        if (mData == null) {
            mData = BackgroundData.generateSampleData();
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
        //getMenuInflater().inflate(R.menu.menu_sgv_dynamic, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        //if(id == R.id.action_skip) {
            Log.d(TAG, "Skip clicked:" + Integer.toString(id));
        //}
        return super.onOptionsItemSelected(item);
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
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + Integer.toString(position + 1), Toast.LENGTH_SHORT).show();
        Intent openinvite = new Intent("android.intent.action.INVITE");
        startActivity(openinvite);
    }
}