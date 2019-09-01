package com.example.mertyemek.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mertyemek.R;

public class MenuListViewHolder extends RecyclerView.ViewHolder {

    public TextView txtName;
    public TextView txtElement;

    public MenuListViewHolder(View itemView) {

        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtElement = itemView.findViewById(R.id.txtElement);

        //  filmImage = itemView.findViewById(com.example.mertyemek.R.id.filmImageIV);
        //  filmYear = itemView.findViewById(com.example.mertyemek.R.id.filmYearTV);
        //  filmDuration = itemView.findViewById(com.example.mertyemek.R.id.filmDurationTV);
        //  filmStory = itemView.findViewById(com.example.mertyemek.R.id.filmStoryTV);

    }

}
