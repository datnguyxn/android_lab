package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class bai4 extends AppCompatActivity {

    private EditText editDollar, editEuro, editVnd;
    private Button btnClear, btnConvert;

    private int dollar = 0;
    private final double euro = 0.92;
    private final double vnd = 23.417;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        init();
        editDollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String currentDollar = editDollar.getText().toString();
                if (TextUtils.isEmpty(currentDollar) || Double.parseDouble(currentDollar) < 0) {
                    editDollar.setError("Dollar have to positive!");
                    editDollar.setFocusable(true);
                    return;
                }

                dollar = Integer.parseInt(currentDollar);
            }
        });

        btnClear.setOnClickListener(view -> {
            clear();
        });

        btnConvert.setOnClickListener(v -> {
            convert();
            if (TextUtils.isEmpty(editDollar.getText().toString())) {
                editEuro.setText("");
                editVnd.setText("");
            }
        });
    }

    public void clear() {
        editDollar.setText("");
        editDollar.setFocusable(true);
        editEuro.setText("");
        editVnd.setText("");
    }

    public void convert() {
        double convertEuro = dollar * euro;
        double convertVnd = dollar * vnd;
        editEuro.setText(String.valueOf(convertEuro));
        editVnd.setText(String.valueOf(convertVnd));
    }
    public void init() {
        editDollar = (EditText) findViewById(R.id.editTextDollar);
        editEuro = (EditText) findViewById(R.id.editTextEuro);
        editVnd  = (EditText) findViewById(R.id.editTextVND);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnConvert = (Button) findViewById(R.id.btn_convert);
    }
}

