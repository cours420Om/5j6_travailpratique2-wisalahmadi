package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
    String nomComplet;
    DatabaseReference ref;
    String pays, dateNais,gender;
    FragmentAffichage frag1;

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

        binding.btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomComplet = binding.etChercher.getText().toString();

                lectureDonnees(nomComplet);
            }
        });

        binding.btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomComplet = binding.etChercher.getText().toString();
                effacerDonnees(nomComplet);
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
                        pays= String.valueOf(data.child("pays").getValue());
                        dateNais = String.valueOf(data.child("dateNais").getValue());
                        gender = String.valueOf(data.child("gender").getValue());

                        Bundle bundle = new Bundle();
                        bundle.putString("pays",pays);
                        bundle.putString("date",dateNais );
                        bundle.putString("gender",gender );

                        frag1 = new FragmentAffichage();

                        frag1.setArguments(bundle);

                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.fl_fragment, frag1).commit();

                    } else {
                        Toast.makeText(ActivityAffichage.this, "L'utilisateur n'existe pas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAffichage.this, "Erreur de lecture", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void effacerDonnees(String usager) {
        ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");

        ref.child(usager).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ActivityAffichage.this, "l'enrigistrement a été effacé", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(ActivityAffichage.this, "Erreur lors de la suppresion", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
