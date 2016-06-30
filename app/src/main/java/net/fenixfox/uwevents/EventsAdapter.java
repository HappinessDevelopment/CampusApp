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

    public Event removeItem(int position) {
        final Event model = eventList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Event model) {
        eventList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Event model = eventList.remove(fromPosition);
        eventList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(ArrayList<Event> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<Event> newModels) {
        for (int i = eventList.size() - 1; i >= 0; i--) {
            final Event model = eventList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<Event> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Event model = newModels.get(i);
            if (!eventList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<Event> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Event model = newModels.get(toPosition);
            final int fromPosition = eventList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}
