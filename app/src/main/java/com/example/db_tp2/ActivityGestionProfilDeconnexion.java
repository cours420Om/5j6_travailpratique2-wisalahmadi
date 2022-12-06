package com.example.db_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

public class ActivityGestionProfilDeconnexion extends AppCompatActivity {

    EditText et_Editemail, et_nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_profil_deconnexion);

        et_Editemail = findViewById(R.id.et_editEmail);
        et_nom = findViewById(R.id.et_name);


    }
}
