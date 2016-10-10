package net.fenixfox.uwevents.Events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO class to encapsulate JSON data
 */
//Event.java

public class Event implements Parcelable {

    private String category; // event category
    private long start; //event start time
    private String formmatedStart; //string formatted start time
    private long end; //event end time
    private String description; //description of event
    private String title; //title of event
    private String location; //location of event (room)

    public Event(String title, String location, long time, String
            description, String category) {
        this.title = title;
        this.location = location;
        this.start = time;
        this.description = description;
        this.category = category;
    }

    public Event(Parcel in){
        String[] data = new String[10];
        in.readStringArray(data);

        this.location = data[0];
        this.start = Long.parseLong(data[1]);
        this.description = data[2];
        this.category = data[4];
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }

    public String getCategory() {
        return category;
    }

    public String getFormmatedStart() {
        return formmatedStart;
    }

    public void setFormmatedStart(String formmatedStart) {
        this.formmatedStart = formmatedStart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.title,
                this.location,
                String.valueOf(this.start),
                this.description,
                this.category});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}

