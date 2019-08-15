package com.example.mertyemek.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// image slider library
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.mertyemek.R;
import java.util.HashMap;


public class GalleryFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    View galleryView;
    private SliderLayout mDemoSlider;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        galleryView = inflater.inflate(R.layout.fragment_gallery_slider, container, false);

        mDemoSlider = galleryView.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        /*
        for(int i=0; i<galleryHolder.getList().size();i++) {
            file_maps.put("Mert Yemek", galleryHolder.getList().get(i));
        }
        */

        file_maps.put("Mert Yemek 1", R.mipmap.mert1);
        file_maps.put("Mert Yemek 2", R.mipmap.mert2);
        file_maps.put("Mert Yemek 3", R.mipmap.mert3);
        file_maps.put("Mert Yemek 4", R.mipmap.mert4);
        file_maps.put("Mert Yemek 5", R.mipmap.mert5);
        file_maps.put("Mert Yemek 6", R.mipmap.mert6);
        file_maps.put("Mert Yemek 7", R.mipmap.mert7);
        file_maps.put("Mert Yemek 8", R.mipmap.mert8);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(1000);


        return galleryView;
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
