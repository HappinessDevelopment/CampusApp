package net.fenixfox.uwevents.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.fenixfox.uwevents.Events.Event;
import net.fenixfox.uwevents.activities.EventsActivity;
import net.fenixfox.uwevents.Events.ItemClickListener;
import net.fenixfox.uwevents.R;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MVH> implements Filterable {
    private ArrayList<Event> originalEventList = new ArrayList<>();
    private ArrayList<Event> eventsList;
    private Context mContext;


    public EventsAdapter(ArrayList<Event> events, Context context) {
        this.originalEventList = events;
        this.eventsList = events;
        this.mContext = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                eventsList = (ArrayList<Event>) results.values;
                EventsAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Event> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = originalEventList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected List<Event> getFilteredResults(String constraint) {
        List<Event> results = new ArrayList<>();

        for (Event item : originalEventList) {
            if (item.getTitle().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }

    public static class MVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, start, location, description;
        public LinearLayout linearLayout;
        private ItemClickListener clickListener;

        public MVH(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            start = (TextView) view.findViewById(R.id.start);
            location = (TextView) view.findViewById(R.id.location);
            description = (TextView) view.findViewById(R.id.description);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
            view.setTag(view);
            view.setOnClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_row, parent, false);
        return new MVH(itemView);
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {
        Event event = eventsList.get(position);
        holder.title.setText(event.getTitle());
        holder.location.setText(event.getLocation());
        holder.start.setText(event.getFormmatedStart());
        holder.description.setText(event.getDescription());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("hi", String.valueOf(position) + eventsList.get(position).getTitle());
                Intent intent = new Intent(mContext, EventsActivity.class);
                intent.putExtra("Event", eventsList.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });

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
        return eventsList.size();
    }

    public Event removeItem(int position) {
        final Event model = originalEventList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Event model) {
        originalEventList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Event model = originalEventList.remove(fromPosition);
        originalEventList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(ArrayList<Event> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<Event> newModels) {
        for (int i = originalEventList.size() - 1; i >= 0; i--) {
            final Event model = originalEventList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<Event> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Event model = newModels.get(i);
            if (!originalEventList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<Event> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Event model = newModels.get(toPosition);
            final int fromPosition = originalEventList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}
