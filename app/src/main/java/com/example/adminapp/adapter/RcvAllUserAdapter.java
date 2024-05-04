package com.example.adminapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.R;
import com.example.adminapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class RcvAllUserAdapter extends RecyclerView.Adapter<RcvAllUserAdapter.UserViewHolder>{
    private ArrayList<User> userList;

    public RcvAllUserAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_allusers, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User currentUser = userList.get(position);
        holder.userID.setText("UserID: "+currentUser.getUserID());
        holder.numberphone.setText("Sđt: "+currentUser.getNumberphone());
        // Xử lý sự kiện click vào detail_user và delete_user nếu cần.
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userID;
        TextView numberphone;
        ImageButton deleteUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.userID);
            numberphone = itemView.findViewById(R.id.usernumberphone);
            deleteUser = itemView.findViewById(R.id.delete_user);
        }
    }

}
