package com.abhinav.klmdemoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.inputmethod.InputMethodManager;

import com.abhinav.klmdemoapp.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {
    public static void hideKeyboard(Activity context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        try {
            Properties properties = new Properties();
            AssetManager assetManager = App.getAppContext().getAssets();
            InputStream inputStream = assetManager.open("destination_images.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        }catch (IOException e){
            e.fillInStackTrace();
        }
        return null;
    }
}
