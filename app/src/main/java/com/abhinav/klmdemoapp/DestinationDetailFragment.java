package com.abhinav.klmdemoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhinav.klmdemoapp.base.BaseFragment;
import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;
import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;
import com.abhinav.klmdemoapp.utils.AppUtils;
import com.abhinav.klmdemoapp.vm.DestinationSearchViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DestinationDetailFragment extends BaseFragment {

    private static final String TAG = "DestinationDetailFragme";

    @BindView(R.id.iv_backdrop)
    ImageView ivBackdrop;
    @BindView(R.id.tv_airport)
    TextView tvAirport;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_time_zone)
    TextView tvTimeZone;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_temprature)
    TextView tvTemprature;
    @BindView(R.id.iv_travel)
    ImageView ivTravel;
    @BindView(R.id.tv_travel)
    TextView tvTravel;
    Unbinder unbinder;
    private DestinationSearchViewModel viewModel;

    public static DestinationDetailFragment getInstance() {
        return new DestinationDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination_details, container, false);
        initViewModel();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(DestinationSearchViewModel.class);
        initLiveDataObserver();
    }

    private void initLiveDataObserver() {
        viewModel.getResponseLiveData().observe(this, this::onDestinationLoaded);
        viewModel.getAirportLiveData().observe(this, this::onAirportDataLoaded);
    }

    private void onAirportDataLoaded(AirportCompleteInfoResponse response) {
        tvAirport.setText(response.getAirport().getDescription());
        tvCountry.setText(response.getCountry().getLabel());
        tvRegion.setText(response.getRegion().getLabel());
    }

    private void onDestinationLoaded(DestinationDetailsResponse destinationDetailsResponse) {

        String imageUrl = AppUtils.getProperty(destinationDetailsResponse.getCityCode());
        try {
            if (!TextUtils.isEmpty(imageUrl)) {
                Picasso.get().load(imageUrl)
                        .fit().centerCrop()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .into(ivBackdrop);
            }
        } catch (Exception e) {
            Log.e(TAG, "onDestinationLoaded: ", e);
        }

        tvLanguage.setText(destinationDetailsResponse.getSpokenLanguages().get(0).getLabel());
        tvTimeZone.setText(destinationDetailsResponse.getTime().getTimeZone());

        tvWeather.setText(destinationDetailsResponse.getWeather().getData().getDescription().getDescription());
        tvTemprature.setText(viewModel.getFormattedTemp(destinationDetailsResponse.getWeather().getData().getTemperature().get(0)));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
