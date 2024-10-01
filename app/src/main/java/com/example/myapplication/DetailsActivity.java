// DetailsActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etphone, etweb, etmap;
    ImageView ivhappy, ivsad, ivneutre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialisation des vues
        etName = findViewById(R.id.etName);
        etphone = findViewById(R.id.etphone);
        etweb = findViewById(R.id.etweb);
        etmap = findViewById(R.id.etmap);
        ivhappy = findViewById(R.id.ivhappy);
        ivsad = findViewById(R.id.ivsad);
        ivneutre = findViewById(R.id.ivneutre);

        // Ajouter des click listeners
        ivhappy.setOnClickListener(this);
        ivsad.setOnClickListener(this);
        ivneutre.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Vérifie si tous les champs sont remplis
        if (etName.getText().toString().isEmpty() || etmap.getText().toString().isEmpty() ||
                etphone.getText().toString().isEmpty() || etweb.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent();
            intent1.putExtra("name", etName.getText().toString().trim());
            intent1.putExtra("number", etphone.getText().toString().trim());
            intent1.putExtra("web", etweb.getText().toString().trim());
            intent1.putExtra("map", etmap.getText().toString().trim());

            // Récupérer l'humeur
            if (view.getId() == R.id.ivhappy) {
                intent1.putExtra("mood", "happy");
            } else if (view.getId() == R.id.ivsad) {
                intent1.putExtra("mood", "sad");
            } else {
                intent1.putExtra("mood", "neutre");
            }

            // Envoyer les données à MainActivity2
            setResult(RESULT_OK, intent1);  // Utilise setResult avec RESULT_OK
            finish();
        }
    }
}

