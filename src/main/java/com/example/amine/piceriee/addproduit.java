package com.example.amine.piceriee;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class addproduit extends ActionBarActivity {
    Bitmap photo;
    EditText lable,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduit);
    }

    public void take(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1888);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1888 && resultCode == Activity.RESULT_OK) {
             photo = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.img);
            imageview.setImageBitmap(photo);

        }
    }

    public void add (View view) {

        lable =(EditText) findViewById(R.id.lable);
        desc =(EditText) findViewById(R.id.desc);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("produit");
        String id = myRef.push().getKey();
        myRef.child(id).setValue(new Produit(lable.getText().toString(),desc.getText().toString(),Produit.BitmapToString(photo)));

        desc.setVisibility(View.INVISIBLE);
        Intent intent=new Intent(this,GrilleProduitsActivity.class);
        startActivity(intent);

    }

    public void annuler(View view) {
        Intent intent=new Intent(this,GrilleProduitsActivity.class);
        startActivity(intent);
    }
}

class Produit{
    private String id,desc,lable, photo;
    public Produit(){}
    public Produit(String desc,String lable ,String photo){
        this.lable =lable;
        this.desc = desc;
        this.photo =photo;
    }

    /*public Produit(String id,String lable ,String desc,String photo){
     this.id = id;
        this.lable =lable;
        this.desc = desc;
        this.photo =photo;
    }*/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
    public static Bitmap StringToBitmap(String photo)throws IOException {
        byte[] decodedByteArray = Base64.decode(photo, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0,
                decodedByteArray.length);
    }

}