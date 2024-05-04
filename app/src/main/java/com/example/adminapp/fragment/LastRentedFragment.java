package com.example.adminapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adminapp.R;
import com.example.adminapp.adapter.RcvAllRentedOrder;
import com.example.adminapp.adapter.RcvAllRentingOrders;
import com.example.adminapp.model.Firebase;
import com.example.adminapp.model.Order;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LastRentedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LastRentedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LastRentedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LastRentedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LastRentedFragment newInstance(String param1, String param2) {
        LastRentedFragment fragment = new LastRentedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rcvAllOrders;
    private RcvAllRentedOrder adapter;
    private ArrayList<Order> ordersList;
    private Firebase mfirebase;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_last_rented, container, false);

        Anhxa();
        getDataAllNewOrders();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        getDataAllNewOrders();
    }

    @Override
        public void onResume() {
            super.onResume();
            getDataAllNewOrders();
        }

        private void Anhxa() {
            rcvAllOrders = view.findViewById(R.id.rcv_rentedorders);
            ordersList = new ArrayList<>();
            mfirebase = new Firebase(getContext());
        }
        private void getDataAllNewOrders(){
        mfirebase.getAllRentedOrders(new Firebase.getAllNewOrdersCallback() {
            @Override
            public void onCallback(ArrayList<Order> ordersList) {
                rcvAllOrders.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new RcvAllRentedOrder(getContext(),ordersList);
                rcvAllOrders.setAdapter(adapter);
            }
        });
    }
}