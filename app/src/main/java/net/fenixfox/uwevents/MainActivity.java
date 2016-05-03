package net.fenixfox.uwevents;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity implements AsyncResponse {
    private RecyclerView recyclerView;
    private EventsAdapter mAdapter;
    private EventList completeEventList;
    private JsonUpdater asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent mServiceIntentJson = new Intent(this, JsonParseService.class);
        //EventBus.getDefault().register(this);
        //startService(mServiceIntentJson);

        asyncTask = (JsonUpdater) new JsonUpdater(MainActivity.this).execute();

        asyncTask.delegate = this;

//        try {
//            new JsonOperation().execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void doThis(EventList eventListResult) {
//        //events = eventListResult.getmResultValue();
//        EventBus.getDefault().post(0);
//
//
//    }

    @Override
    public void onJsonFinish(EventList output) {
        completeEventList = output;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new EventsAdapter(completeEventList.getmResultValue());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private class JsonUpdater extends AsyncTask<Void, Void, EventList> {
        private static final String TAG = "uwevents.JsonParse";
        private static final String SERVER_URL = "http://fenixfox.net:60000/events.json";
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
            return event;
        }

        @Override
        protected void onPostExecute(EventList results) {
            if (dialog.isShowing()) {
                this.dialog.dismiss();
            }
            delegate.onJsonFinish(results);
        }
    }
}
