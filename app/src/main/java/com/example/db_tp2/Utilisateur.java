package com.example.db_tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Utilisateur {

    private String nomComplet;
    private String pays;
    private String dateNais;
    private String gender;

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Utilisateur(String nomComplet, String pays, String dateNais, String gender) {
        this.nomComplet = nomComplet;
        this.pays = pays;
        this.dateNais = dateNais;
        this.gender = gender;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDateNais() {
        return dateNais;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
