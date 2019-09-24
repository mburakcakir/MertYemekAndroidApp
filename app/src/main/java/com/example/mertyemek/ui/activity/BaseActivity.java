package com.example.mertyemek.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

// Created Fragments
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.model.MenuModel;
import com.example.mertyemek.ui.fragment.BaseFragment;
import com.example.mertyemek.ui.fragment.ContactFragment;
import com.example.mertyemek.ui.fragment.GalleryFragment;
import com.example.mertyemek.ui.fragment.ListFragment;
import com.example.mertyemek.ui.fragment.LocationFragment;
import com.example.mertyemek.ui.fragment.MenuFragment;
import com.example.mertyemek.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    MenuFragment menuFragment = new MenuFragment();
    LocationFragment locationFragment = new LocationFragment();
    ListFragment listFragment = new ListFragment();
    GalleryFragment galleryFragment = new GalleryFragment();
    ContactFragment contactFragment = new ContactFragment();
    Toolbar toolbar;

    //  BaseFragment baseFragment = new BaseFragment();

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
