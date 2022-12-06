package com.example.db_tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Utilisateur implements Parcelable {
    private String nom;
    private String courriel;
    private String pass;

    public Utilisateur(String nom, String courriel, String pass, String passVer) {
        this.nom = nom;
        this.courriel = courriel;
        this.pass = pass;
        this.passVer = passVer;
    }

    private String passVer;

    protected Utilisateur(Parcel in) {
        nom = in.readString();
        courriel = in.readString();
        pass = in.readString();
        passVer = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassVer() {
        return passVer;
    }

    public void setPassVer(String passVer) {
        this.passVer = passVer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(courriel);
        parcel.writeString(pass);
        parcel.writeString(passVer);
    }
}
