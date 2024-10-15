package es.ishoppinglist;

import static dataBase.DataBase.productList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Product;


public class Activity_detaill extends AppCompatActivity {

    // Botones para editar el producto y volver a la actividad principal
    Button editProduct;
    Button backToMain;

    // TextViews para mostrar los detalles del producto
    TextView tvname, tvInfo, tvEstado, tvId;


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

        // Vincula los componentes de la interfaz (botones y TextViews) con sus IDs en el layout
        editProduct = findViewById(R.id.buttonEditPrDP);
        backToMain = findViewById(R.id.buttonBackToMain);
        tvname = findViewById(R.id.textNameDP);
        tvInfo = findViewById(R.id.textInfoDP);
        tvEstado = findViewById(R.id.textEstadoDP);
        tvId = findViewById(R.id.textIdDP);

        // Obtiene el Intent que inició esta actividad y recupera el producto pasado como extra
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");

        // Si el producto no es nulo, muestra los detalles del producto en los TextViews correspondientes
        if (p != null) {
            tvId.setText(String.valueOf(p.getId()));
            tvname.setText(p.getNombre_producto());
            tvInfo.setText(p.getNota_info());
            if (p.getEstado_compra()) {
                tvEstado.setText("Comprado");
            } else {
                tvEstado.setText("Pendiente");
            }
        }

        // Configura el botón para volver a la actividad principal cuando se presiona
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(Activity_detaill.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });

        // Configura el botón para editar el producto cuando se presiona
        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad de edición, pasando el ID del producto como extra
                Intent intent = new Intent(Activity_detaill.this, Edit_Activity.class);
                intent.putExtra("productId", p.getId());
                startActivity(intent);
            }
        });
    }
}
