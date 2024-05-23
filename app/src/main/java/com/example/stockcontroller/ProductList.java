package com.example.stockcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.net.Uri;

import java.util.HashMap;

public class ProductList extends AppCompatActivity {
    Button Add;
    ListView products;
    String namenewPr, detailsnewPr;
    HashMap<String, String> map;
    Params p = new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Add = findViewById(R.id.Add);

        Add.setOnClickListener(v -> {
            Intent additem = new Intent(getApplicationContext(), AddtoList.class);
            startActivity(additem);
        });

        products = findViewById(R.id.products);
        Bundle extras2 = getIntent().getExtras();
        if (extras2 != null) {
            namenewPr = extras2.getString("namenewPr");
            detailsnewPr = extras2.getString("detailsnewPr");
            String imageUriString = extras2.getString("imageUri");
            Uri imageUri = null;
            if (imageUriString != null) {
                imageUri = Uri.parse(imageUriString);
            }
            map = new HashMap<>();
            map.put("namenewPr", namenewPr);
            map.put("detailsnewPr", detailsnewPr);
            map.put("imageUri", String.valueOf(imageUri));
            p.values.add(map);
        }

        SimpleAdapter listadapter = new SimpleAdapter(ProductList.this, p.values, R.layout.item,
                new String[]{"namenewPr", "detailsnewPr", "imageUri"},
                new int[]{R.id.namenewPr, R.id.detailsnewPr, R.id.imageView});
        products.setAdapter(listadapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(getApplicationContext(), DetailsProduct.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }
}