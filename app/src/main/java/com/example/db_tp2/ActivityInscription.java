package com.example.db_tp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityInscription extends AppCompatActivity {

    EditText et_email, et_pass, et_valPass;
    Button btn_register;
    FirebaseAuth firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        et_email = findViewById(R.id.et_email2);
        et_pass = findViewById(R.id.et_password2);
        et_valPass = findViewById(R.id.et_password2Validation);
        btn_register = findViewById(R.id.btn_register);
        firebase = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String pass = et_pass.getText().toString();
              //  String valPass = et_valPass.getText().toString();


                if(TextUtils.isEmpty(email)){
                    et_email.setError("Veillez entrer votre courriel!");
                }
                if(TextUtils.isEmpty(pass) ){
                    et_pass.setError("Veillez entrer votre mots de passe");
                }if(pass.length()<5){
                    et_pass.setError("Mots de pass doit contenir 8 lettres/chiffres");
                } /*
                if(TextUtils.isEmpty(valPass) ){
                    et_valPass.setError("Veillez entrer votre mots de passe");
                }

                }if(valPass.length()<8){
                    et_valPass.setError("Mots de pass doit contenir 8 lettres/chiffres");
                }if(!valPass.equals(pass)){
                    et_valPass.setError("Mots de pass non semblable");
                }

                     */

                firebase.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(ActivityInscription.this,"user created", Toast.LENGTH_SHORT).show();
                            Intent activityBD = new Intent(getApplicationContext(), ActivityGestionBd.class);
                            startActivity(activityBD);
                        }else {
                            Toast.makeText(ActivityInscription.this,"Il semble avoir une erreur" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if(firebase.getCurrentUser() != null){
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
        }
    }
}
