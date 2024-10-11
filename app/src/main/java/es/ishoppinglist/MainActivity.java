package es.ishoppinglist;

import static dataBase.DataBase.inicializeList;
import static dataBase.DataBase.initializePendingProducts;
import static dataBase.DataBase.initializePurchasedProducts;
import static dataBase.DataBase.pendingProductList;
import static dataBase.DataBase.productList;
import static dataBase.DataBase.purchasedProductList;

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
        initializePurchasedProducts();

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

        // Define un listener para manejar los clics en los elementos del ListView.
        lsShList.setOnItemClickListener((parent, view, position, id) -> {
            // Obtiene la producto seleccionada en la posición del clic.
            Product product = pendingProductList.get(position);

            // Muestra la información de la persona seleccionada en los logs para depuración.
            Log.i("product click", product.toString());

            // Crea un intent para lanzar la actividad de detalles (Detaill_Activity).
            Intent detailIntent = new Intent(MainActivity.this, Activity_detaill.class);

            // Agrega la persona seleccionada al intent como extra para pasarlo a la actividad de detalles.
            detailIntent.putExtra("product", product);



            detailIntent.putExtra("product", product);
            // Inicia la actividad de detalles.
            startActivity(detailIntent);
        });

        //define un listener para seleccionar un producto del listview y te lleve a la pantalla detalle











    }
}