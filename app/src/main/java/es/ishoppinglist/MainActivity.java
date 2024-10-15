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

    // Botones para añadir productos o ir a la lista de productos para añadir a la compra
    Button btnAddProduct;
    Button btnAddToList;

    // Lista de productos pendientes
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

        // Inicialización de la lista de productos pendientes y de los componentes de la interfaz
        pendingProducts = new ArrayList<>();
        ListView lsShList = findViewById(R.id.lvShoProduct);
        btnAddProduct = findViewById(R.id.buttonaddProduct);
        btnAddToList = findViewById(R.id.buttonAddList);

        // Inicializa la base de datos de productos si aún no se ha hecho
        DataBase.initializeList();

        // Crea un adaptador para mostrar los productos pendientes de compra en la lista
        Product_adapter adapter = new Product_adapter(MainActivity.this, 0, getShopingList());
        lsShList.setAdapter(adapter);

        // Configuración del botón para añadir productos desde la lista desplegable
        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad Spinner_activity para añadir productos
                Intent intentToAddP = new Intent(MainActivity.this, Spinner_activity.class);
                startActivity(intentToAddP);
            }
        });

        // Configuración del botón para añadir un nuevo producto manualmente
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad Add_New_Product para añadir un producto nuevo
                Intent intentToAddP = new Intent(MainActivity.this, Add_New_Product.class);
                startActivity(intentToAddP);
            }
        });

        // Configuración para cuando un producto de la lista es clicado
        lsShList.setOnItemClickListener((adapterView, view, i, l) -> {
            // Obtiene el producto clicado de la lista de productos pendientes
            Product product = DataBase.getShopingList().get(i);

            // Inicia la actividad Activity_detaill para mostrar los detalles del producto
            Intent intentDetail = new Intent(MainActivity.this, Activity_detaill.class);
            intentDetail.putExtra("product", product);
            startActivity(intentDetail);
        });
    }
}
