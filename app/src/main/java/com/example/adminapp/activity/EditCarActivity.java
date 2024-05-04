package com.example.adminapp.activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adminapp.R;
import com.example.adminapp.model.Car;
import com.example.adminapp.model.Firebase;

import java.util.ArrayList;
import java.util.List;

public class EditCarActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int PICK_IMAGE_REQUEST_2 = 2;
    private static final int PICK_IMAGE_REQUEST_3 = 3;
    private EditText edt_namecar, edt_pricecar, edt_descriptioncar;
    private Spinner spinner_typecar, spinner_statuscar, spinner_seats;
    private ImageView img_car1, img_car2, img_car3;
    private AppCompatButton btn_comfirm;
    private Car currentCar;
    private Toolbar toolbar;
    private Uri Image1, Image2, Image3;
    private Firebase mfirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
        currentCar = (Car) getIntent().getSerializableExtra("currentCar");
        Anhxa();
        setdata();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        img_car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser(PICK_IMAGE_REQUEST_1);
            }
        });
        img_car2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser(PICK_IMAGE_REQUEST_2);
            }
        });
        img_car3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser(PICK_IMAGE_REQUEST_3);
            }
        });
        btn_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCarInfo();
            }
        });
    }

    private void setdata() {
        edt_namecar.setText(currentCar.getNamecar());
        edt_pricecar.setText(String.valueOf(currentCar.getPricecar()));
        edt_descriptioncar.setText(currentCar.getDescriptioncar());

        ArrayList<String> typelist = new ArrayList<String>();
        typelist.add("Xe tự lái");
        typelist.add("Xe có tài xế");
        ArrayAdapter adapterspinnertypecar = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typelist);
        spinner_typecar.setAdapter(adapterspinnertypecar);
        if (currentCar.getTypecar().equals("Xe tự lái"))spinner_typecar.setSelection(0);
        else spinner_typecar.setSelection(1);

        ArrayList<String> seatslist = new ArrayList<String>();
        seatslist.add("4");
        seatslist.add("5");
        seatslist.add("6");
        seatslist.add("7");
        ArrayAdapter adapterspinnerseats = new ArrayAdapter(this, android.R.layout.simple_spinner_item, seatslist);
        spinner_seats.setAdapter(adapterspinnerseats);

        ArrayList<String> statuslist = new ArrayList<String>();
        statuslist.add("Còn Trống");
        statuslist.add("Đã thuê");
        ArrayAdapter adapterspinnerstatusar = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statuslist);
        spinner_statuscar.setAdapter(adapterspinnerstatusar);
        if (currentCar.getStatuscar().equals("Còn Trống"))spinner_statuscar.setSelection(0);
        else spinner_statuscar.setSelection(1);

        Glide.with(this).load(currentCar.getImage1()).into(img_car1);
        Glide.with(this).load(currentCar.getImage2()).into(img_car2);
        Glide.with(this).load(currentCar.getImage3()).into(img_car3);

    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbareditcar);
        edt_namecar = findViewById(R.id.edt_namecar);
        edt_pricecar = findViewById(R.id.edt_pricecar);
        edt_descriptioncar = findViewById(R.id.edt_descriptioncar);
        spinner_typecar = findViewById(R.id.spinner_typecar);
        spinner_seats = findViewById(R.id.spinner_seats);
        spinner_statuscar = findViewById(R.id.spinner_statuscar);
        img_car1 = findViewById(R.id.img_car1);
        img_car2 = findViewById(R.id.img_car2);
        img_car3 = findViewById(R.id.img_car3);
        btn_comfirm = findViewById(R.id.btn_confirm);
        mfirebase = new Firebase(this);
    }
    private void openFileChooser(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            switch(requestCode) {
                case PICK_IMAGE_REQUEST_1:
                    Image1 = data.getData();
                    Glide.with(this).load(Image1).into(img_car1);
                    break;
                case PICK_IMAGE_REQUEST_2:
                    Image2 = data.getData();
                    Glide.with(this).load(Image2).into(img_car2);
                    break;
                case PICK_IMAGE_REQUEST_3:
                    Image3 = data.getData();
                    Glide.with(this).load(Image3).into(img_car3);
                    break;
            }
        }
    }

    private void updateCarInfo() {
        String name = edt_namecar.getText().toString().trim();
        String priceString = edt_pricecar.getText().toString().trim();
        String description = edt_descriptioncar.getText().toString().trim();
        String type = spinner_typecar.getSelectedItem().toString();
        Double seats = Double.valueOf(spinner_seats.getSelectedItem().toString());
        String status = spinner_statuscar.getSelectedItem().toString();

        if (name.isEmpty() || priceString.isEmpty() || description.isEmpty() || type.isEmpty() || status.isEmpty()) {
            Toast.makeText(EditCarActivity.this, "Hãy điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceString);

        currentCar.setNamecar(name);
        currentCar.setPricecar(price);
        currentCar.setDescriptioncar(description);
        currentCar.setTypecar(type);
        currentCar.setSeats(seats);
        currentCar.setStatuscar(status);

        List<Uri> list = new ArrayList<>();
        if (Image1 != null) list.add(Image1);
        if (Image2 != null) list.add(Image2);
        if (Image3 != null) list.add(Image3);

        mfirebase.updateCar(currentCar, list,new Firebase.UpdateCarCallback() {
            @Override
            public void onCallback(boolean isSuccess) {
                if(isSuccess) {
                    Toast.makeText(EditCarActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditCarActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}