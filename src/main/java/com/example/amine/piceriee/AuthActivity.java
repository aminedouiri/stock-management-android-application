package com.example.amine.piceriee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    EditText email,pass;
    TextView fals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public void nvcompte(View view){
        //  Intent intent=new Intent(this,GrilleProduitsActivity.class);
        //startActivity(intent);

        Intent intent=new Intent(AuthActivity.this,Nouveau_Compte.class);
        startActivity(intent);
    }

    public void motpass(View view) {
        Intent intent=new Intent(AuthActivity.this,respass.class);
        startActivity(intent);
    }

    public void log(View view) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String pass = ((EditText) findViewById(R.id.pass)).getText().toString();

        fals = (TextView) findViewById(R.id.fals);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pass).
                addOnCompleteListener(


                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), GrilleProduitsActivity.class));
                                    finish();

                                } else {
                                    fals.setVisibility(View.VISIBLE);
                                }

                            }

                        }
                );
    }
}
