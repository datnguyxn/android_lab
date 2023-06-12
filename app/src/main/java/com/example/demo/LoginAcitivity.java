package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAcitivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private CheckBox cbKeep;
    private TextView tvResetPassword;
    private MaterialButton btnLogin;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-6])(?=.*[a-z])(?=.*[A-Z])";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);

        init();

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String currentUsername = edtUsername.getText().toString();
                if (TextUtils.isEmpty(currentUsername)) {
                    edtUsername.setError("username can not be empty!!");
                    edtUsername.setFocusable(true);
                    return;
                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String currentPassword = edtPassword.getText().toString();
                if (TextUtils.isEmpty(currentPassword)) {
                    edtPassword.setError("password can not be empty!!");
                    edtPassword.setFocusable(true);
                    return;
                }
            }
        });


        btnLogin.setOnClickListener(view -> {
            String currentUsername = edtUsername.getText().toString();
            String currentPassword = edtPassword.getText().toString();
            if (TextUtils.isEmpty(currentUsername) || TextUtils.isEmpty(currentPassword)) {
                makeToast("Vui lòng nhập username hoặc password");
            }

            if (isAdmin(currentUsername, currentPassword)) {
                makeToast("Đăng nhập thành công");
            }

            if(isValid(currentPassword) == false) {
                makeToast("Mật khẩu không đúng kêu cầu");
            };

        });

        tvResetPassword.setOnClickListener(view -> {
            String currentUsername = edtUsername.getText().toString();
            if (TextUtils.isEmpty(currentUsername)) {
                makeToast("Vui lòng nhập lại username");
            } else if (currentUsername.equals("admin")) {
                makeToast("Reset mật khẩu thành công");
            }
        });

    }

    public boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isAdmin(String username, String password) {
        if (username.equals("admin") && password.equals("admin1234")) {
            return true;
        }
        return false;
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void init() {
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        cbKeep = (CheckBox) findViewById(R.id.cb_keep);
        tvResetPassword = (TextView) findViewById(R.id.tv_reset_password);
        btnLogin = (MaterialButton) findViewById(R.id.btn_sign_in);
    }
}