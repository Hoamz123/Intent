package com.example.assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private TextInputEditText editTextUserName;
    private TextInputLayout txtUserName;
    private TextInputLayout txtPassWord;
    private CheckBox cb_remember;
    private TextInputEditText editTextPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private String usernameRegister;
    private String passwordRegister;
    private SharedPreferences sharedPreferences; //che do private ( chi co ung dung nay moi co the mo duoc file nay



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        onMyClick();

        //method reset thanh nhap user , pass
        resetInPut();

        //kiem tra xem nguoi dung co luu vao share hay khong
        sharedPreferences = getSharedPreferences("INFORMATION",MODE_PRIVATE);
        boolean isCheckedRemember = sharedPreferences.getBoolean("isCheckedRemember",false);
        if(isCheckedRemember){
            //neu nguoi dung da luu username va password truoc do vao //SharedPreferences
            String usernameSaved = sharedPreferences.getString("userLogin","");
            String passwordSaved = sharedPreferences.getString("passwordLogin","");
            //đổ dữ liệu nên hai cái edit text
            editTextUserName.setText(usernameSaved);
            editTextPassWord.setText(passwordSaved);
            cb_remember.setChecked(true);

            //gan cho 2 bien usernameRegister va passwordRegister
            usernameRegister = usernameSaved;
            passwordRegister = passwordSaved;
        }
    }

    private void resetInPut() {
        editTextUserName.setText("");
        editTextPassWord.setText("");
    }

    private void onMyClick() {
        btnRegister.setOnClickListener(v ->{
            //chuyen sang ben register
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            myActivityResult.launch(intent);
        });

        btnLogin.setOnClickListener(v ->{
            String usernameLogin = Objects.requireNonNull(editTextUserName.getText()).toString();
            String passwordLogin = Objects.requireNonNull(editTextPassWord.getText()).toString();

            if(!usernameRegister.isEmpty() && !passwordRegister.isEmpty() && usernameLogin.equals(usernameRegister) && passwordLogin.equals(passwordRegister)){
                //dang nhap thanh cong

                //check remember
                if(cb_remember.isChecked()){
                    //user chon nho mat khau
                    sharedPreferences = getSharedPreferences("INFORMATION",MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userLogin",usernameLogin);//day du lieu vao theo dang key - value
                    editor.putString("passwordLogin",passwordLogin);
                    editor.putBoolean("isCheckedRemember",cb_remember.isChecked());//luu lai xem lan trc user co luu hay khong
                    editor.apply();//luu thong tin vao file
                }
                else{
                    //khi user bo luu
                    sharedPreferences = getSharedPreferences("INFORMATION",MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                }

                Toast.makeText(LoginActivity.this,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                resetInPut();
                startActivity(myIntent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this,"Ten dang nhap hoac mat khau khong hop le",Toast.LENGTH_SHORT).show();
                resetInPut();
            }
        });
    }
    private void initView() {
        editTextUserName = findViewById(R.id.edt_username);
        editTextPassWord = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        txtPassWord = findViewById(R.id.txt_password);
        txtUserName = findViewById(R.id.txt_username);
        cb_remember = findViewById(R.id.cb_remember);
    }
    private final ActivityResultLauncher<Intent> myActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        //noi xu li du lieu tra ve tu ben Register
        if(result.getResultCode() == RESULT_OK){
            //dung code
            Intent dataOfUser = result.getData();
            assert dataOfUser != null;
            usernameRegister = dataOfUser.getStringExtra("user");
            passwordRegister = dataOfUser.getStringExtra("pass");
        }
    });
}