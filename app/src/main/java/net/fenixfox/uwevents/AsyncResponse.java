package net.fenixfox.uwevents;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Youngmin Lee on 5/2/2016.
 */
public interface AsyncResponse {
    void onJsonFinish(EventList output);

}
