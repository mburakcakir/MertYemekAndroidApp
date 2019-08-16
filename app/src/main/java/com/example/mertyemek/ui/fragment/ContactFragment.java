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


    void setOnClickListener() {
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

       void emailControl() {
           imgEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Email");
                    builder.setMessage("Email göndermeden önce taslak oluşturmak ister misiniz?");
                    builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                            emailIntent.setData(Uri.parse("mailto:" + "mertyemek@gmail.com"));

                            try {
                                startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getActivity(), "No email clients installed.", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                           builder.setTitle("Hızlı bir taslak oluşturun.");
                            final View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_email_layout, (ViewGroup) getView(), false);

                            final EditText subject = viewInflated.findViewById(R.id.edit_text_subject);
                            final EditText message = viewInflated.findViewById(R.id.edit_text_message);
                            builder.setView(viewInflated);

                            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                Utils.sendEmail(getActivity(),subject.toString(),message.toString());

                                }
                            });
                            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            builder.show();

                        }
                    });
                    builder.show();
                }
            });

       }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case  R.id.imgPhone:
                Utils.makeCall(getActivity(), Constants.NUMBER_PHONE);
                break;
            case R.id.imgFacebook:
                Utils.openInView(getActivity(), Constants.URL_FACEBOOK);
                break;
            case R.id.imgTwitter:
                Utils.openInView(getActivity(), Constants.URL_TWITTER);
                break;
            case R.id.imgInstagram:
                Utils.openInView(getActivity(), Constants.URL_INSTAGRAM);
                break;
            case R.id.imgWebsite:
                Utils.openInView(getActivity(),Constants.URL_WEBSITE);
                break;
            case R.id.imgEmail:
                emailControl();
        }
    }
}
