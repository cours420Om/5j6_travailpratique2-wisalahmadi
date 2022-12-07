package com.example.db_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityGestionProfilDeconnexion extends AppCompatActivity {

    EditText et_Editemail, et_nom;
    Button btn_update;
    TextView tv_bjr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_profil_deconnexion);

        et_Editemail = findViewById(R.id.et_courriel);
        et_nom = findViewById(R.id.et_name);
        btn_update = findViewById(R.id.btn_update);
        tv_bjr = findViewById(R.id.tv_titre2);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv_bjr.setText("Bonjour " + et_nom.getText().toString());
            }
        });

    }
}
