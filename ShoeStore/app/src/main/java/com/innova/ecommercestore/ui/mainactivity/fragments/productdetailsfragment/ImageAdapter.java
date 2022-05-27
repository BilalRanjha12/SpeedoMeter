package com.innova.ecommercestore.ui.mainactivity.fragments.productdetailsfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.innova.ecommercestore.utils.OnExtractDominantColorListener;

import java.util.List;

public class ImageAdapter extends FragmentStateAdapter {

    public static final String IMAGE="product_image";
    private List<String> images;
    private int itemCount;
    private OnExtractDominantColorListener onExtractDominantColorListener;

    public ImageAdapter(@NonNull FragmentActivity fragmentActivity, List<String> images, int itemCount, OnExtractDominantColorListener onExtractDominantColorListener) {
        super(fragmentActivity);
        this.images = images;
        this.itemCount = itemCount;
        this.onExtractDominantColorListener = onExtractDominantColorListener;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        ImageFragment imageFragment= new ImageFragment(onExtractDominantColorListener);
        if(images.size()>position){
            Bundle bundle=new Bundle();
            bundle.putString(IMAGE,images.get(position));

            imageFragment.setArguments(bundle);
        }


        return imageFragment;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }



}
