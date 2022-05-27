package com.innova.ecommercestore.utils;

public interface OnEventChanged<T> {

    void onUnhandledContent(T data);
}
