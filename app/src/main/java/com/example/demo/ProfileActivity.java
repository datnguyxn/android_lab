package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFollowing, tvFollowers;
    private Button btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
        changeFollows();

        btnFollow.setOnClickListener(view -> {
            int followers = Integer.parseInt(tvFollowers.getText().toString());

            if (btnFollow.getText().toString().equals("Follow")) {
                followers++;
                btnFollow.setText("Unfollow");
            } else {
                followers--;
                btnFollow.setText("Follow");
            }
            tvFollowers.setText(String.valueOf(followers));
        });
    }

    public void changeFollows() {
        int following = getRandomNumberUsingInts(100, 10000);
        int followers = getRandomNumberUsingInts(100, 10000);
        tvFollowing.setText(String.valueOf(following));
        tvFollowers.setText(String.valueOf(followers));
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public void init() {
        tvFollowing = findViewById(R.id.tvFollowing);
        tvFollowers = findViewById(R.id.tvFollowers);
        btnFollow = findViewById(R.id.btnFollow);
    }
}