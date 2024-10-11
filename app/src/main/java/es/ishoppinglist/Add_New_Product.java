package es.ishoppinglist;

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

    Button addProduct, backToMain;
    TextView textIdAddP;
    EditText editTextText, editTextInfoAddP;
    Switch switchAddP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addProduct.findViewById(R.id.buttonAddPrDP);
        backToMain.findViewById(R.id.buttonBackToMain);
        textIdAddP.findViewById(R.id.textIdAddP);
        editTextText.findViewById(R.id.editTextText);
        editTextInfoAddP.findViewById(R.id.editTextInfoAddP);
        switchAddP.findViewById(R.id.switchAddP);

        textIdAddP.setText(DataBase.getIdMax());


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product pAux = new Product();
                pAux.setId(DataBase.getIdMax());
                pAux.setNombre_producto(editTextText.getText().toString());
                pAux.setNota_info(editTextInfoAddP.getText().toString());
                pAux.setEstado_compra(switchAddP.isChecked());

                if (pAux.getNombre_producto().isEmpty()){

                    Toast toast = Toast.makeText(getApplicationContext(), "El nombre del producto no puede estar vacío", Toast.LENGTH_SHORT);

                    return;

                }

                DataBase.addProduct(pAux,Add_New_Product.this);
                textIdAddP.setText(String.valueOf(DataBase.getLastIdByProductList()));
                editTextText.setText("");
                editTextInfoAddP.setText("");
                switchAddP.setChecked(false);

            }
        });

    }
}