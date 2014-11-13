package com.yourevent.mobilevideoinvitation;

/**
 * Created by raj on 12-10-2014.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.widget.AbsListView;
import android.widget.AdapterView;
import com.etsy.android.grid.StaggeredGridView;
import java.util.ArrayList;

public class StaggeredGridActivityFragment extends FragmentActivity {

    public final static String EXTRA_MESSAGE = "";

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
    public class StaggeredGridFragment extends Fragment implements
             AbsListView.OnItemClickListener {
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
            if (savedInstanceState == null) {
            }
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
            mGridView.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Intent i;
            i = new Intent("android.intent.action.ENTEREVENTDETAILS");
            i.putExtra(EXTRA_MESSAGE, Integer.toString(position));
            startActivity(i);
        }
    }
}

