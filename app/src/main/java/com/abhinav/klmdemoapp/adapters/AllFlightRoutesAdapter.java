package com.abhinav.klmdemoapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.klmdemoapp.R;
import com.abhinav.klmdemoapp.adapters.viewholder.AllFlightRoutesViewHolder;
import com.abhinav.klmdemoapp.data.response.OperationalFlight;

import java.util.ArrayList;
import java.util.List;

public class AllFlightRoutesAdapter extends RecyclerView.Adapter<AllFlightRoutesViewHolder> {

    private List<OperationalFlight> operationalFlights;

    public AllFlightRoutesAdapter() {
        operationalFlights = new ArrayList<>();
    }

    @NonNull
    @Override
    public AllFlightRoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_flight_route_single_item, parent, false);
        return new AllFlightRoutesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllFlightRoutesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void submitList(List<OperationalFlight> flights) {
        operationalFlights.addAll(flights);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return operationalFlights.size();
    }

    public OperationalFlight getItem(int position) {
        return operationalFlights.get(position);
    }
}
