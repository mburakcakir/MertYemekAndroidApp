package com.example.mertyemek.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;
import com.example.mertyemek.di.DynamicConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OneSignal;


public class MainActivity extends BaseActivity {
    private TextView mTextMessage;

    Button btnWhatsApp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_menu:
               //     mTextMessage.setText(R.string.menu);
                    changeFragment(menuFragment, getString(R.string.textMenu));
                    return true;

                case R.id.navigation_location:
                    changeFragment(locationFragment,getString(R.string.textLocation));
                    return true;

                case R.id.navigation_list:
                    changeFragment(listFragment,getString(R.string.textList));
                    return true;

                case R.id.navigation_gallery:
                    changeFragment(galleryFragment,getString(R.string.textGallery));
                    return true;

                case R.id.navigation_contact:
                    changeFragment(contactFragment,getString(R.string.textContact));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeFragment(menuFragment,getString(R.string.textWelcome));

        btnWhatsApp = findViewById(R.id.btnWhatsApp);

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://api.whatsapp.com/send?phone="+ Constants.NUMBER_WHATSAPP;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



        if(!DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals(""))
        {

            if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("menu"))
            {
                changeFragment(menuFragment,"");
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("location"))
            {
                changeFragment(locationFragment,"");
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("list"))
            {
                changeFragment(listFragment,"");
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("contact"))
            {
                changeFragment(galleryFragment,"");
            }
            else {
             //   DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION="";
                changeFragment(contactFragment,"");
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
