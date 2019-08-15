package com.example.mertyemek.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mertyemek.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView filmName;
    public ImageView filmImage;
    public TextView filmYear;
    public TextView filmDuration;
    public TextView filmDirector;
    public TextView filmStory;

    public UserViewHolder(View itemView) {

        super(itemView);

        filmName = itemView.findViewById(R.id.filmTitleTV);
        filmStory = itemView.findViewById(R.id.filmStoryTV);


        //  filmImage = itemView.findViewById(com.example.mertyemek.R.id.filmImageIV);
        //  filmYear = itemView.findViewById(com.example.mertyemek.R.id.filmYearTV);
        //  filmDuration = itemView.findViewById(com.example.mertyemek.R.id.filmDurationTV);
        //  filmStory = itemView.findViewById(com.example.mertyemek.R.id.filmStoryTV);

    }

}
