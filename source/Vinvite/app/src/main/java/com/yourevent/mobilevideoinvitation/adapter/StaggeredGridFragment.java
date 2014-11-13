package com.yourevent.mobilevideoinvitation.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import com.etsy.android.grid.StaggeredGridView;
import com.yourevent.mobilevideoinvitation.R;
import com.yourevent.mobilevideoinvitation.SampleAdapter;
import com.yourevent.mobilevideoinvitation.SampleData;
import java.util.ArrayList;

/**
 * Created by raj on 9/10/14.
 */
class StaggeredGridFragment extends Fragment implements
         AbsListView.OnItemClickListener {
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
        StaggeredGridView mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
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
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}

