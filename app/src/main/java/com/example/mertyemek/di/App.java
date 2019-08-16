package com.example.mertyemek.di;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.mertyemek.R;
import com.example.mertyemek.ui.activity.BaseActivity;
import com.example.mertyemek.ui.activity.MainActivity;
import com.example.mertyemek.ui.fragment.GalleryFragment;
import com.example.mertyemek.ui.fragment.MenuFragment;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;


public class App extends Application {
    MenuFragment menuFragment = new MenuFragment();
    GalleryFragment galleryFragment = new GalleryFragment();


    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new OnesignalHandler(getApplicationContext()))
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();



    }


}


