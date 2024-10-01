// MainActivity2.java
package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    ImageView immood, imweb, imphone, imlocation;
    Button btncreate;
    final int CREATE_Contact = 1; // Identifiant de la requête
    String name = "", number = "", web = "", map = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialisation des vues
        immood = findViewById(R.id.immood);
        imphone = findViewById(R.id.imphone);
        imweb = findViewById(R.id.imweb);
        imlocation = findViewById(R.id.imlocation);
        btncreate = findViewById(R.id.btncreate);

        // Masquer les icônes au départ
        immood.setVisibility(View.GONE);
        imphone.setVisibility(View.GONE);
        imweb.setVisibility(View.GONE);
        imlocation.setVisibility(View.GONE);

        // Lorsqu'on clique sur le bouton "Créer"
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, com.example.myapplication.DetailsActivity.class);
                startActivityForResult(intent, CREATE_Contact);  // Utilisation de startActivityForResult
            }
        });

        // Lorsqu'on clique sur le téléphone
        imphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        // Lorsqu'on clique sur le site web
        imweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);
            }
        });

        // Lorsqu'on clique sur la localisation
        imlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_Contact && resultCode == RESULT_OK && data != null) {
            // Rendre visibles les icônes
            imlocation.setVisibility(View.VISIBLE);
            imweb.setVisibility(View.VISIBLE);
            imphone.setVisibility(View.VISIBLE);
            immood.setVisibility(View.VISIBLE);

            // Récupérer les données passées
            name = data.getStringExtra("name");
            web = data.getStringExtra("web");
            number = data.getStringExtra("number");
            map = data.getStringExtra("map");
            mood = data.getStringExtra("mood");

            // Afficher l'humeur appropriée
            if (mood.equals("happy")) {
                immood.setImageResource(R.drawable.happy);
            } else if (mood.equals("sad")) {
                immood.setImageResource(R.drawable.dissatisfied);
            } else {
                immood.setImageResource(R.drawable.neutre);
            }
        } else {
            Toast.makeText(this, "No data passed.", Toast.LENGTH_SHORT).show();
        }
    }
}

