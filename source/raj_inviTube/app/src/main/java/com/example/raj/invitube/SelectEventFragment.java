package com.example.raj.invitube;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.ViewGroup;

/**
 * Created by raj on 3/10/14.
 */
public class SelectEventFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View tmpview = inflater.inflate(R.layout.selectevent_layout, container, false);
        return tmpview;
    }
}
