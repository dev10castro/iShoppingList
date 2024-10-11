package es.ishoppinglist;


import static dataBase.DataBase.getShopingList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import adapters.Product_adapter;

import dataBase.DataBase;
import models.Product;

public class MainActivity extends AppCompatActivity {

    Button btnAddProduct;
    Button btnAddToList;
    List<Product> pendingProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pendingProducts = new ArrayList<>();
        ListView lsShList = findViewById(R.id.lvShoProduct);
        btnAddProduct = findViewById(R.id.buttonaddProduct);
        btnAddToList = findViewById(R.id.buttonAddList);
        DataBase.initializeList();
        Product_adapter adapter = new Product_adapter(MainActivity.this, 0, getShopingList());

        lsShList.setAdapter(adapter);

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddP = new Intent(MainActivity.this, Spinner_activity.class);
                startActivity(intentToAddP);
            }
        });



        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddP = new Intent(MainActivity.this, Add_New_Product.class);
                startActivity(intentToAddP);
            }
        });

        // Accedemos a los objetos de la lista
        lsShList.setOnItemClickListener((adapterView, view, i, l) -> {
            Product product = DataBase.getShopingList().get(i);

            Intent intentDetail = new Intent(MainActivity.this, Activity_detaill.class);
            intentDetail.putExtra("product", product);
            startActivity(intentDetail);
        });
    }


}









