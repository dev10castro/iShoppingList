package es.ishoppinglist;

import static dataBase.DataBase.inicializeList;
import static dataBase.DataBase.initializePendingProducts;
import static dataBase.DataBase.pendingProductList;

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

import adapters.Product_adapter;
import models.Product;

public class MainActivity extends AppCompatActivity {

    Button btnAddProduct;
    Button btnAddToList;



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

        btnAddProduct = findViewById(R.id.buttonaddProduct);
        btnAddToList = findViewById(R.id.buttonAddList);

        ListView lsShList = findViewById(R.id.lvShoProduct);
        inicializeList();
        initializePendingProducts();

        Product_adapter adapter = new Product_adapter(MainActivity.this, 0, pendingProductList);

        Product p = pendingProductList.get(0);


        lsShList.setAdapter(adapter);

        Log.i("MainActivity", "onCreate: " + p.toString());

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddP= new Intent(MainActivity.this, Add_To_List_activity.class);
                startActivity(intentToAddP);
            }
        });







    }
}