package com.geektech.lesson1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private TextInputEditText username, password;
    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private Button go;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        go = findViewById(R.id.btn_go);
        image = findViewById(R.id.imageView);
        password = findViewById(R.id.tied_password);
        username = findViewById(R.id.editText);
        tilUserName = findViewById(R.id.et_username);
        tilPassword = findViewById(R.id.ed_password);

        if (BuildConfig.DEBUG) {
            username.setText("aidar.azhibraimov@mail.ru");
            password.setText("aidarKrut");
        }

        String URI = "https://i.pinimg.com/474x/23/ab/a6/23aba60b66ef08174bb7455c4a8a2d2f.jpg";
        Glide.with(this).load(URI).into(image);
        initListener();
    }

    public void initListener() {
        go.setOnClickListener(v -> {
            if (password.getText().toString().length() > 6 && username.getText().toString().length() > 0) {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("user_key", username.getText().toString());
                intent.putExtra("pass_key", password.getText().toString());
                startActivity(intent);
            }

            if (password.getText().toString().length() <= 6) {
                tilPassword.setError(getString(R.string.password_error_text));
            } else {
                tilPassword.setError(null);
            }

            if (username.getText().toString().length() <= 0) {
                tilUserName.setError(getString(R.string.username_error_text));
            } else {
                tilUserName.setError(null);
            }
        });
    }
}
