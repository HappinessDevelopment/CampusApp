package net.fenixfox.uwevents;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import net.fenixfox.uwevents.FragmentActivities.HomeFragment;
import net.fenixfox.uwevents.FragmentActivities.MeetUpsFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private JsonUpdater asyncTask;
    private android.support.v7.widget.Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTask = (JsonUpdater) new JsonUpdater(MainActivity.this).execute();
        asyncTask.delegate = this;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onJsonFinish(EventList output) {
        toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment(), "EVENTS");
        viewPagerAdapter.addFragments(new MeetUpsFragment(), "MEET-UPS");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        imageButton = (ImageButton) findViewById(R.id.action_profile);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Search", Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id= item.getItemId();
        switch(res_id) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Search", Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.action_calendar:
                Toast.makeText(getApplicationContext(), "Calendar", Toast
                        .LENGTH_SHORT).show();
                break;
            case R.id.action_filter:
                Toast.makeText(getApplicationContext(), "Filter", Toast
                        .LENGTH_SHORT).show();
                break;
        }
    return true;
    }

    class JsonUpdater extends AsyncTask<Void, Void, EventList> {
        private static final String TAG = "uwevents.JsonParse";
        private static final String SERVER_URL = "https://api.fenixfox.net/events.json";
        private HttpURLConnection urlConnection;
        private Event[] gson;
        public AsyncResponse delegate = null;

        private Activity activity;
        private ProgressDialog dialog;
        private Context context;


        public JsonUpdater(MainActivity activity) {
            this.activity = activity;
            this.context = activity;
            this.dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
             this.dialog.setMessage("Processing");
            this.dialog.show();
        }

        @Override
        protected EventList doInBackground(Void... params) {
            Log.i(TAG, "SERVICE HAS STARTED");
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(SERVER_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            gson = new Gson().fromJson(result.toString(), Event[].class);
            EventBus.getDefault().post(new EventList(gson));
            EventList event = new EventList(gson);
            //System.out.println("Results: " + result.toString());
            return event;
        }

        @Override
        protected void onPostExecute(EventList results) {
            if (dialog.isShowing()) {
                this.dialog.dismiss();
            }
            EventDataHolder.getInstance().setData(results);
            delegate.onJsonFinish(results);
        }
    }
}
