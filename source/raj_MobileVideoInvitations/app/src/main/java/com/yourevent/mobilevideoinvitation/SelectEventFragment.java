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

public class SelectEventFragment extends Fragment implements
            AbsListView.OnItemClickListener {
        private StaggeredGridView mGridView;
        public final static String EXTRA_MESSAGE = "";
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

