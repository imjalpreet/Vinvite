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

        Parse.initialize(this, "D3jgbFBGfMIqdAgwORRBPfPzGRwdSl572cs1DiQt",
                "2A6ICrbaGWaEo98b4j5xZfcHxuWbJgLXOhQ0LKPt");

        // Set your Facebook App Id in strings.xml
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));

    }

}
