package com.yourevent.mobilevideoinvitation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/**
 * Created by imjalpreet on 08-11-2014.
 */
public class UserMenu extends Fragment {
    public final static String EXTRA_MESSAGE = "";
    private UserMenuAdapter mAdapter;
    private ArrayList<String> mData;
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_menu, container, false);
    }
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridView mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
        if (savedInstanceState == null) {
        }
        if (mAdapter == null) {
            mAdapter = new UserMenuAdapter(getActivity(), R.id.txt_line1);
        }
        if (mData == null) {
            mData = UserMenuData.generateSampleData();
        }
        for (String data : mData) {
            mAdapter.add(data);
        }
        mGridView.setAdapter(mAdapter);
       //mGridView.setOnItemClickListener(this);
    }

}
