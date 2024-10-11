package es.ishoppinglist;

import static dataBase.DataBase.productList;
import static dataBase.DataBase.purchasedProductList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import adapters.Product_adapter;
import dataBase.DataBase;
import models.Product;

public class Detaill_activity extends AppCompatActivity {

    // Declaración de variables para los componentes de la vista.
    private TextView tvname, tvInfo;
    private Spinner spProduct;
    Button addTolist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detaill);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

// Obtiene las referencias a los componentes de la vista por su ID.
        tvname = findViewById(R.id.textname);
        tvInfo = findViewById(R.id.textNoteInfo);

// Botón que se usará para enviar la persona seleccionada.
        addTolist = findViewById(R.id.buttonadd);

// Obtiene el intent que lanzó esta actividad y recupera un objeto de tipo "Product".
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");

// Obtiene la referencia al Spinner de la vista.
        spProduct = findViewById(R.id.spinner);

// Verifica que la lista de productos esté inicializada y no esté vacía antes de asignarla al adaptador.
        if (productList != null && !productList.isEmpty()) {
            // Crea un adaptador para el Spinner usando la lista de productos.
            Product_adapter adapterP = new Product_adapter(Detaill_activity.this, 0, purchasedProductList);
            spProduct.setAdapter(adapterP);

            // Si hay un producto recibido por el intent, encuentra su posición en la lista.
            if (p != null) {
                int position = productList.indexOf(p);
                if (position != -1) {
                    // Selecciona el producto en el Spinner.
                    spProduct.setSelection(position);
                }

            }

        }
    }

}