package adapters;

import static dataBase.DataBase.productList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dataBase.DataBase;
import es.ishoppinglist.R;
import models.Product;


public class Product_adapter extends ArrayAdapter<Product> {

    private List<Product> products; // Lista de objetos Product que se mostrarán


    public Product_adapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }

    /**
     * Proporciona una vista para un AdapterView (ListView, GridView, etc.).
     * Este método se llama al renderizar cada fila de la lista.
     *
     * @param position    La posición del ítem dentro de los datos del adaptador.
     * @param convertView La vista antigua para reutilizar, si es posible.
     * @param parent      El ViewGroup al que eventualmente se adjuntará esta vista.
     * @return La vista correspondiente a los datos en la posición especificada.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtiene el producto en la posición actual
        Product p = this.products.get(position);

        // Si convertView es nulo, infla una nueva vista desde el recurso de diseño
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Recupera los componentes UI de la vista inflada
        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);

        // Establece el nombre del producto y un texto de información en los TextViews
        tvName.setText(p.getNombre_producto());
        tvInfo.setText("Pulsa para detalle de producto"); // Texto informativo

        return convertView;
    }

    /**
     * Método para crear y devolver la vista de cada elemento desplegable del Spinner.
     *
     * @param position    La posición del ítem dentro del adaptador.
     * @param convertView La vista antigua para reutilizar, si es posible.
     * @param parent      El ViewGroup al que eventualmente se adjuntará esta vista.
     * @return La vista correspondiente a los datos en la posición especificada en el desplegable.
     */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtiene el producto de la base de datos en la posición actual
        Product product = DataBase.getProductToList().get(position);

        // Si convertView es nulo, infla una nueva vista desde el recurso de diseño
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Recupera los componentes UI de la vista inflada
        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);

        // Establece el nombre del producto y la información adicional en los TextViews
        tvName.setText(product.getNombre_producto());
        tvInfo.setText(String.valueOf(product.getNota_info()));

        return convertView;
    }
}
