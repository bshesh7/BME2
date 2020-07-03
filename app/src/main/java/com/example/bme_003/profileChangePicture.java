package com.example.bme_003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class profileChangePicture extends AppCompatActivity {

    Button uploadPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        uploadPicture = findViewById(R.id.uploadPicture);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change_picture);
    }

    public void onClickUpload(View view) {
        startActivity(new Intent(profileChangePicture.this, PostActivity.class));
    }
}
