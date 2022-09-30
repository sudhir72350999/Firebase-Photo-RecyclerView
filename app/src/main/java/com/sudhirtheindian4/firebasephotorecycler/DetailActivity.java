package com.sudhirtheindian4.firebasephotorecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {


    TextView t1,t2;
    ImageView profileImage;

    String name,email,image;


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.email);
        profileImage = findViewById(R.id.profileImage);



        btn=findViewById(R.id.back);

        t1.setText(getIntent().getStringExtra("uname").toString());
        t2.setText(getIntent().getStringExtra("uemail").toString());

//        name = getIntent().getStringExtra("uname");
//        email = getIntent().getStringExtra("uemail");
        image = getIntent().getStringExtra("image");




        try {
            /// picasso or glide dono me se koi bhi
            Glide.with(DetailActivity.this).load(image).placeholder(R.drawable.profile).into(profileImage);
        } catch (Exception e) {
            e.printStackTrace();
        }





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}