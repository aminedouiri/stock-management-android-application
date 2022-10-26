package com.example.amine.piceriee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class respass extends AppCompatActivity {
    EditText email;
    TextView conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respass);
    }

    public void rest(View view) {
        email = (EditText) findViewById(R.id.email);
        conf = (TextView) findViewById(R.id.conf);
        String semail =email.getText().toString();
        FirebaseAuth Auth= FirebaseAuth.getInstance();
        Auth.sendPasswordResetEmail(semail).addOnCompleteListener(new OnCompleteListener<Void>(){
         public void onComplete(Task<Void> task){
             if(task.isSuccessful()){
                 conf.setVisibility(View.VISIBLE);
             }
         }                                                         }
        );
    }
}
