package com.abhinav.klmdemoapp.base;

import com.abhinav.klmdemoapp.R;
import com.abhinav.klmdemoapp.utils.ResourceUtil;

public class FailureResponse {

    private int errorCode;
    private CharSequence errorMessage;

    public FailureResponse() {
    }

    public FailureResponse(int errorCode, CharSequence errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public CharSequence getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static FailureResponse getGenericError() {
        FailureResponse response = new FailureResponse();
        response.errorCode = 2727;
        response.errorMessage = ResourceUtil.getInstance().getString(R.string.txt_something_went_wrong);
        return response;
    }
}

