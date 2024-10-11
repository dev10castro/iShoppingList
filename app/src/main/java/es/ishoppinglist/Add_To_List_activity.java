package es.ishoppinglist;

import static dataBase.DataBase.initializePendingProducts;
import static dataBase.DataBase.pendingProductList;
import static dataBase.DataBase.productList;
import static dataBase.DataBase.purchasedProductList;

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


        Product_adapter adapter = new Product_adapter(Add_To_List_activity.this, 0, purchasedProductList);
        spinner = findViewById(R.id.spinner);
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
                // Obtiene el producto seleccionado del Spinner
                Product selectedProduct = (Product) spinner.getSelectedItem();

                // Verifica que el producto seleccionado no sea nulo
                if (selectedProduct != null) {
                    // Cambia el estado de compra del producto
                    selectedProduct.setEstado_compra(true);

                    // Agrega el producto a la lista de productos pendientes
                    if (!pendingProductList.contains(selectedProduct)) {
                        pendingProductList.add(selectedProduct);
                    }

                    // Remueve el producto de la lista de productos comprados si existe en la lista
                    purchasedProductList.remove(selectedProduct);

                    // Muestra un mensaje de éxito
                    Toast.makeText(Add_To_List_activity.this, "Producto agregado a la lista", Toast.LENGTH_SHORT).show();

                    // Inicia la actividad principal
                    Intent intentToMain = new Intent(Add_To_List_activity.this, MainActivity.class);
                    startActivity(intentToMain);
                } else {
                    // Muestra un mensaje de error si no se seleccionó un producto
                    Toast.makeText(Add_To_List_activity.this, "Por favor, selecciona un producto", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}