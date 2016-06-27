package net.fenixfox.uwevents;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MVH> {
    private ArrayList<Event> eventList = new ArrayList<>();

    public EventsAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public class MVH extends RecyclerView.ViewHolder {
        public TextView title, start, location, description;
        public LinearLayout linearLayout;

        public MVH(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            start = (TextView) view.findViewById(R.id.start);
            location = (TextView) view.findViewById(R.id.location);
            description = (TextView) view.findViewById(R.id.description);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        }
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_row, parent, false);
        return new MVH(itemView);
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.getTitle());
        holder.location.setText(event.getLocation());
        holder.start.setText(event.getFormmatedStart());
        holder.description.setText(event.getDescription());
        switch (event.getCategory()){
            case "Academics": holder.linearLayout.setBackgroundColor(Color.CYAN);
                break;
            case "Athletics": holder.linearLayout.setBackgroundColor(Color
                    .BLUE);
                break;
            case "Career Fairs": holder.linearLayout.setBackgroundColor(Color
                    .GRAY);
                break;
            case "Ceremonies": holder.linearLayout.setBackgroundColor(Color
                    .GREEN);
                break;
            case "Commencement": holder.linearLayout.setBackgroundColor(Color
                    .MAGENTA);
                break;
            case "Conferences": holder.linearLayout.setBackgroundColor(Color
                    .RED);
                break;
            case "Diversity Outreach": holder.linearLayout.setBackgroundColor
                    (Color.YELLOW);
                break;
            case "Exhibits": holder.linearLayout.setBackgroundColor(Color.RED);
                break;
            case "Graduate": holder.linearLayout.setBackgroundColor(Color.MAGENTA);
                break;
            case "Information Sessions": holder.linearLayout.setBackgroundColor
                    (Color.GREEN);
                break;
            case "Lectures/Seminars": holder.linearLayout.setBackgroundColor
                    (Color.GRAY);
                break;
            case "Not Specified": holder.linearLayout.setBackgroundColor(Color
                    .BLUE);
                break;
            case "Orientation": holder.linearLayout.setBackgroundColor(Color
                    .CYAN);
                break;
            case "Performances": holder.linearLayout.setBackgroundColor(Color
                    .CYAN);
                break;
            case "Screenings": holder.linearLayout.setBackgroundColor(Color
                    .CYAN);
                break;
            case "Special Events": holder.linearLayout.setBackgroundColor(Color
                    .CYAN);
                break;
            case "Student Activities": holder.linearLayout.setBackgroundColor
                    (Color.CYAN);
                break;
            case "Workshops": holder.linearLayout.setBackgroundColor(Color
                    .CYAN);
                break;
            default: holder.linearLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
