package com.example.mertyemek.ui.holder;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mertyemek.R;

public class ContactHolder extends RecyclerView.ViewHolder {

    View contactView;
    ImageView call, email, fax, face, ins, twit;


    public ContactHolder(View itemView) {
        super(itemView);


        call = itemView.findViewById(R.id.callIV);
        email = itemView.findViewById(R.id.emailIV);
        fax = itemView.findViewById(R.id.websiteIV);
        face = itemView.findViewById(R.id.facebookIV);
        ins = itemView.findViewById(R.id.instagramIV);
        twit = itemView.findViewById(R.id.twittterIV);

    }

    public void init() {

        call = contactView.findViewById(R.id.callIV);
        email = contactView.findViewById(R.id.emailIV);
        fax = contactView.findViewById(R.id.websiteIV);
        face = contactView.findViewById(R.id.facebookIV);
        ins = contactView.findViewById(R.id.instagramIV);
        twit = contactView.findViewById(R.id.twittterIV);
    }
}