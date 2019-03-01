package com.abhinav.klmdemoapp.base;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.abhinav.klmdemoapp.R;

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    public void showToastLong(CharSequence message) {
        ((BaseActivity) getActivity()).showToastLong(message);
    }

    public void showToastShort(CharSequence message) {
        ((BaseActivity) getActivity()).showToastShort(message);
    }

    public void showProgressDialog() {
        ((BaseActivity) getActivity()).showProgressDialog();
    }

    public void hideProgressDialog() {
        ((BaseActivity) getActivity()).hideProgressDialog();
    }

    public void showUnknownRetrofitError() {
        ((BaseActivity) getActivity()).showUnknownRetrofitError();
    }

    public void hideKeyboard() {
        ((BaseActivity) getActivity()).hideKeyboard();
    }

    public void showNoNetworkError() {
        ((BaseActivity) getActivity()).showNoNetworkError();
    }

    public void popFragment() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void onStop() {
        hideProgressDialog();
        super.onStop();
    }

    public void onFailure(FailureResponse failureResponse) {
        hideProgressDialog();
        showToastShort(failureResponse.getErrorMessage());
        Log.e(TAG, " onFailure: " + failureResponse.getErrorMessage() + "   " + failureResponse.getErrorCode());

    }

    public void onNetworkStateChanged(NetworkState networkState) {
        if (networkState == NetworkState.RUNNING)
            showProgressDialog();
        else hideProgressDialog();
    }

    public void shakeView(View view) {
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.anim_shake);
        view.startAnimation(shake);
    }

    public void updateTitle(CharSequence title) {
//        ((BaseActivity) getActivity()).updateTitle(title);
    }
}
