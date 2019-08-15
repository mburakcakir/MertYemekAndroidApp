package com.example.mertyemek.ui.holder;

import com.example.mertyemek.R;
import java.util.Arrays;
import java.util.List;

public class GalleryHolder {

    List<Integer> imageHolderList = Arrays.asList(
            R.mipmap.mert5,
            R.mipmap.mert2,
            R.mipmap.mert3,
            R.mipmap.mert4,
            R.mipmap.mert1,
            R.mipmap.mert6,
            R.mipmap.mert7,
            R.mipmap.mert8);


    public List<Integer> getList() {
        return imageHolderList;
    }

    public void Add() {
        imageHolderList.add(R.mipmap.mert5);
    }


}
