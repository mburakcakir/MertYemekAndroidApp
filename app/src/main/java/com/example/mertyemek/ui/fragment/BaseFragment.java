package com.example.mertyemek.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.mertyemek.R;


public class BaseFragment extends Fragment {

    View baseView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        baseView = inflater.inflate(R.layout.fragment_base, container, false);
        return baseView;
    }


}
