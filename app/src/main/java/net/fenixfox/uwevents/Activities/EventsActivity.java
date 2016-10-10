package net.fenixfox.uwevents.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import net.fenixfox.uwevents.R;

/**
 * Created by Youngmin Lee on 10/8/2016.
 */

public class EventsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();

        //Event event = bundle.getParcelable("Event");

        //Log.i("Event data", event.getCategory() + event.getDescription());

        TextView tv = (TextView) findViewById(R.id.Title);
        tv.setText((String) bundle.getSerializable("Event"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
