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

    ActivityAjouterBinding binding;
    String nomComplet, pays, dateNais, gender;
    FirebaseAuth bd;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_ajouter);

        binding = ActivityAjouterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSauvegarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomComplet = binding.etNomComplet.getText().toString();
                pays = binding.etCountry.getText().toString();
                dateNais = binding.etBirthDate.getText().toString();
                gender = binding.etGender.getText().toString();

                if(!nomComplet.isEmpty() && !pays.isEmpty()  && !dateNais.isEmpty()  && !gender.isEmpty()){
                    Utilisateur usager = new Utilisateur(nomComplet, pays, dateNais, gender);

                    bd = FirebaseAuth.getInstance();
                    ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
                    ref.child(nomComplet).setValue(usager).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.etNomComplet.setText("");
                            binding.etCountry.setText("");
                            binding.etBirthDate.setText("");
                            binding.etGender.setText("");
                            Toast.makeText(ActivityAjouter.this,"Update!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
