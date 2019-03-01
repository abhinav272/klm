package com.abhinav.klmdemoapp.utils;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import com.abhinav.klmdemoapp.App;

import java.util.Arrays;
import java.util.List;

public class ResourceUtil {

    private static ResourceUtil instance;

    private ResourceUtil() {

    }

    public synchronized static ResourceUtil getInstance() {
        if (instance == null) {
            instance = new ResourceUtil();
        }

        return instance;
    }


    public Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(App.getAppContext(), resId);
    }

    public int getColor(int colorResId) {
        return ContextCompat.getColor(App.getAppContext(), colorResId);
    }

    public String getString(int resId) {
        return App.getAppContext().getString(resId);
    }

    public List<String> getStringArray(int resId) {
        String[] stringArray = App.getAppContext().getResources().getStringArray(resId);
        return Arrays.asList(stringArray);
    }

    public Typeface getFont(int font) {
        return ResourcesCompat.getFont(App.getAppContext(), font);
    }
}

