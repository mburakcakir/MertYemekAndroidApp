package com.example.mertyemek.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mertyemek.R;


public class ContactFragment extends Fragment {
    public View contactView;
    public ImageView call, email, web, face, ins, twit;
    EditText mEditTextSubject,mEditTextMessage;
    String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater = LayoutInflater.from(container.getContext());
        contactView = inflater.inflate(R.layout.fragment_contact, container, false);

        starting();
        return contactView;
    }
    void starting() {

        init();
        socialMedia();
        call();
        emailControl();
    }


    void init() {

        call = contactView.findViewById(R.id.callIV);
        email = contactView.findViewById(R.id.emailIV);
        web = contactView.findViewById(R.id.websiteIV);
        face = contactView.findViewById(R.id.facebookIV);
        ins = contactView.findViewById(R.id.instagramIV);
        twit = contactView.findViewById(R.id.twittterIV);

        mEditTextSubject = contactView.findViewById(R.id.edit_text_subject);
        mEditTextMessage = contactView.findViewById(R.id.edit_text_subject);
    }

    void socialMedia() {


        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://www.instagram.com/mertyemek/?hl=tr";
                setIntent();

            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               url = "https://www.facebook.com/mert.yemek.54";
               setIntent();
            }
        });

        twit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 url = "https://twitter.com/Verisun";
                setIntent();
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://mertyemek.net";
                setIntent();
            }
        });
    }

    void call() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:905071625899"));
                startActivity(i);
            }

        });
       }

       void emailControl() {
            email.setOnClickListener(new View.OnClickListener() {
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

                                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                    emailIntent.setData(Uri.parse("mailto:" + "mertyemek@gmail.com"));
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());

                                    try {
                                        startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                                    } catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(getActivity(), "No email clients installed.", Toast.LENGTH_SHORT).show();
                                    }

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


/*
       private void sendEmail() {
      //  String recipient = mEditTextTo.getText().toString();
           String recipient = "mertyemek@gmail.com";

           String subject = mEditTextSubject.getText().toString();
           String message = mEditTextMessage.getText().toString();

           Intent intent = new Intent(Intent.ACTION_SEND);
           intent.putExtra(Intent.EXTRA_EMAIL,recipient);
           intent.putExtra(Intent.EXTRA_SUBJECT,subject);
           intent.putExtra(Intent.EXTRA_TEXT,message);

           intent.setType("message/rfc822");
           startActivity(Intent.createChooser(intent,"Choose an email client"));


       }
 */
       void setIntent() {
           Intent i = new Intent(Intent.ACTION_VIEW);
           i.setData(Uri.parse(url));
           startActivity(i);

       }
}
