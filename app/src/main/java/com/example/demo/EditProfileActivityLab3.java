package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

public class EditProfileActivityLab3 extends AppCompatActivity {

    private TextView tvName;
    private RoundedImageView avt;
    private ImageView imgEdit;
    private EditText edtName, edtJob, edtEmail, edtPhone, edtAddress, edtLink;
    private Button btnSave;

    private Bitmap avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_lab3);

        init();
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String nameSecond = intent.getStringExtra("nameSecond");
            String job = intent.getStringExtra("job");
            String email = intent.getStringExtra("email");
            String phone = intent.getStringExtra("phone");
            String address = intent.getStringExtra("address");
            String homepage = intent.getStringExtra("homepage");
            byte[] byteArray = getIntent().getByteArrayExtra("profileAvt");
            Bitmap profileAvt = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            tvName.setText(name);
            edtName.setText(nameSecond);
            edtJob.setText(job);
            edtEmail.setText(email);
            edtPhone.setText(phone);
            edtAddress.setText(address);
            edtLink.setText(homepage);
            avt.setImageBitmap(profileAvt);
            avatar = profileAvt;
        }
        openCamera();
        handleSave();
    }

    private void openCamera() {
        imgEdit.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) != getPackageManager().PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ((requestCode == 1) && (grantResults[0] == getPackageManager().PERMISSION_GRANTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && (resultCode == RESULT_OK)) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            avt.setImageBitmap(bitmap);
            avatar = bitmap;
        }
    }

    public void handleSave() {
        btnSave.setOnClickListener(view -> {
            String name = tvName.getText().toString();
            String edtNameText = edtName.getText().toString();
            String nameChange = editName(edtNameText);
            String edtJobText = edtJob.getText().toString();
            String edtEmailText = edtEmail.getText().toString();
            String edtPhoneText = edtPhone.getText().toString();
            String edtAddressText = edtAddress.getText().toString();
            String edtLinkText = edtLink.getText().toString();
            String tvNameSecond = tvName.getText().toString();
//            String imgAvt = avt.toString();
//            avt.setDrawingCacheEnabled(true);
//            avt.buildDrawingCache();
//            Bitmap bitmap = avt.getDrawingCache();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            avatar.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte[] byteArray = output.toByteArray();
            if (edtNameText.isEmpty()) {
                edtName.setError("Name is empty");
                edtName.setFocusable(true);
                return;
            }
            if (edtJobText.isEmpty()) {
                edtJob.setError("Job is empty");
                edtJob.setFocusable(true);
                return;
            }
            if (edtEmailText.isEmpty()) {
                edtEmail.setError("Email is empty");
                edtEmail.setFocusable(true);
                return;
            }
            if (edtPhoneText.isEmpty()) {
                edtPhone.setError("Phone is empty");
                edtPhone.setFocusable(true);
                return;
            }
            if (edtAddressText.isEmpty()) {
                edtAddress.setError("Address is empty");
                edtAddress.setFocusable(true);
                return;
            }
            if (edtLinkText.isEmpty()) {
                edtLink.setError("Link is empty");
                edtLink.setFocusable(true);
                return;
            }

            Intent intent1 = new Intent();
            intent1.putExtra("name", nameChange);
            intent1.putExtra("nameSecond", edtNameText);
            intent1.putExtra("job", edtJobText);
            intent1.putExtra("email", edtEmailText);
            intent1.putExtra("phone", edtPhoneText);
            intent1.putExtra("address", edtAddressText);
            intent1.putExtra("homepage", edtLinkText);
            intent1.putExtra("profileAvt", byteArray);
//            intent1.putExtra("avt", bitmap);
            setResult(RESULT_OK, intent1);
            finish();
        });
    }

    public String editName(String param) {
        for (int i = 0; i < param.length(); i++) {
            if (param.charAt(i) != ' ') {
                return param.toLowerCase(Locale.ROOT);
            }
        }
        param = param.replace(' ', '_');
        return param.toLowerCase(Locale.ROOT);

    }

    private void init() {
        tvName = (TextView) findViewById(R.id.tvName);
        avt = (RoundedImageView) findViewById(R.id.avt);
        imgEdit = (ImageView) findViewById(R.id.imgEdit);
        edtName = (EditText) findViewById(R.id.edtName);
        edtJob = (EditText) findViewById(R.id.edtJob);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtLink = (EditText) findViewById(R.id.edtLink);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

}