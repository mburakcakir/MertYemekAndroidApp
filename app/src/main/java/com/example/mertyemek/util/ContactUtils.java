package com.example.mertyemek.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;

public class ContactUtils {


    static void makeCall(Activity activity, String phone) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:" + phone));
        activity.startActivity(i);
    }

    static void openInView(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

    static void sendEmail(Activity activity) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + "mertyemek@gmail.com"));

        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    static void sendEmailDialog(Activity activity, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + "mertyemek@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void clickContactItems(View view, Activity activity, View getView) {
        switch (view.getId()) {
            case R.id.imgPhone:
                makeCall(  activity, Constants.NUMBER_PHONE);
                break;
            case R.id.imgFacebook:
                openInView(activity, Constants.URL_FACEBOOK);
                break;
            case R.id.imgTwitter:
                openInView(activity, Constants.URL_TWITTER);
                break;
            case R.id.imgInstagram:
                openInView(activity, Constants.URL_INSTAGRAM);
                break;
            case R.id.imgWebsite:
                openInView(activity, Constants.URL_WEBSITE);
                break;
            case R.id.imgEmail:
                emailControl(activity, getView);
        }
    }

    static void emailControl(final Activity activity, final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Email");
        builder.setMessage("Email göndermeden önce taslak oluşturmak ister misiniz?");
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                sendEmail(activity);
            }
        });
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Hızlı bir taslak oluşturun.");
                final View viewInflated = LayoutInflater.from(activity).inflate(R.layout.dialog_email_layout, (ViewGroup) view, false);

                final EditText subject = viewInflated.findViewById(R.id.edit_text_subject);
                final EditText message = viewInflated.findViewById(R.id.edit_text_message);

                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        sendEmailDialog(activity, subject.getText().toString(), message.getText().toString());
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

}