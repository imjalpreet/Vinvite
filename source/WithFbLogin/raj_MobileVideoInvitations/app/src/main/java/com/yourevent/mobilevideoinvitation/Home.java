package com.yourevent.mobilevideoinvitation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;
import android.content.Intent;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.yourevent.mobilevideoinvitation.adapter.TabsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;


public class Home extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private String[] tabs = {"Select Event", "Previous Invitations"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Initialization
        actionBar = getActionBar();
        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        Session session = ParseFacebookUtils.getSession();
        if (session != null && session.isOpened()) {
            makeMeRequest();
        }


        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
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
    public void onBackPressed()
    {
        onLogoutButtonClicked();
    }


    private void makeMeRequest() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {
                            // Create a JSON object to hold the profile info
                            JSONObject userProfile = new JSONObject();
                            try {
                                // Populate the JSON object
                                userProfile.put("facebookId", user.getId());
                                userProfile.put("name", user.getName());


                                // Save the user profile info in a user property
                                ParseUser currentUser = ParseUser
                                        .getCurrentUser();
                                currentUser.put("profile", userProfile);
                                currentUser.saveInBackground();

                                // Show the user info
                                updateViewsWithProfileInfo();
                            } catch (JSONException e) {
                                Log.d(IntegratingFacebook.TAG,
                                        "Error parsing returned user data.");
                            }

                        } else if (response.getError() != null) {
                            if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY)
                                    || (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
                                Log.d(IntegratingFacebook.TAG,
                                        "The facebook session was invalidated.");
                                onLogoutButtonClicked();
                            } else {
                                Log.d(IntegratingFacebook.TAG,
                                        "Some other error: "
                                                + response.getError()
                                                .getErrorMessage());
                            }
                        }
                    }
                });
        request.executeAsync();

    }


    private void updateViewsWithProfileInfo() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.get("profile") != null) {
            JSONObject userProfile = currentUser.getJSONObject("profile");
            try {

                if (userProfile.getString("name") != null) {
                    //   userNameView.setText(userProfile.getString("name"));
                    Toast.makeText(Home.this,
                            "Hello " + userProfile.getString("name"), Toast.LENGTH_LONG).show();

                    //     Toast.makeText(AndroidVideoCapture.this,
                    //           "Fail in prepareMediaRecorder()!\n - Ended -",
                } else {
                    Toast.makeText(Home.this,
                            "Logged In", Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                Log.d(IntegratingFacebook.TAG,
                        "Error parsing saved user data.");
            }

        }
    }


    private void onLogoutButtonClicked() {
        // Log the user out
        ParseUser.logOut();

        // Go to the login view
        startLoginActivity();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
