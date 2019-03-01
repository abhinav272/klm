package com.abhinav.klmdemoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.klmdemoapp.adapters.AllFlightRoutesAdapter;
import com.abhinav.klmdemoapp.base.BaseFragment;
import com.abhinav.klmdemoapp.vm.AllFlightRoutesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AllFlightRoutesListingFragment extends BaseFragment {

    private static final String ORIGIN = "origin";
    private static final String DESTINATION = "destination";
    private static final String TITLE_FORMAT = "%s To %s";
    @BindView(R.id.rv_flights)
    RecyclerView rvFlights;
    Unbinder unbinder;
    private AllFlightRoutesViewModel viewModel;
    private AllFlightRoutesAdapter adapter;
    private String origin, destination;

    public static AllFlightRoutesListingFragment getInstance(String origin, String destination) {
        Bundle bundle = new Bundle();
        bundle.putString(ORIGIN, origin);
        bundle.putString(DESTINATION, destination);

        AllFlightRoutesListingFragment fragment = new AllFlightRoutesListingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AllFlightRoutesAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_flight_routes_listing, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewModel();
        if (getArguments() != null) {
            origin = getArguments().getString(ORIGIN);
            destination = getArguments().getString(DESTINATION);
        }
        updateTitle(String.format(TITLE_FORMAT, origin, destination));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList();
    }

    private void initList() {
        rvFlights.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFlights.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(AllFlightRoutesViewModel.class);
        initLiveDataObserver();
    }

    private void initLiveDataObserver() {
        viewModel.getListLiveData().observe(this, flights -> adapter.submitList(flights));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
