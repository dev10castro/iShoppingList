package adapters;

import static dataBase.DataBase.productList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dataBase.DataBase;
import es.ishoppinglist.R;
import models.Product;

public class Product_adapter extends ArrayAdapter<Product> {

    private List<Product> products;

    // Constructor que acepta una lista de productos.
    public Product_adapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.products.get(position);

        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);

        // Modificamoslos atributos de los componentes
        tvName.setText(p.getNombre_producto());
        tvInfo.setText("Pulsa para detalle de producto"); // Convierte el ID a String
        return convertView;
    }


    // Método para crear y devolver la vista de cada elemento desplegable del Spinner.
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = DataBase.getProductToList().get(position);
        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }
        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);
        // Modificamoslos atributos de los componentes
        tvName.setText(product.getNombre_producto());
        tvInfo.setText(String.valueOf(product.getNota_info()));
        return convertView;
    }
}
