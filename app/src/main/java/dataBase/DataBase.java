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

        if(productList==null) {
            productList = new ArrayList<>();

            Product p1 = new Product(1, "Leche Descremada", "Leche descremada 1L, ideal para dietas bajas en grasa.", true);
            Product p2 = new Product(2, "Pan Integral", "Pan de trigo integral con alto contenido en fibra.", false);
            Product p3 = new Product(3, "Queso Gouda", "Queso gouda holandés, suave y cremoso. Paquete de 200g.", true);
            Product p4 = new Product(4, "Avena", "Cereal de avena con frutos rojos, paquete de 500g.", false);
            Product p5 = new Product(5, "Jabón Líquido", "Jabón líquido antibacterial, fragancia a lavanda, 400ml.", false);
            Product p6 = new Product(6, "Spaghetti", "Pasta de trigo duro, paquete de 1kg.", true);
            Product p7 = new Product(7, "Tomate Triturado", "Tomate triturado natural, lata de 400g.", false);
            Product p8 = new Product(8, "Café Molido", "Café molido 100% arábica, paquete de 250g.", true);
            Product p9 = new Product(9, "Azúcar Moreno", "Azúcar moreno orgánico, bolsa de 1kg.", false);
            Product p10 = new Product(10, "Yogur Griego", "Yogur griego natural, alto en proteínas, 500g.", true);
            Product p11 = new Product(11, "Plátanos", "De Canarias", true);

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


    /**
     * Método para obtener el último id de la lista de productos
     * @return
     */
    public static int getLastIdByProductList() {
        int id = 1;
        for (Product product : productList) {
            if (product.getId() == id) {
                id++;
            } else {
                return id;
            }
        }
        return id;
    }

    /**
     * Funcion que sirve para añadir un producto a la lista de productos
     *
     * @param product - Producto que vamos a añadir
     * @param view    - Vista actual
     */
    public static void addProduct(Product product, Add_New_Product view) {

        for (Product p : productList) {
            if (p.getId() == product.getId()) {
                Toast toas = new Toast(view);
                toas.setText("Ya existe un producto con ese id");
                toas.show();
                return;
            }
            if (product.getNombre_producto().equalsIgnoreCase(p.getNombre_producto())) {
                Toast toas = new Toast(view);
                toas.setText("Ya existe un producto con ese nombre");
                toas.show();
                return;
            }
        }

        Toast toast = new Toast(view);
        toast.setText("Producto añadido correctamente");
        toast.show();
        productList.add(product);
    }

}
