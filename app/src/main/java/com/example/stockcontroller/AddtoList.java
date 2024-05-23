package com.example.stockcontroller;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class AddtoList extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    EditText namenewPr, detailsnewPr;
    Button changeImageBtn, addnew;
    ImageView imageView;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_list);

        namenewPr = findViewById(R.id.namenewPr);
        detailsnewPr = findViewById(R.id.detailsnewPr);
        imageView = findViewById(R.id.imageView2);
        changeImageBtn = findViewById(R.id.changeImageBtn);
        addnew = findViewById(R.id.addnew);

        changeImageBtn.setOnClickListener(v -> openImageChooser());

        addnew.setOnClickListener(v -> {
            Intent tolistp = new Intent(getApplicationContext(), ProductList.class);
            tolistp.putExtra("namenewPr", namenewPr.getText().toString());
            tolistp.putExtra("detailsnewPr", detailsnewPr.getText().toString());
            if (imageUri != null) {
                String imageUriString = imageUri.toString();
                tolistp.putExtra("imageUri", imageUriString);
            }
            startActivity(tolistp);
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
