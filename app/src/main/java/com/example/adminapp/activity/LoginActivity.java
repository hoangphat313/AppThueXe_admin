package com.example.adminapp.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.R;
import com.example.adminapp.model.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_email, edt_password;
    AppCompatButton btn_login;
    Firebase mfirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Anhxa();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                mfirebase.signIn(email, password, new Firebase.SignInCallback() {
                    @Override
                    public void onCallback(boolean isSuccess, String adminID) {
                        if (isSuccess) {
                            gotoTrangChuActivity(adminID);
                        } else {
                            Toast.makeText(LoginActivity.this, "Tài khoàn không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void Anhxa() {
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        mfirebase = new Firebase(this);
    }

    private void gotoTrangChuActivity(String adminID) {
        String adminEmail = edt_email.getText().toString();
        Intent i = new Intent(LoginActivity.this, TrangChuActivity.class);
        i.putExtra("adminID", adminID);
        i.putExtra("adminEmail", adminEmail);
        startActivity(i);
    }
}