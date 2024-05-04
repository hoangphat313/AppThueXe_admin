package com.example.adminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.adminapp.R;
import com.example.adminapp.adapter.VpgImageCarAdapter;
import com.example.adminapp.model.Car;

import java.util.ArrayList;

public class DetailCarActivity extends AppCompatActivity {
    private TextView tv_namecar, tv_pricecar, tv_typecar, tv_statuscar, tv_describebook;
    private ViewPager vpg_detailcar;
    private Car currentCar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_car);
        currentCar = (Car) getIntent().getSerializableExtra("currentCar");
        Anhxa();
        setdata();
    }
    private void setdata() {
        tv_namecar.setText(currentCar.getNamecar());
        tv_pricecar.setText(String.format("%,d", Math.round(currentCar.getPricecar())) + " VNĐ/Ngày");
        tv_typecar.setText("Loại xe: "+ currentCar.getTypecar());
        tv_statuscar.setText("Tình trạng: "+ currentCar.getStatuscar());
        tv_describebook.setText(currentCar.getDescriptioncar());
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add(currentCar.getImage1());
        imageUrls.add(currentCar.getImage2());
        imageUrls.add(currentCar.getImage3());
        VpgImageCarAdapter adapter = new VpgImageCarAdapter(this, imageUrls);
        vpg_detailcar.setAdapter(adapter);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }


    private void Anhxa() {
        toolbar = findViewById(R.id.toolbardetailcar);
        tv_namecar = findViewById(R.id.tv_namecar);
        tv_pricecar = findViewById(R.id.tv_pricecar);
        tv_typecar = findViewById(R.id.tv_typecar);
        tv_statuscar = findViewById(R.id.tv_statuscar);
        tv_describebook = findViewById(R.id.tv_describebook);
        vpg_detailcar = findViewById(R.id.vpg_detailcar);
    }
}