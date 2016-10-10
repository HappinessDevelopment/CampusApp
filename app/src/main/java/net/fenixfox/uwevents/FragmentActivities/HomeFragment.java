package net.fenixfox.uwevents.FragmentActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.fenixfox.uwevents.Events.Event;
import net.fenixfox.uwevents.Events.EventDataHolder;
import net.fenixfox.uwevents.Events.EventList;
import net.fenixfox.uwevents.EventsUI.EventsAdapter;
import net.fenixfox.uwevents.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{
    private static final String TAG = "RecyclerViewFragment";
    private EventList eventList;
    private RecyclerView.Adapter<EventsAdapter.MVH> mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

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
        mAdapter = new EventsAdapter(eventList.getmResultValue(), getContext());
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


    public ArrayList<Event> filter(ArrayList<Event> events, String query){
        query = query.toLowerCase();

        final ArrayList<Event> filteredEventList = new ArrayList<Event>();
        for(Event event : events){
            final String text = event.getTitle().toLowerCase();
            if(text.contains(query)){
                filteredEventList.add(event);
            }
        }
        return filteredEventList;
    }
}
