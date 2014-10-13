package com.yourevent.mobilevideoinvitation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import com.etsy.android.grid.StaggeredGridView;
import java.util.ArrayList;

public class SelectEventFragment extends FragmentActivity {
    public static final String TAG = "StaggeredGridActivityFragment";
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fm = getSupportFragmentManager();
// Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            final StaggeredGridFragment fragment = new StaggeredGridFragment();
            fm.beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }
    private class StaggeredGridFragment extends Fragment implements
            AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
        private StaggeredGridView mGridView;
        private SampleAdapter mAdapter;
        private ArrayList<String> mData;
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_sgv, container, false);
        }
        @Override
        public void onActivityCreated(final Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
            if (mAdapter == null) {
                mAdapter = new SampleAdapter(getActivity(), R.id.txt_line1);
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
// stash all the data in our backing store
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Intent i;
            i = new Intent("android.intent.action.ENTEREVENTDETAILS");
            i.putExtra(EXTRA_MESSAGE, Integer.toString(position));
            startActivity(i);
        }
    }
}
