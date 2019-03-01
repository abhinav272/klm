package com.abhinav.klmdemoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.abhinav.klmdemoapp.base.BaseActivity;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.vm.MainActivityViewModel;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements AllFlightRoutesFragment.AllFlightRoutesHost,
        ChoiceFragment.ChoiceFragmentHost, FlightStatusFragment.FlightStatusHost, DestinationSearchFragment.DestinationSearchHost {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewModel();
    }

    private void showChoiceFragment() {
        addFragment(R.id.frame_container, ChoiceFragment.getInstance()
                , ChoiceFragment.class.getSimpleName());
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.initLiveData();

        viewModel.getNetworkStateMutableLiveData().observe(this, networkState -> {
            if (networkState == NetworkState.LOADED ||
                    networkState == NetworkState.FAILED) {
                hideProgressDialog();
                showChoiceFragment();
            }

            if (networkState == NetworkState.RUNNING) {
                showProgressDialog();
            }
        });
    }

    private void showAllFlightRoutesFragment() {
        addFragmentWithBackstack(R.id.frame_container,
                AllFlightRoutesFragment.getInstance(),
                AllFlightRoutesFragment.class.getSimpleName());
    }

    @Override
    public void onAllFlightRouteLoaded(String origin, String destination) {
        addFragmentWithBackstack(R.id.frame_container,
                AllFlightRoutesListingFragment.getInstance(origin, destination),
                AllFlightRoutesListingFragment.class.getSimpleName());
    }

    @Override
    public void showSearchFlightOnRoutesFragment() {
        showAllFlightRoutesFragment();
    }

    @Override
    public void showSearchFlightStatusFragment() {
        addFragmentWithBackstack(R.id.frame_container,
                FlightStatusFragment.getInstance(),
                FlightStatusFragment.class.getSimpleName());
    }

    @Override
    public void showSearchDestinationFragment() {
        addFragmentWithBackstack(R.id.frame_container,
                DestinationSearchFragment.getInstance(),
                DestinationSearchFragment.class.getSimpleName());
    }

    @Override
    public void showFlightStatusDetailFragment() {
        addFragmentWithBackstack(R.id.frame_container,
                FlightStatusDetailFragment.getInstance(),
                FlightStatusDetailFragment.class.getSimpleName());
    }

    @Override
    public void onDestinationFetched() {
        addFragmentWithBackstack(R.id.frame_container,
                DestinationDetailFragment.getInstance(),
                DestinationDetailFragment.class.getSimpleName());
    }
}
