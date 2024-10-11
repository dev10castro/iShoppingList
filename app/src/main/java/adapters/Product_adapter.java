package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
            convertView = View.inflate(getContext(), R.layout.detail_product, null);
        }
        Product p = this.products.get(position);

        TextView tvName = convertView.findViewById(R.id.textname);
        TextView tvInfo = convertView.findViewById(R.id.textNoteInfo);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swStatus = convertView.findViewById(R.id.switch1);

        tvName.setText(p.getNombre_producto());
        tvInfo.setText(p.getNota_info());
        swStatus.setChecked(p.getEstado_compra());


        return convertView;
    }
}
