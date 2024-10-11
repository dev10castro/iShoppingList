package es.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dataBase.DataBase;
import models.Product;

public class Add_New_Product extends AppCompatActivity {

    private Button addProduct, backToMain;
    private TextView textIdAddP;
    private EditText editTextText, editTextInfoAddP;
    private Switch switchAddP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_product);

        // Ajustes para el comportamiento de EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de las vistas
        addProduct = findViewById(R.id.buttonAddPrDP);
        backToMain = findViewById(R.id.buttonBackToMain);
        textIdAddP = findViewById(R.id.textIdAddP);
        editTextText = findViewById(R.id.editTextText);
        editTextInfoAddP = findViewById(R.id.editTextInfoAddP);
        switchAddP = findViewById(R.id.switchAddP);

        // Establecer el ID máximo en el TextView
        textIdAddP.setText(String.valueOf(DataBase.getIdMax()));

        // Configuración del botón para añadir un producto
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pAux = new Product();
                pAux.setId(DataBase.getIdMax());
                pAux.setNombre_producto(editTextText.getText().toString());
                pAux.setNota_info(editTextInfoAddP.getText().toString());
                pAux.setEstado_compra(switchAddP.isChecked());

                // Validación del nombre del producto
                if (pAux.getNombre_producto().isEmpty()) {
                    Toast.makeText(Add_New_Product.this, "El nombre del producto no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Añadir el producto a la base de datos
                DataBase.addProduct(pAux, Add_New_Product.this);

                // Actualizar el ID para el próximo producto
                textIdAddP.setText(String.valueOf(DataBase.getLastIdByProductList()));

                // Limpiar los campos de entrada
                editTextText.setText("");
                editTextInfoAddP.setText("");
                switchAddP.setChecked(false);
            }
        });

        // Configuración del botón para volver a la pantalla principal
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(Add_New_Product.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });
    }
}
