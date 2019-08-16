package com.example.mertyemek.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

// Created Fragments
import com.example.mertyemek.ui.fragment.BaseFragment;
import com.example.mertyemek.ui.fragment.ContactFragment;
import com.example.mertyemek.ui.fragment.GalleryFragment;
import com.example.mertyemek.ui.fragment.ListFragment;
import com.example.mertyemek.ui.fragment.LocationFragment;
import com.example.mertyemek.ui.fragment.MenuFragment;
import com.example.mertyemek.R;

public class BaseActivity extends AppCompatActivity {


    MenuFragment menuFragment = new MenuFragment();
    BaseFragment baseFragment = new BaseFragment();
    LocationFragment locationFragment = new LocationFragment();
    ListFragment listFragment = new ListFragment();
    GalleryFragment galleryFragment = new GalleryFragment();
    ContactFragment contactFragment = new ContactFragment();
    Toolbar toolbar;

   public void changeFragment(Fragment fragment,String text) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle(text);

    }

}
