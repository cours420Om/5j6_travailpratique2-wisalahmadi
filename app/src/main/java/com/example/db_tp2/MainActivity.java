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

public class MainActivity extends AppCompatActivity {

    EditText et_email, et_pass;
    Button btn_create, btn_login;
    FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create = findViewById(R.id.btn_create);
        btn_login = findViewById(R.id.btn_login);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_password);

        firebase = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String pass = et_pass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    et_email.setError("Veillez entrer votre courriel!");
                }
                if(TextUtils.isEmpty(pass) ) {
                    et_pass.setError("Veillez entrer votre mots de passe");
                }

                firebase.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Intent activityBD = new Intent(getApplicationContext(), ActivityGestionBd.class);
                             startActivity(activityBD);
                         }else {
                             Toast.makeText(MainActivity.this,"Il semble avoir une erreur" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                    }
                });


            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityInscription = new Intent(getApplicationContext(),ActivityInscription.class);
                startActivity(activityInscription);
            }
        });
    }
}
