package com.example.raj.invitube;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class MyActivity extends FragmentActivity implements ActionBar.TabListener {
    ActionBar actionbar;
    ViewPager viewpager;
    FragmentPageAdapter ft;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        viewpager = (ViewPager)findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        actionbar = getActionBar();
        viewpager.setAdapter(ft);

        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab t1= actionbar.newTab();
        Tab t2= actionbar.newTab();

        actionbar.addTab(t1.setText("Select Event").setTabListener(this));
        actionbar.addTab(t2.setText("Previous Invites").setTabListener(this));

        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ea460f")));
        actionbar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ea460f")));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
//                actionbar.setSelectedNavigationItem(i);

            }

            @Override
            public void onPageSelected(int i) {
                actionbar.setSelectedNavigationItem(i);
                if(i==1){

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
    */    return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewpager.setCurrentItem(tab.getPosition());
        if(tab.getPosition() == 1) {
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    //    viewpager.setCurrentItem(tab.getPosition());
    }
}
