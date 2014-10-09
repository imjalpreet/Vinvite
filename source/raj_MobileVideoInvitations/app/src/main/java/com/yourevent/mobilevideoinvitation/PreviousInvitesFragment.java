package com.yourevent.mobilevideoinvitation;

import android.support.v4.app.Fragment;
import com.yourevent.mobilevideoinvitation.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by imjalpreet on 02-10-2014.
 */
public class PreviousInvitesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_previous_invitation, container, false);

        return rootView;
    }
}
