package com.example.thanhhuyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SpashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        Intent intent = new Intent(SpashScreenActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
