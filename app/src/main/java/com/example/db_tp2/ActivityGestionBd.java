package com.example.db_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityGestionBd extends AppCompatActivity {

    Button btn_settings, btn_ouvrirAjouter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_bd);

        btn_settings = findViewById(R.id.btn_setting);
        btn_ouvrirAjouter = findViewById(R.id.btn_ouvrirAjouter);

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityGestionProfilDeconnexion.class));
                finish();
            }
        });

        btn_ouvrirAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityAjouter.class));
                finish();
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
