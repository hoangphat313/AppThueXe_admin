package com.example.adminapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adminapp.R;
import com.example.adminapp.model.Firebase;
import com.example.adminapp.model.Order;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThongKeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongKeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
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
    private Firebase mfirebase;
    private BarChart barChart;
    private TextView tv_thunhap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barChart = view.findViewById(R.id.barChart);
        tv_thunhap = view.findViewById(R.id.tv_thunhap);
        mfirebase = new Firebase(getContext());

        mfirebase.getOrdersCountWaitingConfirmation(new Firebase.GetOrderCountCallback() {
            @Override
            public void onCallback(int waitingConfirmationCount) {
                mfirebase.getOrdersCountRenting(new Firebase.GetOrderCountCallback() {
                    @Override
                    public void onCallback(int rentingCount) {
                        mfirebase.getOrdersCountCompleted(new Firebase.GetOrderCountCallback() {
                            @Override
                            public void onCallback(int completedCount) {
                                ArrayList<BarEntry> barEntries = new ArrayList<>();
                                barEntries.add(new BarEntry(0f, waitingConfirmationCount));
                                barEntries.add(new BarEntry(1f, rentingCount));
                                barEntries.add(new BarEntry(2f, completedCount));

                                // Add labels for the X-axis (optional)
                                ArrayList<String> labels = new ArrayList<>();
                                labels.add("Đang chờ");
                                labels.add("Đang thuê");
                                labels.add("Đã hoàn thành");

                                BarDataSet barDataSet = new BarDataSet(barEntries, "Số đơn");
                                BarData barData = new BarData(barDataSet);

                                float barWidth = 1f;
                                barData.setBarWidth(barWidth);
                                barChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value) {
                                        return String.valueOf((int) value);
                                    }
                                });
                                barChart.getAxisLeft().setAxisMinimum(0f);
                                barChart.getAxisLeft().setAxisMaximum(7f);
                                barChart.getAxisLeft().setAxisLineWidth(2f);
                                barChart.getAxisRight().setEnabled(false);

                                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

                                barChart.setData(barData);
                                barChart.invalidate();
                            }
                        });
                    }
                });
            }
        });
        mfirebase.getTotalAmountOfCompletedOrders(new Firebase.GetCompletedOrdersTotalAmountCallback() {
            @Override
            public void onCallback(double totalAmount) {
                tv_thunhap.setText(String.format("%.0f VNĐ", totalAmount));
            }
        });
    }
}