package net.fenixfox.uwevents;

import android.text.format.DateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventList {
    private ArrayList<Event> mResultValue = new ArrayList<Event>();

    EventList(Event[] jsonArray) {
        //mResultValue = resultValue;
        for (int i = 0; i < jsonArray.length; i++) {
            mResultValue.add(jsonArray[i]);

            String fDate = DateUtils.getRelativeTimeSpanString(jsonArray[i].getStart()*1000).toString();
            jsonArray[i].setFormmatedStart(fDate);
        }
        Collections.sort(mResultValue, new Comparator<Event>() {
            @Override
            public int compare(Event lhs, Event rhs) {
                int cmp = lhs.getStart() > rhs.getStart() ? +1 : lhs.getStart() < rhs.getStart() ? -1 : 0;
                return cmp;
            }
        });
    }


    public ArrayList<Event> getmResultValue() {
        return mResultValue;
    }
}
