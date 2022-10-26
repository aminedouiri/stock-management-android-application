package com.example.amine.piceriee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//java.lang.String
public class Nouveau_Compte extends AppCompatActivity {
    EditText nom ,email, mot_passe, telephone;
    Button enregistre,login;
    FirebaseAuth Auth;
    ProgressBar progressBar;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau__compte);
       // progressBar.setVisibility(View.INVISIBLE);

        nom = (EditText) findViewById(R.id.nom);
        email = (EditText) findViewById(R.id.email);
        mot_passe = (EditText) findViewById(R.id.pass);
        telephone = (EditText) findViewById(R.id.telephone);
        enregistre = (Button) findViewById(R.id.reg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
         log =(TextView) findViewById(R.id.log);
        login=(Button) findViewById(R.id.login);
    }
    public void regs(View view) {

        Auth= FirebaseAuth.getInstance();
        String snom =nom.getText().toString();
        String semail =email.getText().toString();
        String spass =mot_passe.getText().toString();
        String stelephone =telephone.getText().toString();


        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Auth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.print("succe");
                            log.setVisibility(View.VISIBLE);
                            login.setVisibility(View.VISIBLE);
                            enregistre.setVisibility(View.INVISIBLE);
                        } else {
                            System.out.print("erore");
                        }
                    }
                }
        );
        if(snom.isEmpty() && spass.isEmpty()){
            System.out.print("erore");
        }
        else{

            DatabaseReference myRef = database.getReference("user");
            myRef = myRef.child(String.valueOf(myRef.push().getKey()));
            myRef.child("name").setValue(snom);
            myRef.child("email").setValue(semail);
            myRef.child("pass").setValue(spass);
            myRef.child("tele").setValue(stelephone);
            progressBar.setVisibility(View.INVISIBLE);


        }





    }


    public void login(View view) {
        Intent intent=new Intent(this,AuthActivity.class);
        startActivity(intent);
    }
}

class User {
    String name,email,pass,tele;
    static int iduser = 0;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String name,String email,String pass,String tele){
        this.name = name;
        this.email =email;
        this.pass = pass;
        this.tele = tele;
    }
}





