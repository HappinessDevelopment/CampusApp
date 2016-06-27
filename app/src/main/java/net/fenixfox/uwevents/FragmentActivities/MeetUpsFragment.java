package net.fenixfox.uwevents.FragmentActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fenixfox.uwevents.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetUpsFragment extends Fragment {


    public MeetUpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meet_ups, container, false);
    }

}
