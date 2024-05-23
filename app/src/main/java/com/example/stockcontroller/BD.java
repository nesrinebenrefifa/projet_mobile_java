package com.example.stockcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {

    public BD(@Nullable Context context) {
        super(context, "productManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE produit (" +
                "ref INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " +
                "details TEXT, " + // Ajout de la colonne details
                "image BLOB" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produit");
        onCreate(sqLiteDatabase);
    }

    public void insertProduct(Produit p) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", p.getNom());
        cv.put("details", p.getDetails()); // Ajout des détails
        cv.put("image", p.getImage());
        sqLiteDatabase.insert("produit", null, cv);
        sqLiteDatabase.close();
    }

    public void updateProduct(Produit p) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", p.getNom());
        cv.put("details", p.getDetails()); // Ajout des détails
        cv.put("image", p.getImage());
        sqLiteDatabase.update("produit", cv, "ref=?", new String[]{String.valueOf(p.getRef())});
        sqLiteDatabase.close();
    }

    public void deleteProduct(int ref) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("produit", "ref=?", new String[]{String.valueOf(ref)});
        sqLiteDatabase.close();
    }

    public Cursor getAllProducts() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM produit", null);
    }

    public Produit getOneProduit(int ref) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query("produit", new String[]{"ref", "nom", "details", "image"}, "ref=?", new String[]{String.valueOf(ref)},
                null, null, null);
        Produit p = null;
        if (c.moveToFirst()) {
            p = new Produit(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3)); // Ajout des détails
        }
        c.close();
        return p;
    }
}