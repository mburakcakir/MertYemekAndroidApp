package com.example.mertyemek.ui.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.mertyemek.R;
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.util.ContactUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
  //  private TextView mTextMessage;
  //  @BindView(R.id.btnWhatsApp) Button btnWhatsApp;
    Button btnWhatsApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(menuFragment,getString(R.string.textWelcome));
        //  mTextMessage = findViewById(R.id.message);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnWhatsApp = findViewById(R.id.btnWhatsApp);

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new ContactUtils().connectWhatsapp(MainActivity.this);
            }
        });

    chooseNotificationFragment();

    }

    public void chooseNotificationFragment(){

        if(!DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals(""))
        {

            if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("menu"))
            {
                changeFragment(menuFragment, getString(R.string.textMenu));
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("location"))
            {
                changeFragment(locationFragment,getString(R.string.textLocation));
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("list"))
            {
                changeFragment(listFragment,getString(R.string.textList));
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("gallery"))
            {
                changeFragment(galleryFragment,getString(R.string.textGallery));
            }
            else if(DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION.equals("contact")){
                //   DynamicConstants.MOVE_FRAGMENT_NAME_BY_NOTIFICATION="";
                changeFragment(contactFragment,getString(R.string.textContact));
            }
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

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
}
