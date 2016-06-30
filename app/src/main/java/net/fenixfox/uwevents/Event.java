package net.fenixfox.uwevents;

/**
 * POJO class to encapsulate JSON data
 */
//Event.java

public class Event {

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
}

