package com.example.db_tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Utilisateur {
    private String nom;
    private String courriel;

    public Utilisateur(String nom, String courriel) {
        this.nom = nom;
        this.courriel = courriel;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    @Override
    public String toString(){
        return nom + ", " + courriel ;
    }
}
