package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtAmount;
    private TextView tvCurrentPercent, tvTip, tvTotal;
    private Button btnSubtract, btnPlus;

    private int amount = 0;
    private int percent = 10;
    private double tip = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initContent();


        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String currentAmount = edtAmount.getText().toString();
                if (TextUtils.isEmpty(currentAmount) || Double.parseDouble(currentAmount) < 0) {
                    edtAmount.setError("amount have to positive!!");
                    edtAmount.setFocusable(true);
                    return;
                }

                amount = Integer.parseInt(currentAmount);
                calculateTip();
                calculateTotal();
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percent--;
                calculateTip();
                calculateTotal();

                if (percent <= 5) {
                    btnSubtract.setEnabled(false);
                }
            }
        });

        btnPlus.setOnClickListener(v -> {
            percent++;

            if (percent >= 5) {
                btnSubtract.setEnabled(true);
            }

            if (percent == 100) {
                btnSubtract.setEnabled(true);
            }
            calculateTip();
            calculateTotal();
        });
    }

    private void calculateTip() {
        double currentTip = tip % percent / 100;
        tvCurrentPercent.setText(String.valueOf(percent) + "%");
        tvTip.setText("$" + String.valueOf(currentTip));
    }

    private void initContent() {
        tvCurrentPercent.setText(String.valueOf(percent) + "%");
        tvTip.setText("$" +String.valueOf(tip));

        calculateTotal();
    }

    private void calculateTotal() {
        double total = amount + amount * percent / 100 ;
        tvTotal.setText("$" + String.valueOf(total));
    }

    private void init() {
        edtAmount = (EditText) findViewById(R.id.edt_amount);
        tvCurrentPercent = (TextView) findViewById(R.id.tv_current_percent);
        tvTip = (TextView) findViewById(R.id.tv_tip);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        btnSubtract = (Button) findViewById(R.id.btn_subtract);
        btnPlus = (Button) findViewById(R.id.btn_plus);
    }
}