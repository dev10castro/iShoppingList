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

    Button editProduct;
    Button backToMain;
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

        editProduct = findViewById(R.id.buttonEditPrDP);
        backToMain = findViewById(R.id.buttonBackToMain);

        tvname = findViewById(R.id.textNameDP);
        tvInfo = findViewById(R.id.textInfoDP);
        tvEstado = findViewById(R.id.textEstadoDP);
        tvId = findViewById(R.id.textIdDP);

        Intent intent = getIntent();

        Product p = (Product) intent.getSerializableExtra("product");

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

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToMain = new Intent(Activity_detaill.this, MainActivity.class);
                startActivity(intentToMain);

            }
        });


        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_detaill.this, Edit_Activity.class);
                intent.putExtra("productId", p.getId());
                startActivity(intent);
            }
        });





    }
}