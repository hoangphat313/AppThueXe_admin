package com.example.adminapp.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminapp.R;
import com.example.adminapp.activity.DetailCarActivity;
import com.example.adminapp.activity.EditCarActivity;
import com.example.adminapp.model.Car;
import com.example.adminapp.model.Firebase;
import com.example.adminapp.model.User;

import java.util.ArrayList;

public class RcvAllCarsAdapter extends RecyclerView.Adapter<RcvAllCarsAdapter.ViewHolder>{
    private ArrayList<Car> carList;
    private Context context;
    private Firebase mfirebase;
    public RcvAllCarsAdapter(Context context, ArrayList<Car> carList) {
        this.carList = carList;
        this.context = context;
        mfirebase = new Firebase(context);
    }

    @Override
    public RcvAllCarsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_allcars, parent, false);
        return new RcvAllCarsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RcvAllCarsAdapter.ViewHolder holder, int position) {
        Car currentCar = carList.get(position);
        holder.namecar.setText(currentCar.getNamecar());
        holder.pricecar.setText(String.format("%,d", Math.round(currentCar.getPricecar())) + " VNĐ/Ngày");
        holder.statuscar.setText(currentCar.getStatuscar());
        Glide.with(context).load(currentCar.getImage1()).into(holder.imagecar);
        holder.btn_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCarActivity.class);
                intent.putExtra("currentCar", currentCar);
                context.startActivity(intent);
            }
        });
        holder.btn_editcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditCarActivity.class);
                intent.putExtra("currentCar", currentCar);
                context.startActivity(intent);
            }
        });
        holder.btn_deletecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(position, currentCar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namecar, pricecar, statuscar;
        Button btn_viewdetails, btn_editcar, btn_deletecar;
        ImageView imagecar;
        public ViewHolder(View itemView) {
            super(itemView);
            namecar = itemView.findViewById(R.id.tv_namecar);
            pricecar = itemView.findViewById(R.id.tv_pricecar);
            statuscar = itemView.findViewById(R.id.tv_statuscar);
            btn_viewdetails = itemView.findViewById(R.id.btn_viewdetails);
            btn_editcar = itemView.findViewById(R.id.btn_editcar);
            btn_deletecar = itemView.findViewById(R.id.btn_deletecar);
            imagecar = itemView.findViewById(R.id.imagecar1);
        }
    }

    private void showDeleteDialog(final int position, Car currentCar){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this car?");
        builder.setPositiveButton("Delete", (dialog, id) -> {
            // Call the deleteCar method
            mfirebase.deleteCar(currentCar, new Firebase.DeleteCarCallback() {
                @Override
                public void onCallback(boolean isSuccess) {
                    if (isSuccess) {
                        Toast.makeText(context, "Car deleted successfully", Toast.LENGTH_SHORT).show();
                        carList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(context, "Failed to delete car", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
        builder.create().show();
    }
}
