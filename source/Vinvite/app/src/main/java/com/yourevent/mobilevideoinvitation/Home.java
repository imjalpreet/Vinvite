package com.yourevent.mobilevideoinvitation;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.app.ActionBar.Tab;
import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.ParseUser;
import com.yourevent.mobilevideoinvitation.adapter.NavDrawerListAdapter;
import com.yourevent.mobilevideoinvitation.adapter.TabsPagerAdapter;
import com.yourevent.mobilevideoinvitation.model.NavDrawerItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Home extends FragmentActivity implements ActionBar.TabListener {

    private static String DOB, Date, Month, Year;
    private long diff;
    private ViewPager viewPager;
    //private ActionBar actionBar;
    private String[] tabs = {"Select Event", "Previous Invitations"};
    //SlidingMenu menu;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        assert intent!=null;
        //Recieving the Date Of Birth of the User
        if(intent.getExtras()!=null) {
            DOB = intent.getStringExtra("DOB");
            Date = DOB.split("/")[1];
            Month = DOB.split("/")[0];
            Year = DOB.split("/")[2];
            DOB = Year.concat("-").concat(Month).concat("-").concat(Date);

        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        try {
            Date date1 = sdf.parse(currentDateandTime);
            Date date2 = sdf.parse(DOB);
            Calendar thatDay = Calendar.getInstance();
            thatDay.setTime(date2);
            Calendar today = Calendar.getInstance();
            thatDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
            if (thatDay.getTimeInMillis() - today.getTimeInMillis() < 0)
            {
                thatDay.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
                diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
            }
            else
            {
                diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
            }
            /*long days = diff/(24*60*60*1000);
            if(days > 155)
            {
                Log.d("Date", "Your birthday is far away");
            }*/
            
            if(diff-604800000 > 0)
                createNotification(diff-604800000);
            if(diff-172800000 > 0)
                createNotification(diff-172800000);
            else
                createNotification(diff);
                
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTitle = mDrawerTitle = getTitle();
        ParseUser currentUser = ParseUser.getCurrentUser();
        String User=currentUser.getObjectId();

        File direct = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" );

        if(!direct.exists()) {
            if(direct.mkdir()); //directory is created;
        }

        File direct1 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User);

        if(!direct1.exists()) {
            if(direct1.mkdir()); //directory is created;
        }
        File direct2 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Saved");

        if(!direct2.exists()) {
            if(direct2.mkdir()); //directory is created;
        }
        File direct3 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/UnSaved");

        if(!direct3.exists()) {
            if(direct3.mkdir()); //directory is created;
        }
        File direct4 = new File(Environment.getExternalStorageDirectory()+"/YourEvents/" + User + "/Thumbnails");

        if(!direct4.exists()) {
            if(direct4.mkdir()); //directory is created;
        }
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        //navDrawerItems.add(new NavDrawerItem("Previous Events", navMenuIcons.getResourceId(0, -1)));
        // My Profile
        JSONObject userProfile = currentUser.getJSONObject("profile");
        try {
            navDrawerItems.add(new NavDrawerItem("Hi, "+userProfile.getString("name"), navMenuIcons.getResourceId(1, -1)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // About Us
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Terms and Policies
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Log Out
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setLogo(R.color.transparent);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFE4E4E4));
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.vinvite);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER
                | Gravity.CENTER_VERTICAL);
        //layoutParams.rightMargin = 40;

        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);
        /*final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setLogo(R.color.transparent);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar, null);
        ImageButton nav = (ImageButton)mCustomView.findViewById(R.id.nav_icon);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                else{
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
            }
        });*/
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //displayView(0);
        }
        //actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TabsPagerAdapter mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Adding tabs
        for (String tab_name: tabs){
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        /*actionBar.setSelectedNavigationItem(0);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);*/
    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            switch (position){
                case 1:
                    
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse("http://www.yourevent.co/about#about"));
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(i);
                break;
                case 2:
                    i = new Intent("android.intent.action.VIEW", Uri.parse("http://www.yourevent.co/privacy"));
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(i);
                break;
                case 3:
                    ParseUser.logOut();
                    i = new Intent(Home.this, LoginPage.class);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(i);
                    Home.this.finish();
                break;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    public void createNotification(long delay) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent i = new Intent(this, Home.class);
        //intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        //intent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pIntent2 = PendingIntent.getActivity(this, 0, i, 0);

        // Build notification
        // Actions are just fake
        Notification noti =  new Notification.Builder(this)
                .setContentTitle("Your Birthday is about to come!")
                .setContentText("Invite your Friends, family!").setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent2)
                /*.addAction(R.drawable.vinvite, "Call", pIntent)
                .addAction(R.drawable.vinvite, "More", pIntent)
                .addAction(R.drawable.vinvite, "And more", pIntent)*/.build();
        //NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        //notificationManager.notify(0, noti);

        Intent intent = new Intent(this, NotificationPublisher.class);
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 0);
        intent.putExtra(NotificationPublisher.NOTIFICATION, noti);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pIntent);

    }
}
