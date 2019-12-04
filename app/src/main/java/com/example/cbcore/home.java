package com.example.cbcore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void enc(View view) {
        Intent in = new Intent(this, enc.class);
        startActivity(in);
    }

    public void decr(View view) {
        Intent in = new Intent(this, decr.class);
        startActivity(in);
    }
}
