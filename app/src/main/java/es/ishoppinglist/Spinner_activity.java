package es.ishoppinglist;




import static dataBase.DataBase.getProductToList;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import adapters.Product_adapter;
import dataBase.DataBase;
import models.Product;

public class Spinner_activity extends AppCompatActivity {

    Button addNewProduct, back;
    Spinner lspinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addNewProduct = findViewById(R.id.buttonadd);
        back = findViewById(R.id.back);
        lspinner = findViewById(R.id.spinner);

        Product_adapter productAdapter = new Product_adapter(Spinner_activity.this, 0, DataBase.getProductToList());
        lspinner.setAdapter(productAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(Spinner_activity.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });

        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = (Product) lspinner.getSelectedItem();
                // Ponemos el estado a pendiente
                product.setEstado_compra(false);
                Intent intent = new Intent(Spinner_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}