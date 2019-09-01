package com.example.mertyemek.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mertyemek.R;
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.ui.holder.MenuListViewHolder;

public class MenuAdapter extends RecyclerView.Adapter<MenuListViewHolder> {

    @NonNull
    @Override
    public MenuListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_menu_listt, parent, false);
        return new MenuListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListViewHolder holder, int position) {

        holder.txtName.setText(DynamicConstants.MENU_MODEL_LIST.get(position).getMenuName());
        holder.txtElement.setText(DynamicConstants.MENU_MODEL_LIST.get(position).getMenuList().toString().trim());
    }

    @Override
    public int getItemCount() {
        return DynamicConstants.MENU_MODEL_LIST.size();
    }
}
