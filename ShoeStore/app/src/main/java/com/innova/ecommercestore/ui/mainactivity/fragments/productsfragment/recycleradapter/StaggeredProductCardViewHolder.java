package com.innova.ecommercestore.ui.mainactivity.fragments.productsfragment.recycleradapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innova.ecommercestore.R;
import com.innova.ecommercestore.models.Product;
import com.innova.ecommercestore.utils.FavoritedProductListener;
import com.innova.ecommercestore.utils.OnNavigateToProductDetails;
import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class StaggeredProductCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public MaterialCardView materialCardView;
    public ImageView productImage;
    public TextView productName;
    public TextView productPrice;
    public Product product;

    public static String productDetails="com.digitalskies.virtualclothierdemo.staggeredgridlayout.StaggeredProductCardViewHolder.product";
    public final AppCompatImageView imgFavorite;
    private FavoritedProductListener favoritedProductListener;
    private OnNavigateToProductDetails onNavigateToProductDetails;

    StaggeredProductCardViewHolder(@NonNull View itemView, FavoritedProductListener favoritedProductListener, OnNavigateToProductDetails onNavigateToProductDetails) {
        super(itemView);
        productImage = itemView.findViewById(R.id.item_product_image);
        productName = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.tv_product_price);

        imgFavorite = itemView.findViewById(R.id.iv_favorite);
        this.favoritedProductListener = favoritedProductListener;
        this.onNavigateToProductDetails = onNavigateToProductDetails;
        product=new Product();

        productName.setOnClickListener(this);

        productImage.setOnClickListener(this);

        productPrice.setOnClickListener(this);





        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                favoritedProductListener.onProductFavorited(StaggeredProductCardViewHolder.this);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(productDetails,product);


       /* ProductFragmentDirections.ActionProductFragmentToProductDetailsFragment  actionProductFragmentToProductDetailsFragment
                =ProductFragmentDirections.actionProductFragmentToProductDetailsFragment(product);


        Navigation.findNavController(view).navigate(actionProductFragmentToProductDetailsFragment);*/




        onNavigateToProductDetails.onNavigateToProductDetails(product);







    }
}
