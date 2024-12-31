package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    private TextInputEditText editTextUserName;
    private TextInputEditText editTextPassWord;
    private TextInputEditText editTextRPassWord;
    private TextInputLayout txtUserName;
    private TextInputLayout txtPassWord;
    private TextInputLayout txtRPassWord;
    private Button btnRegister;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        myOnClick();


        editTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() == 0){
                        editTextUserName.setError("Khong duoc de trong");
                    }else{
                        editTextUserName.setError(null);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    editTextPassWord.setError("Mat khau khong hop le");
                }else{
                    editTextUserName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() < 6) txtPassWord.setError("Mat khau phai nhieu hon 6 ki tu");
                else txtPassWord.setError(null);
            }
        });

        editTextRPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    editTextRPassWord.setError("Mat khau khong hop le");
                }else{
                    editTextRPassWord.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void myOnClick() {
        btnRegister.setOnClickListener(v -> {
            //lay du lieu tren 3a cai edt
            String username = Objects.requireNonNull(editTextUserName.getText()).toString();
            String password = Objects.requireNonNull(editTextPassWord.getText()).toString();
            String R_password = Objects.requireNonNull(editTextRPassWord.getText()).toString();

            if(username.isEmpty() || password.isEmpty() || R_password.isEmpty()){
//                Toast.makeText(RegisterActivity.this,"Nhap thieu thong tin",Toast.LENGTH_SHORT).show();
                if(username.isEmpty()) editTextUserName.setError("username khong duoc bo trong");
                if(password.isEmpty()) editTextPassWord.setError("password khong duoc bo trong");
                if(R_password.isEmpty()) editTextRPassWord.setError("password khong duoc bo trong");
            }
            else if(!password.equals(R_password)){
                //Toast.makeText(RegisterActivity.this,"Mat khau khong hop le",Toast.LENGTH_SHORT).show();
                txtRPassWord.setError("password khong hop le");
            }
            else{
                Intent intent = new Intent();
                intent.putExtra("user",username);
                intent.putExtra("pass",password);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btnBack.setOnClickListener(v ->{
            finish();//back
        });
    }

    private void initView() {
        editTextPassWord = findViewById(R.id.edt_password_r);
        editTextRPassWord = findViewById(R.id.edt_re_password);
        editTextUserName = findViewById(R.id.edt_username_r);
        txtPassWord = findViewById(R.id.txt_password_r);
        txtUserName = findViewById(R.id.txt_username_r);
        txtRPassWord = findViewById(R.id.txt_re_password);
        btnRegister = findViewById(R.id.btn_register_R);
        btnBack = findViewById(R.id.btn_back);
    }
}