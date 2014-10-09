package com.yourevent.mobilevideoinvitation.adapter;

/**
 * Created by imjalpreet on 02-10-2014.
 */

import com.yourevent.mobilevideoinvitation.SelectEventFragment;
import com.yourevent.mobilevideoinvitation.PreviousInvitesFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index){
            case 0:
                //Select Event Fragment
                //return new SelectEventFragment();
            case 1:
                //Previous Events Fragment
                return new PreviousInvitesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
