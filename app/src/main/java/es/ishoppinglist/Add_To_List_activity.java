package es.ishoppinglist;


import static dataBase.DataBase.getPendingProducts;
import static dataBase.DataBase.getPurchasedProducts;
import static dataBase.DataBase.productList;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import adapters.Product_adapter;
import models.Product;

public class Add_To_List_activity extends AppCompatActivity {

    Button addNewProduct, back;
    Spinner spinner;

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

        spinner = findViewById(R.id.spinner);
        Product_adapter adapter = new Product_adapter(Add_To_List_activity.this, 0, getPendingProducts());
        spinner.setAdapter(adapter);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(Add_To_List_activity.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });

        addNewProduct = findViewById(R.id.buttonadd);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product product = (Product) spinner.getSelectedItem();
                product.setEstado_compra(true);
                Toast.makeText(Add_To_List_activity.this, "Producto añadido a la lista", Toast.LENGTH_SHORT).show();
                Intent intentToMain = new Intent(Add_To_List_activity.this, MainActivity.class);
                startActivity(intentToMain);

            }
        });


    }
}