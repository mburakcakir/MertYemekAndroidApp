package com.example.mertyemek.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OneSignal;


public class MainActivity extends BaseActivity {
    private TextView mTextMessage;

    BottomNavigationView bottomNavigationView;

    Button buton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_menu:
               //     mTextMessage.setText(R.string.menu);
                    changeFragment(menuFragment, Constants.MENU_TEXT);
                    return true;

                case R.id.navigation_location:
                    changeFragment(locationFragment,Constants.LOCATION_TEXT);
                    return true;

                case R.id.navigation_list:
                    changeFragment(listFragment,Constants.LIST_TEXT);
                    return true;

                case R.id.navigation_gallery:
                    changeFragment(galleryFragment,Constants.GALLERY_TEXT);
                    return true;

                case R.id.navigation_contact:
                    changeFragment(contactFragment,Constants.CONTACT_TEXT);
                    return true;


            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        bottomNavigationView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        changeFragment(baseFragment,Constants.BASE_TEXT);

        buton = findViewById(R.id.whatsappBT);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://api.whatsapp.com/send?phone="+"905071625899";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }



}