package com.abhinav.klmdemoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abhinav.klmdemoapp.adapters.DestinationAdapter;
import com.abhinav.klmdemoapp.base.BaseFragment;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.CacheManager;
import com.abhinav.klmdemoapp.data.response.Destination;
import com.abhinav.klmdemoapp.vm.DestinationSearchViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DestinationSearchFragment extends BaseFragment {

    @BindView(R.id.et_destination)
    AppCompatAutoCompleteTextView etDestination;
    @BindView(R.id.btn_search)
    Button btnSearch;
    Unbinder unbinder;

    private DestinationAdapter adapter;
    private DestinationSearchHost host;
    private DestinationSearchViewModel viewModel;
    private Destination destination;

    public static DestinationSearchFragment getInstance() {
        return new DestinationSearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DestinationSearchHost) {
            host = (DestinationSearchHost) context;
        } else throw new IllegalStateException("host must impl DestinationSearchHost");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_destination, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewModel();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter = new DestinationAdapter(getContext(), R.layout.layout_drop_down_custom, CacheManager.getInstance().getDestinations());
        etDestination.setAdapter(adapter);
        etDestination.setThreshold(1);
        etDestination.setOnItemClickListener((parent, view, position, id) -> {
            destination = (Destination) parent.getAdapter().getItem(position);
            hideKeyboard();
        });
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(DestinationSearchViewModel.class);
        viewModel.initLiveData();
        initLiveDataObserver();
    }

    private void initLiveDataObserver() {

        viewModel.getNetworkStateLiveData().observe(this, networkState -> {
            if (networkState == NetworkState.RUNNING)
                showProgressDialog();
        });

        viewModel.getAirportLiveData().observe(this, response -> {
            hideProgressDialog();
            host.onDestinationFetched();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        if (destination != null)
            viewModel.onDestinationSelected(destination);
    }

    public interface DestinationSearchHost {
        void onDestinationFetched();
    }
}
