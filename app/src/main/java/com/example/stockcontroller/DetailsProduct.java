package com.example.stockcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class DetailsProduct extends AppCompatActivity {
    TextView nompro, refpro, details;
    Button Edit, Deletebt;
    HashMap<String,String> m ;

    int position;
    Params p = new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        nompro = findViewById(R.id.nompro);
        refpro = findViewById(R.id.refpro);
        details = findViewById(R.id.details);
        Edit = findViewById(R.id.Edit);
        Deletebt = findViewById(R.id.dlt);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position=extras.getInt("position");

        }
        m = p.values.get(position);
        nompro.setText(m.get("namenewPr"));
        refpro.setText(m.get("detailsnewPr"));
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.put("namenewPr",nompro.getText().toString());
                m.put("refpro",refpro.getText().toString());
                Intent i = new Intent(getApplicationContext(),ProductList.class);
                startActivity(i);
                finish();
            }


        });
        Deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.values.remove(position);
                Intent i = new Intent(getApplicationContext(),ProductList.class);
                startActivity(i);
                finish();
            }
        });

    }
}