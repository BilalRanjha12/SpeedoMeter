package com.innova.ecommercestore.utils;

import androidx.recyclerview.widget.RecyclerView;

public interface FavoritedProductListener {

    void onProductFavorited(RecyclerView.ViewHolder holder);

    void onFavoritedProductUpdatedToDatabase();
}
