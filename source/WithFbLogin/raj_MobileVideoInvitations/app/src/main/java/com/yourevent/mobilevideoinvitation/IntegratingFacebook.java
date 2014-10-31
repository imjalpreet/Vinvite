package com.yourevent.mobilevideoinvitation;

/**
 * Created by vikram on 15/10/14.
 */
       import android.app.Application;

        import com.parse.Parse;
        import com.parse.ParseFacebookUtils;

public class IntegratingFacebook extends Application {

    static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "asO83QNQyAB3hYyJuphIWLfKjqYNrhHct9q4H28f",
                "KwrM3iFmVFgIsBwaq1HEC7MTwNCJX5hWjkszmQ94");

        // Set your Facebook App Id in strings.xml
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));

    }

}
