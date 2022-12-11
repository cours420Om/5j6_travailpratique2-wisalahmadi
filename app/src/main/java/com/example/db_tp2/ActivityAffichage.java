package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db_tp2.databinding.ActivityAffichageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityAffichage extends AppCompatActivity {

    ActivityAffichageBinding binding;
    FirebaseAuth bd;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAffichageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnOupdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityUpdate.class));
                finish();
            }
        });
    }

    private void lectureDonnees(String usager) {
        ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        ref.child(usager).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(ActivityAffichage.this, "Lectures des données", Toast.LENGTH_SHORT).show();
                        DataSnapshot data = task.getResult();
                        String nomComplet = String.valueOf(data.child("nomComplet").getValue());
                        String pays = String.valueOf(data.child("pays").getValue());
                        String dateNais = String.valueOf(data.child("dateNais").getValue());
                        String gender = String.valueOf(data.child("gender").getValue());

                        binding.tvNomComplet.setText(nomComplet);
                        binding.tvPays.setText(pays);
                        binding.tvDateNais.setText(dateNais);
                        binding.tvGender.setText(gender);
                    } else {
                        Toast.makeText(ActivityAffichage.this, "L'utilisateur n'existe pas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAffichage.this, "Erreur de lecture", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
