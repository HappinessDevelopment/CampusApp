package net.fenixfox.uwevents.Events;

import net.fenixfox.uwevents.Events.EventList;

/**
 * Created by Youngmin Lee on 5/2/2016.
 */
public interface AsyncResponse {
    void onJsonFinish(EventList output);

}
