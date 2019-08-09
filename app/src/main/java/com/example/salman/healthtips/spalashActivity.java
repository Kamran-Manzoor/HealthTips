package com.example.salman.healthtips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class spalashActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        ActionBar action=getSupportActionBar();
        assert action != null;
        action.hide();
        ProgressBar simpleProgressBar=(ProgressBar) findViewById(R.id.simpleProgressBar); // initiate the progress bar
        simpleProgressBar.setMax(100); // 100 maximum value for the progress bar
        simpleProgressBar.setVisibility(View.VISIBLE);
           handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(spalashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }


}
