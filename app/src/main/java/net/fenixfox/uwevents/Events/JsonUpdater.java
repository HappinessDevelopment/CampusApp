package net.fenixfox.uwevents.Events;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import net.fenixfox.uwevents.EventsUI.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Burhan on 6/29/2016.
 */
public class JsonUpdater extends AsyncTask<Void, Void, EventList> {
    private static final String TAG = "uwevents.JsonParse";
    private static final String SERVER_URL = "https://api.fenixfox.net/events.json";
    private HttpURLConnection urlConnection;
    private Event[] gson;
    public MainActivity delegate = null;

    private Activity activity;
    private ProgressDialog dialog;
    private Context context;

    public JsonUpdater(Activity activity) {
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
