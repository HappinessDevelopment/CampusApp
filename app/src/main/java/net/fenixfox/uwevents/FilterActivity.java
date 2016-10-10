package net.fenixfox.uwevents;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Burhan on 6/29/2016.
 */
public class FilterActivity extends AppCompatActivity {

    private FilterAdapter filterAdapter;
    private ImageButton profileBackIcon;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_list_screen);

        profileBackIcon = (ImageButton) findViewById(R.id.action_profile);
        Context context = getApplicationContext();

        final Animation outAnimation = AnimationUtils.loadAnimation(context, R.anim
                .fade_out);
        final Animation inAnimation = AnimationUtils.loadAnimation(context, R.anim
                .fade_in);

        profileBackIcon.startAnimation(outAnimation);

        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                profileBackIcon.setImageResource(R.drawable.ic_back_button);
                profileBackIcon.startAnimation(inAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        displayListView();

        profileBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayListView(){
        // Array of possible filters
        ArrayList<FilterData> filters = new ArrayList<FilterData>();

        filters.add(new FilterData("Aca", "Academics", false));
        filters.add(new FilterData("Ath", "Athletics" ,false));
        filters.add(new FilterData("Car", "Career Fairs", false));
        filters.add(new FilterData("Cer", "Ceremonies", false));
        filters.add(new FilterData("Com", "Commencement", false));
        filters.add(new FilterData("Con", "Conferences", false));
        filters.add(new FilterData("Div", "Diversity Outreach", false));
        filters.add(new FilterData("Exh", "Exhibits", false));
        filters.add(new FilterData("Gra", "Graduate", false));
        filters.add(new FilterData("Inf", "Information Sessions", false));
        filters.add(new FilterData("Lec", "Lectures/Seminars", false));
        filters.add(new FilterData("Not", "Not Specified", false));
        filters.add(new FilterData("Ori", "Orientation", false));
        filters.add(new FilterData("Per", "Performances", false));
        filters.add(new FilterData("Scr", "Screenings", false));
        filters.add(new FilterData("Spe", "Special Events", false));
        filters.add(new FilterData("Stu", "Student Activities", false));
        filters.add(new FilterData("Wor", "Workshops", false));

        filterAdapter = new FilterAdapter(this, R.layout.filter_list_item, filters);
        listView = (ListView) findViewById(R.id.filter_list_view);
        listView.setAdapter(filterAdapter);
    }

    private class FilterAdapter extends ArrayAdapter<FilterData>{
        private ArrayList<FilterData> filterList;

        public FilterAdapter(Context context, int textViewResourceId,
                             ArrayList<FilterData> filters){
            super(context, textViewResourceId, filters);
            this.filterList = new ArrayList<FilterData>();
            this.filterList.addAll(filters);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            Log.v("Convert View", String.valueOf(position));

            if(convertView == null){
                LayoutInflater vi = (LayoutInflater) getSystemService(Context
                        .LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.filter_list_item, null);

                holder = new ViewHolder();
                holder.filterCode = (TextView) convertView.findViewById(R.id
                        .filter_code);
                holder.filterName = (TextView) convertView.findViewById(R.id
                        .filter_name);
                holder.filterCheckBox = (CheckBox) convertView.findViewById(R
                        .id.filter_checkBox);
                convertView.setTag(holder);

                holder.filterCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox ) v;
                        FilterData filterData = (FilterData) cb.getTag();
                        filterData.setSelected(cb.isChecked());
                    }
                });
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            FilterData filterData = filterList.get(position);
            holder.filterCode.setText(" (" +  filterData.getFilterCode() + ")");
            holder.filterName.setText(filterData.getFilterName());
            holder.filterCheckBox.setChecked(filterData.isSelected());
            holder.filterCheckBox.setTag(filterData);
            return convertView;
        }

        private class ViewHolder {
            TextView filterCode;
            TextView filterName;
            CheckBox filterCheckBox;
        }
    }

    public class FilterData{
        private String filterCode;
        private String filterName;
        private boolean selected;

        public FilterData(String filterCode, String filterName, boolean
                selected){
            this.filterCode = filterCode;
            this.filterName = filterName;
            this.selected = selected;
        }

        public String getFilterCode() {
            return filterCode;
        }

        public String getFilterName() {
            return filterName;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}