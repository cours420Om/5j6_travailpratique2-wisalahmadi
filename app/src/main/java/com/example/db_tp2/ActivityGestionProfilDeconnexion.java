package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db_tp2.databinding.ActivityGestionProfilDeconnexionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ActivityGestionProfilDeconnexion extends AppCompatActivity {

    ActivityGestionProfilDeconnexionBinding binding;
    String nom, courriel;
    FirebaseAuth bd;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGestionProfilDeconnexionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = binding.etName.getText().toString();
                courriel = binding.etCourriel.getText().toString();

                if(!nom.isEmpty() && !courriel.isEmpty()){
                    Utilisateur usager = new Utilisateur(nom,courriel);

                    bd = FirebaseAuth.getInstance();
                    ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
                    ref.child(nom).setValue(usager).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.etName.setText("");
                            binding.etCourriel.setText("");
                            Toast.makeText(ActivityGestionProfilDeconnexion.this,"Update!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void majDonnees(String nom, String courriel) {

        HashMap usagers = new HashMap();
        usagers.put("Nom complet",nom);
        usagers.put("Courriel",courriel);

        ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        ref.child(nom).updateChildren(usagers).addOnCompleteListener(new OnCompleteListener() {

            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    binding.etName.setText("");
                    binding.etCourriel.setText("");
                    Toast.makeText(ActivityGestionProfilDeconnexion.this,"Updated Success",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(ActivityGestionProfilDeconnexion.this,"Update fail",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
