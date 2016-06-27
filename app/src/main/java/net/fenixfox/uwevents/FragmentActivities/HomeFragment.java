package net.fenixfox.uwevents.FragmentActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fenixfox.uwevents.EventDataHolder;
import net.fenixfox.uwevents.EventList;
import net.fenixfox.uwevents.EventsAdapter;
import net.fenixfox.uwevents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private EventList eventList;
    private EventsAdapter mAdapter;


    public HomeFragment(){
        this.eventList = EventDataHolder.getInstance().getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id
                .recycler_view);

       mAdapter = new EventsAdapter(eventList.getmResultValue());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
