package com.example.stockcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username,pass;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= findViewById(R.id.username);
        pass=findViewById(R.id.Password);
        signin=findViewById(R.id.signin);

        signin.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ProductList.class);
            startActivity(i);
        });



    }

}