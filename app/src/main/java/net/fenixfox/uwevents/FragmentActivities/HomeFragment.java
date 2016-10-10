package net.fenixfox.uwevents.FragmentActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.fenixfox.uwevents.Events.EventDataHolder;
import net.fenixfox.uwevents.Events.EventList;
import net.fenixfox.uwevents.EventsUI.EventsAdapter;
import net.fenixfox.uwevents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{
    private static final String TAG = "RecyclerViewFragment";
    public RecyclerView.Adapter<EventsAdapter.MVH> mAdapter;
    private EventList eventList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;

    public HomeFragment(){
        this.eventList = EventDataHolder.getInstance().getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id
                .recycler_view);
        eventsAdapter = new EventsAdapter(eventList.getmResultValue(), getContext());
        mAdapter = eventsAdapter;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id
                .swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"lol",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return rootView;
    }

    public boolean search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                eventsAdapter.getFilter().filter(text);
                return true;
            }
        });
        return true;
    }
}
