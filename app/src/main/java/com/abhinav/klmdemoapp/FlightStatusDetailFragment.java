package com.abhinav.klmdemoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abhinav.klmdemoapp.base.BaseFragment;
import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;
import com.abhinav.klmdemoapp.utils.loadindicator.DateUtil;
import com.abhinav.klmdemoapp.vm.FlightStatusViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FlightStatusDetailFragment extends BaseFragment {

    @BindView(R.id.tv_status_placeholder)
    TextView tvStatusPlaceholder;
    @BindView(R.id.tv_time_status)
    TextView tvTimeStatus;
    @BindView(R.id.v_divider_1)
    View vDivider1;
    @BindView(R.id.tv_departure_placeholder)
    TextView tvDeparturePlaceholder;
    @BindView(R.id.tv_departure_airport)
    TextView tvDepartureAirport;
    @BindView(R.id.tv_scheduled_placeholder)
    TextView tvScheduledPlaceholder;
    @BindView(R.id.tv_scheduled_date)
    TextView tvScheduledDate;
    @BindView(R.id.tv_terminal_placeholder)
    TextView tvTerminalPlaceholder;
    @BindView(R.id.tv_terminal)
    TextView tvTerminal;
    @BindView(R.id.v_divider_2)
    View vDivider2;
    @BindView(R.id.tv_arrival_placeholder)
    TextView tvArrivalPlaceholder;
    @BindView(R.id.tv_arrival_airport)
    TextView tvArrivalAirport;
    @BindView(R.id.tv_scheduled_arrival_placeholder)
    TextView tvScheduledArrivalPlaceholder;
    @BindView(R.id.tv_scheduled_arrival_date)
    TextView tvScheduledArrivalDate;
    @BindView(R.id.tv_terminal_arrival_placeholder)
    TextView tvTerminalArrivalPlaceholder;
    @BindView(R.id.tv_terminal_arrival)
    TextView tvTerminalArrival;
    @BindView(R.id.container_status)
    ConstraintLayout containerStatus;
    @BindView(R.id.tv_uber_placeholder)
    TextView tvUberPlaceholder;
    @BindView(R.id.tv_uber_text)
    TextView tvUberText;
    @BindView(R.id.btn_uber_reminder)
    Button btnUberReminder;
    Unbinder unbinder;

    private FlightStatusViewModel viewModel;

    public static FlightStatusDetailFragment getInstance() {
        return new FlightStatusDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_status_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewModel();
        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(FlightStatusViewModel.class);
        initLiveDataObserver();
    }

    private void initLiveDataObserver() {
        viewModel.getResponseLiveData().observe(this, this::onDataLoaded);
    }

    private void onDataLoaded(FlightStatusResponse flightStatusResponse) {
        tvArrivalAirport.setText(flightStatusResponse.getFlightLegs().get(0).getArrivalInformation().getAirport().getName());
        tvDepartureAirport.setText(flightStatusResponse.getFlightLegs().get(0).getDepartureInformation().getAirport().getName());

        tvScheduledDate.setText(flightStatusResponse.getFlightLegs().get(0).getDepartureInformation().getTimes().getFormattedDatePublicTransl());
        tvScheduledArrivalDate.setText(flightStatusResponse.getFlightLegs().get(0).getArrivalInformation().getTimes().getFormattedDatePublicTransl());

        String timeStatus = DateUtil.getFormattedTimeStatus(flightStatusResponse.getFlightLegs().get(0).getDepartureInformation().getTimes().getDate());
        timeStatus = (timeStatus == null) ? getString(R.string.txt_already_departed) : timeStatus;
        tvTimeStatus.setText(timeStatus);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_uber_reminder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_uber_reminder:
                showToastShort(getString(R.string.txt_uber_reminder_set));
                break;
        }
    }
}
