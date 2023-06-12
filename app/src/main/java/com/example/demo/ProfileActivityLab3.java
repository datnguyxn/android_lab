package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;

public class ProfileActivityLab3 extends AppCompatActivity {

    private TextView tvName, tvJob, tvNameSecond, tvEmail, tvPhone, tvAddress, tvHomepage;
    private RoundedImageView avt;
    private ImageView imgEdit;
    private RoundedImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_lab3);

        init();
        imgEdit.setOnClickListener(v -> {
            String name = tvName.getText().toString();
            String job = tvJob.getText().toString();
            String nameSecond = tvNameSecond.getText().toString();
            String email = tvEmail.getText().toString();
            String phone = tvPhone.getText().toString();
            String address = tvAddress.getText().toString();
            String homepage = tvHomepage.getText().toString();
            String profileAvt = avt.toString();
            // create bitmap from roundedimageview
            avt.setDrawingCacheEnabled(true);
            avt.buildDrawingCache();
            Bitmap bitmap = avt.getDrawingCache();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte[] byteArray = output.toByteArray();
            Intent intent = new Intent(ProfileActivityLab3.this, EditProfileActivityLab3.class);
            intent.putExtra("name", name);
            intent.putExtra("job", job);
            intent.putExtra("nameSecond", nameSecond);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);
            intent.putExtra("homepage", homepage);
            intent.putExtra("profileAvt", byteArray);
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String job = data.getStringExtra("job");
            String nameSecond = data.getStringExtra("nameSecond");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");
            String address = data.getStringExtra("address");
            String homepage = data.getStringExtra("homepage");

            byte[] byteArray = data.getByteArrayExtra("profileAvt");
            Bitmap profileAvt = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            Bitmap avt2 = data.getParcelableExtra("avt");
            tvName.setText(name);
            tvJob.setText(job);
            tvNameSecond.setText(nameSecond);
            tvEmail.setText(email);
            tvPhone.setText(phone);
            tvAddress.setText(address);
            tvHomepage.setText(homepage);
            avt.setImageBitmap(profileAvt);
            avatar = avt;
            avatar.setImageBitmap(avatar.getDrawingCache());
        }
    }



    private void init() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvJob = (TextView) findViewById(R.id.tvJob);
        tvNameSecond = (TextView) findViewById(R.id.tvNameSecond);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvHomepage = (TextView) findViewById(R.id.tvHomepage);
        avt = (RoundedImageView) findViewById(R.id.avt);
        imgEdit = (ImageView) findViewById(R.id.imgEdit);
    }

}