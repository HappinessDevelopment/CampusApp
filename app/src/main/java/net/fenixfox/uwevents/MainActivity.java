package net.fenixfox.uwevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import net.fenixfox.uwevents.FragmentActivities.HomeFragment;
import net.fenixfox.uwevents.FragmentActivities.MeetUpsFragment;

public class MainActivity extends AppCompatActivity implements AsyncResponse,
        SearchView.OnQueryTextListener {
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
                Toast.makeText(getApplicationContext(), "Profile", Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        // TODO: Set up search functionality.
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(item);
        searchView.setOnQueryTextListener(this);
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
                // TODO: Add a working calendar.
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.action_filter:
                // TODO: Connect the filter to the eventList.
                startActivity(new Intent(this, FilterActivity.class));
                break;
        }
    return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return false;
    }
}
