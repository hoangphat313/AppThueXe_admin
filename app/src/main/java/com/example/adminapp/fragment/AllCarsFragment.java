package com.example.adminapp.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adminapp.R;
import com.example.adminapp.activity.AddCarActivity;
import com.example.adminapp.adapter.RcvAllCarsAdapter;
import com.example.adminapp.adapter.RcvAllUserAdapter;
import com.example.adminapp.model.Car;
import com.example.adminapp.model.Firebase;
import com.example.adminapp.model.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllCarsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllCarsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllCarsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllCarsFragment newInstance(String param1, String param2) {
        AllCarsFragment fragment = new AllCarsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView rcvAllCars;
    private RcvAllCarsAdapter adapter;
    private ArrayList<Car> carList;
    private Firebase mfirebase;
    private View view;
    private Button btn_add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_cars, container, false);
        Anhxa();
        getDataAllCar();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddCarActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDataAllCar();
    }

    private void Anhxa() {
        btn_add = view.findViewById(R.id.btn_addcar);
        rcvAllCars = view.findViewById(R.id.rcv_allcars);
        carList = new ArrayList<>();
        mfirebase = new Firebase(getContext());
    }

    private void getDataAllCar(){
        mfirebase.getAllCars(new Firebase.getAllCarsCallback() {
            @Override
            public void onCallback(boolean isSuccess, ArrayList<Car> carList) {
                if(isSuccess) {
                    rcvAllCars.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new RcvAllCarsAdapter(getContext(),carList);
                    rcvAllCars.setAdapter(adapter);
                } else {
                    // Handle error here
                }
            }
        });
    }
}