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

import adapters.Product_adapter;
import dataBase.DataBase;
import models.Product;


 // Clase Spinner_activity que permite al usuario seleccionar productos desde un Spinner y añadirlos a la lista de compras.
 //La actividad también ofrece la opción de regresar a la pantalla principal sin realizar cambios.

public class Spinner_activity extends AppCompatActivity {

    // Botones para añadir el producto seleccionado o volver a la pantalla principal
    Button addNewProduct, back;

    // Spinner para mostrar la lista de productos disponibles para agregar a la lista de compras
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

        // Inicialización de los componentes de la interfaz
        addNewProduct = findViewById(R.id.buttonadd);
        back = findViewById(R.id.back);
        lspinner = findViewById(R.id.spinner);

        // Configura el adaptador del Spinner con la lista de productos disponibles para añadir a la compra
        Product_adapter productAdapter = new Product_adapter(Spinner_activity.this, 0, DataBase.getProductToList());
        lspinner.setAdapter(productAdapter);

        // Configuración del botón para volver a la pantalla principal sin añadir ningún producto
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a la actividad principal (MainActivity)
                Intent intentToMain = new Intent(Spinner_activity.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });

        // Configuración del botón para añadir el producto seleccionado a la lista de compras
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el producto seleccionado en el Spinner
                Product product = (Product) lspinner.getSelectedItem();

                // Cambia el estado del producto a 'pendiente'
                product.setEstado_compra(false);

                // Muestra un mensaje de éxito al añadir el producto
                Toast.makeText(Spinner_activity.this, "Producto añadido a la lista de compras", Toast.LENGTH_SHORT).show();

                // Redirige a la actividad principal (MainActivity)
                Intent intent = new Intent(Spinner_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
