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

public class Edit_Activity extends AppCompatActivity {

    EditText namEdit, infoEdit;
    Switch swEdit;
    Button save,back;
    TextView idEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idEdit = findViewById(R.id.idEdits);
        namEdit = findViewById(R.id.NameEdit);
        infoEdit = findViewById(R.id.TextInfoEdit);
        swEdit = findViewById(R.id.switchEdit);
        save = findViewById(R.id.EditSave);
        back = findViewById(R.id.EditBackToMain);

        // Obtenemos el intent
        Intent intentDetailProduct = getIntent();
        int idProduct = (int) intentDetailProduct.getSerializableExtra("productId");
        Product pAux = DataBase.getProductById(idProduct);
        // Asignamos los valores a los campos correspondientes
        idEdit.setText(String.valueOf(pAux.getId()));
        namEdit.setText(pAux.getNombre_producto());
        infoEdit.setText(pAux.getNota_info());
        swEdit.setChecked(pAux.getEstado_compra());

        // Configuración del botón para guardar los cambios

        // Guardar cambios
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (namEdit.getText().toString().isEmpty()) {
                    Toast toast = new Toast(Edit_Activity.this);
                    toast.setText("El campo nombre no debe estar vacio");
                    toast.show();
                    return;
                }
                pAux.setNombre_producto(namEdit.getText().toString());
                pAux.setNota_info(infoEdit.getText().toString());
                pAux.setEstado_compra(swEdit.isChecked());
                Toast toast = new Toast(Edit_Activity.this);
                toast.setText("Producto editado!");
                toast.show();

                Intent intent = new Intent(Edit_Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(Edit_Activity.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });



    }
}