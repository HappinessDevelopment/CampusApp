package net.fenixfox.uwevents;

/**
 * Created by Burhan on 6/26/2016.
 */
public class EventDataHolder{
    private EventList data;
    public EventList getData(){return data;}
    public void setData(EventList data){this.data = data;}

    private static final EventDataHolder holder = new EventDataHolder();
    public static EventDataHolder getInstance() {return holder;}
}


