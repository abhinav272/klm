package com.abhinav.klmdemoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.abhinav.klmdemoapp.R;
import com.abhinav.klmdemoapp.data.response.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends ArrayAdapter<Destination> {

    private final int resource;
    private final ArrayList<Destination> items;
    private List<Destination> tempItems, suggestions;
    private Filter destinationFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Destination destination = (Destination) resultValue;
            return destination.getCode();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Destination destination : tempItems) {
                    if (destination.getCode().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(destination);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Destination> tempValues = (ArrayList<Destination>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                items.clear();
                for (Destination destinationObj : tempValues) {
                    items.add(destinationObj);
                    notifyDataSetChanged();
                }
            } else {
                items.clear();
                notifyDataSetChanged();
            }
        }
    };

    public DestinationAdapter(@NonNull Context context, int resource, List<Destination> list) {
        super(context, resource);
        this.resource = resource;
        this.items = new ArrayList<>(list);
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            }
            Destination item = getItem(position);
            TextView code = view.findViewById(R.id.tv_code);
            TextView label = view.findViewById(R.id.tv_label);

            code.setText(item.getCode());
            label.setText(item.getLabel());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public Destination getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return destinationFilter;
    }
}
