package com.example.bme_003;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import Model.User;
public class ProfileActivity extends AppCompatActivity implements WeightDialog.WeightDialogListener, GenderDialog.GenderDialogListener, HeightDialog.HeightDialogListner {

    ImageView displayPicture;
    private FirebaseUser fUser;
    private Button weightButton;
    private  Button genderButton;
    private  Button heightButton;


    public void onClickPic(View view) {
        Intent intentPic = new Intent(this, profileChangePicture.class);
        startActivity(intentPic);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        displayPicture = findViewById(R.id.display_picture);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        getUserImage();

        weightButton = (Button) findViewById(R.id.weight);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDialog();
            }
        });

        genderButton = (Button) findViewById(R.id.gender);
        genderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenderDialog();
            }
        });

        heightButton = (Button) findViewById(R.id.height);
        heightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHeightDialog();
            }
        });

    }

    private void openHeightDialog() {
        HeightDialog heightDialog = new HeightDialog();
        heightDialog.show(getSupportFragmentManager(),"example dialog");

    }

    private void openGenderDialog() {
        GenderDialog genderDialog = new GenderDialog();
        genderDialog.show(getSupportFragmentManager(),"example dialog");
    }

    private void openWeightDialog() {
        WeightDialog weightDialog = new WeightDialog();
        weightDialog.show(getSupportFragmentManager(),"example dialog");

    }


    private void getUserImage() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Picasso.get().load(user.getImageurl()).into(displayPicture);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void applyTexts(String weight) {
        Log.i(weight,"saas");
    }


    @Override
    public void applyText(String gender) {
        Log.i(gender,"saas");
    }


    @Override
    public void applyheight(String feet, String inch) {
        Log.i(feet,inch);
    }
}
