package com.example.stockcontroller;

public class Produit {
    int ref;
    String nom;
    String details;
    byte[] image;


    public Produit(int ref, String nom, String details, byte[] image) {
        this.ref = ref;
        this.nom = nom;
        this.details = details;
        this.image = image;
    }


    public Produit(String nom, String details, byte[] image) {
        this.nom = nom;
        this.details = details;
        this.image = image;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
