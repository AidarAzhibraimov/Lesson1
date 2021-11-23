package com.geektech.lesson1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ImageView ImageView;
    private Button Btn;
    private EditText etEmail;
    private EditText etPassword;

    private static final int CAMERA = 1000;
    private static final int GALLERY = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ImageView = findViewById(R.id.iv_photo);
        Btn = findViewById(R.id.btn_edit_photo);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.tied_password);

        etEmail.setText(getIntent().getStringExtra("user_key"));
        etPassword.setText(getIntent().getStringExtra("pass_key"));

        ImageView.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        });

        Btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, GALLERY);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && data != null && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView.setImageBitmap(image);
        }

        if (requestCode == GALLERY && data != null && resultCode == RESULT_OK) {
            ImageView.setImageURI(data.getData());
            ImageView.setRotation(90);
        }

    }
}

