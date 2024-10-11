package adapters;

import static dataBase.DataBase.productList;
import static dataBase.DataBase.purchasedProductList;

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

        if(convertView==null){
            convertView = View.inflate(getContext(), R.layout.item_product, null);
        }
        Product p = this.products.get(position);

        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);


        tvName.setText(p.getNombre_producto());
        tvInfo.setText(p.getNota_info());



        return convertView;
    }

    // Método para crear y devolver la vista de cada elemento desplegable del Spinner.
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {

        // Obtiene la persona en la posición actual desde la lista.
        Product product = purchasedProductList.get(position);

        // Si convertView es nulo, infla una nueva vista desde el layout 'item_person' para el desplegable.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtiene la referencia al TextView que mostrará el nombre completo de la persona.
        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);

        // Asigna el nombre y apellido de la persona al TextView.
        tvName.setText(product.getNombre_producto());
        tvInfo.setText(product.getNota_info());


        // Devuelve la vista actualizada para el elemento desplegable.
        return convertView;
    }
}
