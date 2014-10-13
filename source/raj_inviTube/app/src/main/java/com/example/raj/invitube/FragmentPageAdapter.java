package com.example.raj.invitube;

/**
 * Created by raj on 7/10/14.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by raj on 3/10/14.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0)
            return new SelectEventFragment();
        else if(i==1)
            return new PreviousInvitesFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
