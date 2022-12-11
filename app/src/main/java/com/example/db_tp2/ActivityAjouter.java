package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db_tp2.databinding.ActivityAjouterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityAjouter extends AppCompatActivity {

    ActivityAjouterBinding binding1;
    String nomComplet, pays, dateNais, gender;
    FirebaseAuth bd;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_ajouter);

        binding1 = ActivityAjouterBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        binding1.btnSauvegarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomComplet = binding1.etNomComplet.getText().toString();
                pays = binding1.etCountry.getText().toString();
                dateNais = binding1.etBirthDate.getText().toString();
                gender = binding1.etGender.getText().toString();

                if(!nomComplet.isEmpty() && !pays.isEmpty()  && !dateNais.isEmpty()  && !gender.isEmpty()){
                    Utilisateur usager = new Utilisateur(nomComplet, pays, dateNais, gender);

                    bd = FirebaseAuth.getInstance();
                    ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
                    ref.child(nomComplet).setValue(usager).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding1.etNomComplet.setText("");
                            binding1.etCountry.setText("");
                            binding1.etBirthDate.setText("");
                            binding1.etGender.setText("");
                            Toast.makeText(ActivityAjouter.this,"Ajouter!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
