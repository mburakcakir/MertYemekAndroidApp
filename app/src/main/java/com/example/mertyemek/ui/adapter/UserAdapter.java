package com.example.mertyemek.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mertyemek.model.UserModel;
import com.example.mertyemek.R;
import com.example.mertyemek.ui.holder.UserViewHolder;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserModel> userModelList;

    public UserAdapter(List<UserModel> dataList) {
        this.userModelList = dataList;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_layout, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        holder.filmName.setText(userModelList.get(position).getTitle());

        holder.filmStory.setText(userModelList.get(position).getBody());
    }


    @Override

    public int getItemCount() {

        return userModelList.size();

    }



    }



