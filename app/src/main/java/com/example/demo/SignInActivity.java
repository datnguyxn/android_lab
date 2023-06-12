package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private TextView tvSignIn;
    private EditText edtEmail;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();

        btnSignIn.setOnClickListener(v -> {
            String edtEmailText = edtEmail.getText().toString();
            if (edtEmailText.isEmpty()) {
                edtEmail.setError("Email is empty");
                edtEmail.setFocusable(true);
                return;
            }

            Intent intent = new Intent(SignInActivity.this, SecondActivity.class);
            intent.putExtra("email", edtEmailText);
            startActivityForResult(intent, 1);
        });
    }
    public void init() {
        tvSignIn = findViewById(R.id.tvSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        btnSignIn = findViewById(R.id.btnSignIn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            String name = data.getStringExtra("name");
            tvSignIn.setText("Xin chào, " + name);
            edtEmail.setText(name);
            btnSignIn.setVisibility(Button.GONE);
        }
    }
}