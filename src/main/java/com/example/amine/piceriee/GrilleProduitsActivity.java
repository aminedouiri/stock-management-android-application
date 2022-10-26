package com.example.amine.piceriee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.amine.piceriee.R.menu.add;

public class GrilleProduitsActivity extends AppCompatActivity {

    Bitmap photo;
    TextView lable, desc;
    ArrayList<Produit> listproduit;
    DatabaseReference myRef;
    String de;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille_produits);
        myRef = FirebaseDatabase.getInstance().getReference().child("produit");
/*
       myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listproduit.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {


                    Produit produit = d.getValue(Produit.class);

                    listproduit.add(produit);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }

/*class MylistAdapter extends ArrayAdapter<Produit>{
    private Activity context;
    private  ArrayList<Produit> listProduits;

public MylistAdapter(Context context, ArrayList<Produit> listproduits) {


    }*/

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item1) {
            Toast.makeText(this, "ajouter un produit", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, addproduit.class);
            startActivity(intent);


            if ((super.onOptionsItemSelected(item))) return true;
            else return false;
        }


        return false;
    }
}






