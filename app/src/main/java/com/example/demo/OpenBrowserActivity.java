package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class OpenBrowserActivity extends AppCompatActivity {

    private EditText edtUrl;
    private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_browser);

        init();

        btnOpen.setOnClickListener(v -> {
            String url = edtUrl.getText().toString();
            if (url.isEmpty()) {
                edtUrl.setError("Url is empty");
                edtUrl.setFocusable(true);
                return;
            }
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
    });
    }

    private void init() {
        edtUrl = findViewById(R.id.edtUrl);
        btnOpen = findViewById(R.id.btnOpen);
    }
}