package com.abhinav.klmdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.abhinav.klmdemoapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChoiceFragment extends BaseFragment {

    private static final String TITLE = "KLM-DEMO";
    @BindView(R.id.btn_search_flight_on_routes)
    Button btnSearchFlightOnRoutes;
    @BindView(R.id.btn_search_flight_status)
    Button btnSearchFlightStatus;
    Unbinder unbinder;
    @BindView(R.id.btn_search_destination)
    Button btnSearchDestination;
    private ChoiceFragmentHost host;

    public static ChoiceFragment getInstance() {
        return new ChoiceFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ChoiceFragmentHost)
            host = (ChoiceFragmentHost) context;
        else throw new IllegalStateException("Host must impl ChoiceFragmentHost");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        updateTitle(TITLE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animateBtn();
    }

    private void animateBtn() {
        btnSearchFlightOnRoutes.animate()
                .setStartDelay(300)
                .alpha(1)
                .translationY(0)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(700);

        btnSearchFlightStatus.animate()
                .setStartDelay(500)
                .alpha(1)
                .setDuration(700);

        btnSearchDestination.animate()
                .setStartDelay(300)
                .alpha(1)
                .translationY(0)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(700);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_search_flight_on_routes, R.id.btn_search_flight_status, R.id.btn_search_destination})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search_flight_on_routes:
                host.showSearchFlightOnRoutesFragment();
                break;
            case R.id.btn_search_flight_status:
                host.showSearchFlightStatusFragment();
                break;
            case R.id.btn_search_destination:
                host.showSearchDestinationFragment();
                break;
        }
    }

    public interface ChoiceFragmentHost {
        void showSearchFlightOnRoutesFragment();

        void showSearchFlightStatusFragment();

        void showSearchDestinationFragment();
    }
}
