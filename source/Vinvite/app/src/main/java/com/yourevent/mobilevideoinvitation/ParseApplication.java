package com.yourevent.mobilevideoinvitation;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by imjalpreet on 31-10-2014.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "asO83QNQyAB3hYyJuphIWLfKjqYNrhHct9q4H28f", "KwrM3iFmVFgIsBwaq1HEC7MTwNCJX5hWjkszmQ94");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
