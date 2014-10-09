package com.example.raj.invitube;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

/**
 * Created by raj on 3/10/14.
 */
public class PreviousInvitesFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tmpview = inflater.inflate(R.layout.previous_invites_layout, container, false);

        return tmpview;
    }
}
