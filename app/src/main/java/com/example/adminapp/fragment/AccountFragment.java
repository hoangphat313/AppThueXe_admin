package com.example.adminapp.fragment;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adminapp.R;
import com.example.adminapp.model.Admin;
import com.example.adminapp.model.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String madminID;
    public AccountFragment() {
        // Required empty public constructor
    }
    public AccountFragment(String adminID) {
        // Required empty public constructor
        this.madminID = adminID;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
    private static final int PICK_IMAGE_REQUEST = 1;
    private View view;
    private CircleImageView mImgAvatar;
    private EditText mEdtUsername;
    private TextView mTvEmail;
    private EditText mEdtPhoneNumber;
    private Button mBtnUpdate;
    private Admin madmin;
    private Firebase mfirebase;
    private Uri mImageUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        Anhxa();
        setDataUser();

        mImgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        return view;
    }

    private void Anhxa() {
        mImgAvatar = view.findViewById(R.id.id_img_avatar);
        mEdtUsername = view.findViewById(R.id.edt_username);
        mTvEmail = view.findViewById(R.id.edt_email);
        mEdtPhoneNumber = view.findViewById(R.id.edt_phonenumber);
        mBtnUpdate = view.findViewById(R.id.btn_update);
        mfirebase = new Firebase(getContext());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
                mImageUri = data.getData();
                Glide.with(this).load(mImageUri).into(mImgAvatar);
        }
    }
    private void setDataUser(){
        mfirebase.setDataUser(madminID, new Firebase.SetDataUserCallback() {
            @Override
            public void onCallback(boolean isSuccess, Admin admin) {
                if(isSuccess) {
                    madmin = admin;
                    mTvEmail.setText(madmin.getEmail());
                    if (madmin.getImage() != null) {
                        Glide.with(getContext()).load(madmin.getImage()).into(mImgAvatar);
                    }
                    if (madmin.getUsername() != null) mEdtUsername.setText(madmin.getUsername());
                    else mEdtUsername.setHint("Vui lòng nhập tên");
                    if (madmin.getPhonenumber() != null) mEdtPhoneNumber.setText(madmin.getPhonenumber());
                    else mEdtPhoneNumber.setHint("Vui lòng nhập SĐT");
                } else {
                    Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateUser() {
        final String username = mEdtUsername.getText().toString().trim();
        final String phonenumber = mEdtPhoneNumber.getText().toString().trim();
        final String email = mTvEmail.getText().toString().trim();
        if (mImageUri == null){
            mfirebase.updateUser(madminID ,email, username, phonenumber, new Firebase.UpdateUserCallback() {
                @Override
                public void onCallback(boolean isSuccess) {
                    if(isSuccess) {
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        setDataUser();
                    } else {
                        Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            mfirebase.updateInforAndImageUser(madminID ,email, username, phonenumber, mImageUri, new Firebase.UpdateUserCallback() {
                @Override
                public void onCallback(boolean isSuccess) {
                    if(isSuccess) {
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        setDataUser();
                    } else {
                        Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}