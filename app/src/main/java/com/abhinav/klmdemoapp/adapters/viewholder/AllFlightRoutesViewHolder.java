package com.abhinav.klmdemoapp.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhinav.klmdemoapp.R;
import com.abhinav.klmdemoapp.data.response.OperationalFlight;
import com.abhinav.klmdemoapp.data.response.Times;
import com.abhinav.klmdemoapp.utils.loadindicator.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllFlightRoutesViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_airline)
    TextView tvAirline;
    @BindView(R.id.tv_airline_status)
    TextView tvAirlineStatus;
    @BindView(R.id.tv_takeoff)
    TextView tvTakeoff;
    @BindView(R.id.tv_landing)
    TextView tvLanding;
    @BindView(R.id.tv_journey_time_placeholder)
    TextView tvJourneyTimePlaceholder;
    @BindView(R.id.tv_journey_time)
    TextView tvJourneyTime;
    @BindView(R.id.tv_departure_gate_placeholder)
    TextView tvDepartureGatePlaceholder;
    @BindView(R.id.tv_departure_gate)
    TextView tvDepartureGate;
//    R.layout.layout_all_flight_route_single_item

    public AllFlightRoutesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(OperationalFlight item) {
        tvAirline.setText(item.getAirline().getName());
        tvAirlineStatus.setText(item.getFlightStatusPublicLangTransl());
        Times takeoff = item.getFlightLegs().get(0).getDepartureInformation().getTimes();
        Times landing = item.getFlightLegs().get(0).getArrivalInformation().getTimes();
        tvTakeoff.setText(takeoff.getFormattedDatePublicTransl());
        tvLanding.setText(landing.getFormattedDatePublicTransl());
        tvJourneyTime.setText(DateUtil.getDifferenceInDates(takeoff.getDate(), landing.getDate()));
        if (item.getFlightLegs().get(0).getDepartureInformation().getAirport().getDeparturePlaces() != null)
            tvDepartureGate.setText(item.getFlightLegs().get(0).getDepartureInformation().getAirport().getDeparturePlaces().getTerminalCode());
    }
}
