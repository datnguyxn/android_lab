package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tvName;
    private EditText edtName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        Intent intent = getIntent();
        if (intent != null) {
            String email = intent.getStringExtra("email");
            tvName.setText("Xin chào, " + email + ". Vui lòng nhập tên");
        }

        btnSave.setOnClickListener(view -> {
            String edtNameText = edtName.getText().toString();
            if (edtNameText.isEmpty()) {
                edtName.setError("Name is empty");
                edtName.setFocusable(true);
                return;
            }

            Intent intent1 = new Intent();
            intent1.putExtra("name", edtNameText);
            setResult(RESULT_OK, intent1);
            finish();
        });
    }

    private void init() {
        tvName = findViewById(R.id.tvName);
        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);
    }
}