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
import com.example.mertyemek.di.Constants;
import com.example.mertyemek.di.DynamicConstants;

import java.util.HashMap;


public class GalleryFragment extends Fragment {

    View galleryView;
    private SliderLayout mDemoSlider;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        galleryView = inflater.inflate(R.layout.fragment_gallery_slider, container, false);
        mDemoSlider = galleryView.findViewById(R.id.slider);

        for (int i = 0; i < Constants.galleryImagesList.size(); i++) {
            addDemoSlider(i);
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

    void  addDemoSlider(int i)
    {
        TextSliderView textSliderView = new TextSliderView(getContext());
        // initialize a SliderLayout
        textSliderView
                .description(i+"")
                .image(Constants.galleryImagesList.get(i))
                .setScaleType(BaseSliderView.ScaleType.Fit);


        //add your extra information
        textSliderView.bundle(new Bundle());
        textSliderView.getBundle().putString("extra",i+"");

        mDemoSlider.addSlider(textSliderView);
    }

}
