package dataBase;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.ishoppinglist.Add_New_Product;
import models.Product;

public class DataBase {

    public static List<Product> productList;

    public static void initializeList() {
        productList = new ArrayList<>();

        Product p1 = new Product(1, "Leche Descremada", "Leche descremada 1L, ideal para dietas bajas en grasa.", true);
        Product p2 = new Product(2, "Pan Integral", "Pan de trigo integral con alto contenido en fibra.", false);
        Product p3 = new Product(3, "Queso Gouda", "Queso gouda holandés, suave y cremoso. Paquete de 200g.", true);
        Product p4 = new Product(4, "Avena", "Cereal de avena con frutos rojos, paquete de 500g.", false);
        Product p5 = new Product(6, "Jabón Líquido", "Jabón líquido antibacterial, fragancia a lavanda, 400ml.", false);
        Product p6 = new Product(7, "Spaghetti", "Pasta de trigo duro, paquete de 1kg.", true);
        Product p7 = new Product(8, "Tomate Triturado", "Tomate triturado natural, lata de 400g.", false);
        Product p8 = new Product(9, "Café Molido", "Café molido 100% arábica, paquete de 250g.", true);
        Product p9 = new Product(10, "Azúcar Moreno", "Azúcar moreno orgánico, bolsa de 1kg.", false);
        Product p10 = new Product(11, "Yogur Griego", "Yogur griego natural, alto en proteínas, 500g.", true);
        Product p11 = new Product(12, "Plátanos", "De Canarias", true);

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);
        productList.add(p7);
        productList.add(p8);
        productList.add(p9);
        productList.add(p10);
        productList.add(p11);
    }

    /**
     * Método para obtener la lista de productos pendientes de compra
     *
     * @return - Lista de productos pendientes de compra
     */
    public static ArrayList<Product> getShopingList() {
        ArrayList<Product> productsPending = new ArrayList<>();
        for (Product product : productList) {
            if (!product.getEstado_compra()) {
                productsPending.add(product);
            }
        }
        return productsPending;
    }

    /**
     * Método para obtener la lista de productos no pendientes de compra
     *
     * @return - Lista de productos no pendientes de compra
     */
    public static ArrayList<Product> getProductToList() {
        ArrayList<Product> productsNotPending = new ArrayList<>();
        for (Product product : productList) {
            if (product.getEstado_compra()) {
                productsNotPending.add(product);
            }
        }
        return productsNotPending;
    }

    /**
     * Método para obtener un producto por su id
     *
     * @param id - Id del producto
     * @return - Objeto Product
     */
    public static Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return new Product();
    }

    /*
    metodo para devolver el valor de id maxima de la lista
     */

    public static int getIdMax() {
        int idMax = 0;
        for (Product product : productList) {
            if (product.getId() > idMax) {
                idMax = product.getId();
            }
        }
        return idMax;
    }
    /**
     * Método para obtener el último id de la lista de productos
     * @return
     */
    public static int getLastIdByProductList() {
        int id = productList.size();

        return id;
    }

    public static void addProduct(Product product, Context context) {

    for (Product p : productList) {
        if (p.getId() == product.getId()) {
            Toast.makeText(context, "Ya existe un producto con ese id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (product.getNombre_producto().equalsIgnoreCase(p.getNombre_producto())) {
            Toast.makeText(context, "Ya existe un producto con ese nombre", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    Toast.makeText(context, "Producto añadido correctamente", Toast.LENGTH_SHORT).show();
    productList.add(product);
}

}
