package net.fenixfox.uwevents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MVH> {
    private ArrayList<Event> eventList = new ArrayList<>();

    public EventsAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public class MVH extends RecyclerView.ViewHolder {
        public TextView title, start, location;

        public MVH(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            start = (TextView) view.findViewById(R.id.start);
            location = (TextView) view.findViewById(R.id.location);
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
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
