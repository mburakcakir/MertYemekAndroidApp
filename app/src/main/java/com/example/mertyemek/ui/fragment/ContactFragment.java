package com.example.mertyemek.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;
import com.example.mertyemek.util.Utils;


public class ContactFragment extends Fragment implements View.OnClickListener {


    View contactView;
    ImageView imgPhone, imgEmail, imgWebsite, imgFacebook, imgInstagram, imgTwitter;
    EditText mEditTextSubject,mEditTextMessage;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflater = LayoutInflater.from(container.getContext());
        contactView = inflater.inflate(R.layout.fragment_contact, container, false);
        init();
       setOnClickListener();

        return contactView;
    }


    void setOnClickListener()
    {
    imgPhone.setOnClickListener(this);
    imgEmail.setOnClickListener(this);
    imgWebsite.setOnClickListener(this);
    imgTwitter.setOnClickListener(this);
    imgFacebook.setOnClickListener(this);
    imgInstagram.setOnClickListener(this);
    }


    void init() {
        imgPhone = contactView.findViewById(R.id.imgPhone);
        imgEmail = contactView.findViewById(R.id.imgEmail);
        imgWebsite = contactView.findViewById(R.id.imgWebsite);
        imgFacebook = contactView.findViewById(R.id.imgFacebook);
        imgInstagram = contactView.findViewById(R.id.imgInstagram);
        imgTwitter = contactView.findViewById(R.id.imgTwitter);
        mEditTextSubject = contactView.findViewById(R.id.edit_text_subject);
        mEditTextMessage = contactView.findViewById(R.id.edit_text_subject);
    }


    @Override
    public void onClick(View view) {

       Utils.clickContactItems(view,getActivity(),getView());
    }
}
