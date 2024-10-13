package es.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

/**
 * Clase Add_New_Product que permite al usuario añadir un nuevo producto a la lista.
 * Proporciona un formulario para introducir el nombre, la descripción y el estado de compra del producto.
 */
public class Add_New_Product extends AppCompatActivity {

    // Botones para añadir el producto y regresar a la pantalla principal
    private Button addProduct, backToMain;

    // Elementos de la interfaz para introducir y mostrar datos del producto
    private TextView textIdAddP;
    private EditText editTextText, editTextInfoAddP;
    private Switch switchAddP;

    /**
     * Método onCreate, llamado al crear la actividad.
     * Configura la interfaz de usuario y define las acciones de los botones.
     *
     * @param savedInstanceState El estado de la actividad previamente guardado, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita el modo Edge-to-Edge para una experiencia de pantalla completa
        EdgeToEdge.enable(this);

        // Establece el layout de la actividad
        setContentView(R.layout.activity_add_new_product);

        // Ajustes para manejar los márgenes de la vista principal con respecto a las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de los componentes de la interfaz
        addProduct = findViewById(R.id.buttonAddPrDP);
        backToMain = findViewById(R.id.buttonBackToMain);
        textIdAddP = findViewById(R.id.textIdAddP);
        editTextText = findViewById(R.id.editTextText);
        editTextInfoAddP = findViewById(R.id.editTextInfoAddP);
        switchAddP = findViewById(R.id.switchAddP);

        // Establece el ID máximo de la lista de productos en el TextView
        textIdAddP.setText(String.valueOf(DataBase.getLastId()));

        // Configuración del botón para añadir un nuevo producto
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un nuevo producto y establecer sus propiedades
                Product pAux = new Product();
                pAux.setId(DataBase.getLastId());
                pAux.setNombre_producto(editTextText.getText().toString());
                pAux.setNota_info(editTextInfoAddP.getText().toString());
                pAux.setEstado_compra(switchAddP.isChecked());

                // Registrar los detalles del producto en el log
                Log.d("Add_New_Product", "Adding product: " + pAux.toString());

                // Validar que el nombre del producto no esté vacío
                if (pAux.getNombre_producto().isEmpty()) {
                    Toast toas = new Toast(Add_New_Product.this);
                    toas.setText("El nombre del producto no puede estar vacío");
                    toas.show();
                    return;
                }

                // Añadir el producto a la base de datos y actualizar el ID en la interfaz
                DataBase.addProduct(pAux, Add_New_Product.this);
                textIdAddP.setText(String.valueOf(DataBase.getLastId()));

                // Limpiar los campos del formulario después de añadir el producto
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
