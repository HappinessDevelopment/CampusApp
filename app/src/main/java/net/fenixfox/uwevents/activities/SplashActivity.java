package net.fenixfox.uwevents.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import net.fenixfox.uwevents.R;

/**
 * Created by Burhan on 6/13/2016.
 */
public class SplashActivity extends Activity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up_splash_screen);

        //Temporarily disabling LoginActivity since it doesnt serve a purpose in this application.
        //NOTE: remember to add LoginActivity to Android Manifest.
        // intent = new Intent(this, LoginActivity.class);
        intent = new Intent(this, MainActivity.class);
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 700);

    }
}