package com.innova.ecommercestore.ui.mainactivity.fragments.availablecardsfragment;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.innova.ecommercestore.R;
import com.innova.ecommercestore.ui.mainactivity.MainActivity;
import com.innova.ecommercestore.ui.mainactivity.fragments.deliverydetailsfragment.DeliveryDetailsFragment;
import com.innova.ecommercestore.ui.mainactivity.fragments.deliverydetailsfragment.DeliveryDetailsViewModel;

public class AvailableCardsFragment extends Fragment {
    View view;
    private AvailableCardsViewModel viewModel;
    private BottomNavigationView bottomNavigationView;

    public AvailableCardsFragment() {
        // Required empty public constructor
    }

    public static AvailableCardsFragment newInstance(String param1, String param2) {
        AvailableCardsFragment fragment = new AvailableCardsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Button PlaceOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_availablecards, container, false);

        bottomNavigationView = requireActivity().findViewById(R.id.bottom_nav);
        PlaceOrder = view.findViewById(R.id.placeOrder);
        viewModel = new ViewModelProvider(this).get(AvailableCardsViewModel.class);
        viewModel.getUser();

        setUpBtnPlaceOrder();
        return view;
    }

    private void setUpBtnPlaceOrder() {
        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.emptycart(view.getContext());
                viewModel.updateCartItemsinSharedPrefs();
                ((MainActivity)requireActivity()).setUpCartCount(0);
            }
        });
    }
}