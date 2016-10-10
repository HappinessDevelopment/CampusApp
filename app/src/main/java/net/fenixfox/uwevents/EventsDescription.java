package net.fenixfox.uwevents;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Youngmin Lee on 10/8/2016.
 */

public class EventsDescription extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();

        //Event event = bundle.getParcelable("Event");

        //Log.i("Event data", event.getCategory() + event.getDescription());

        TextView tv = (TextView) findViewById(R.id.messageTextView);
        tv.setText((String) bundle.getSerializable("Event"));
    }
}
