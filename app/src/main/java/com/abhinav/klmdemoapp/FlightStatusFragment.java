package com.abhinav.klmdemoapp;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.abhinav.klmdemoapp.base.BaseFragment;
import com.abhinav.klmdemoapp.base.Event;
import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.data.CacheManager;
import com.abhinav.klmdemoapp.vm.FlightStatusViewModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FlightStatusFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.et_flight_number)
    EditText etFlightNumber;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.et_origin)
    AppCompatAutoCompleteTextView etOrigin;
    @BindView(R.id.btn_search)
    Button btnSearch;
    Unbinder unbinder;

    private FlightStatusViewModel viewModel;
    private FlightStatusHost host;

    public static FlightStatusFragment getInstance() {
        return new FlightStatusFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FlightStatusHost) {
            host = (FlightStatusHost) context;
        } else throw new IllegalStateException("Host must implement FlightStatusHost");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewModel();
        setupDropDowns();
        return view;
    }

    private void setupDropDowns() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getContext(), R.layout.layout_drop_down_single_item, CacheManager.getInstance().getAllAirportNames());
        etOrigin.setThreshold(1);
        etOrigin.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(FlightStatusViewModel.class);
        viewModel.initLiveData();
        initLiveDataObserver();
    }

    private void initLiveDataObserver() {
        viewModel.getUiValidationLiveData().observe(this, this::onUIValidationsResult);
        viewModel.getResponseLiveData().observe(this, flightStatusResponse -> host.showFlightStatusDetailFragment());
        viewModel.getFailureResponseLiveData().observe(this, this::onFailure);
        viewModel.getNetworkStateLiveData().observe(this, this::onNetworkStateChanged);
    }

    private void onUIValidationsResult(Event<FailureResponse> failureResponseEvent) {
        if (failureResponseEvent != null && !failureResponseEvent.isAlreadyHandled()) {
            FailureResponse content = failureResponseEvent.getContent();
            showToastShort(content.getErrorMessage());
            switch (content.getErrorCode()) {
                case 101:
                    shakeView(etDate);
                    break;
                case 102:
                    shakeView(etFlightNumber);
                    break;
                case 104:
                case 103:
                    shakeView(etOrigin);
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.et_date, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_date:
                showDatePicker();
                break;
            case R.id.btn_search:
                viewModel.onSearchClicked(etOrigin.getText().toString().trim(),
                        etFlightNumber.getText().toString().trim(),
                        etDate.getText().toString().trim());
                break;
        }
    }

    private void showDatePicker() {
        new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        hideKeyboard();
        StringBuilder builder = new StringBuilder();
        builder.append(dayOfMonth).append("-").append(month + 1).append("-").append(year);
        etDate.setText(builder.toString());
    }

    public interface FlightStatusHost {
        void showFlightStatusDetailFragment();
    }
}
