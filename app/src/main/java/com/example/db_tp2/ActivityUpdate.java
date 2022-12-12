package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db_tp2.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ActivityUpdate extends AppCompatActivity {

    ActivityUpdateBinding binding;
    FirebaseAuth bd;
    String nomComplet, pays, dateNais, gender;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnOupdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomComplet = binding.etNomComplet3.getText().toString();
                pays = binding.etCountry.getText().toString();
                dateNais = binding.etBirthDate3.getText().toString();
                gender = binding.etGender.getText().toString();

                majDonnees(nomComplet,pays,dateNais,gender);
            }
        });

    }
    private void majDonnees(String nomComplet, String pays, String dateNais, String gender ) {

        HashMap usagers = new HashMap();
        usagers.put("nomComplet", nomComplet);
        usagers.put("pays",pays);
        usagers.put("dateNais",dateNais);
        usagers.put("gender ",gender);

        ref = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        ref.child(nomComplet).updateChildren(usagers).addOnCompleteListener(new OnCompleteListener() {

            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    binding.etNomComplet3.setText("");
                    binding.etCountry.setText("");
                    binding.etBirthDate3.setText("");
                    binding.etGender.setText("");
                    Toast.makeText(ActivityUpdate.this,"Enrigistrement mise à jour",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(ActivityUpdate.this,"Erreur dans la mise à jour",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
