package net.fenixfox.uwevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Burhan on 6/13/2016.
 */
public class SplashActivity extends Activity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up_splash_screen);

        intent = new Intent(this, LoginActivity.class);
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 700);

    }
}
