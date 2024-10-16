package dataBase;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.ishoppinglist.Add_New_Product;
import models.Product;


public class DataBase {

    // Lista estática de productos que representa la "base de datos"
    public static List<Product> productList;


    public static void initializeList() {
        if (productList == null) {
            productList = new ArrayList<>();

            // Se añaden productos predefinidos a la lista
            Product p1 = new Product(1, "Leche Descremada", "Leche descremada 1L, ideal para dietas bajas en grasa.", true, true, false);
            Product p2 = new Product(2, "Pan Integral", "Pan de trigo integral con alto contenido en fibra.", false, false, true);
            Product p3 = new Product(3, "Queso Gouda", "Queso gouda holandés, suave y cremoso. Paquete de 200g.", true, true, false);
            Product p4 = new Product(4, "Avena", "Cereal de avena con frutos rojos, paquete de 500g.", false, false, true);
            Product p5 = new Product(5, "Jabón Líquido", "Jabón líquido antibacterial, fragancia a lavanda, 400ml.", false, false, false);
            Product p6 = new Product(6, "Spaghetti", "Pasta de trigo duro, paquete de 1kg.", true, false, true);
            Product p7 = new Product(7, "Tomate Triturado", "Tomate triturado natural, lata de 400g.", false, false, false);
            Product p8 = new Product(8, "Café Molido", "Café molido 100% arábica, paquete de 250g.", true, false, false);
            Product p9 = new Product(9, "Azúcar Moreno", "Azúcar moreno orgánico, bolsa de 1kg.", false, false, false);
            Product p10 = new Product(10, "Yogur Griego", "Yogur griego natural, alto en proteínas, 500g.", true, true, false);
            Product p11 = new Product(11, "Plátanos", "De Canarias", true, false, false);

            // Se añaden los productos a la lista
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
     * Obtiene la lista de productos pendientes de compra (estado 'pendiente').
     *
     * @return Una lista de productos cuyo estado de compra es false (pendiente).
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
     * Obtiene la lista de productos comprados (estado 'no pendiente').
     *
     * @return Una lista de productos cuyo estado de compra es true (comprado).
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
     * Busca y devuelve un producto según su ID.
     *
     * @param id El ID del producto que se desea buscar.
     * @return El producto con el ID especificado o un nuevo objeto Product vacío si no se encuentra.
     */
    public static Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return new Product(); // Devuelve un producto vacío si no se encuentra
    }

    /**
     * Obtiene el siguiente ID disponible para añadir un nuevo producto.
     *
     * @return El siguiente ID que se puede asignar a un nuevo producto.
     */
    public static int getLastId() {

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
     * Añade un nuevo producto a la lista si no existe un producto con el mismo ID o nombre.
     * Si ya existe un producto con el mismo ID o nombre, muestra un mensaje Toast indicando el error.
     *
     * @param product El producto que se desea añadir.
     * @param view    La vista actual, usada para mostrar los mensajes Toast.
     */
    public static void addProduct(Product product, Add_New_Product view) {

        for (Product p : productList) {
            if (p.getId() == product.getId()) {
                Toast toast = new Toast(view);
                toast.setText("Ya existe un producto con ese id");
                toast.show();
                return;
            }
            if (product.getNombre_producto().equalsIgnoreCase(p.getNombre_producto())) {
                Toast toast = new Toast(view);
                toast.setText("Ya existe un producto con ese nombre");
                toast.show();
                return;
            }
        }

        // Si no existen conflictos, añade el producto y muestra un mensaje de éxito
        Toast toast = new Toast(view);
        toast.setText("Producto añadido!!");
        toast.show();
        productList.add(product);
    }

    /**
     * metodo para seleccionar los productos con lactosa
     */
    public static ArrayList<Product> getLactosaList() {
        ArrayList<Product> productsLactosa = new ArrayList<>();
        for (Product product : getShopingList()) {
            if (product.isLactosa()) {
                productsLactosa.add(product);
            }
        }
        return productsLactosa;
    }

    /**
     * metodo para seleccionar los productos con gluten
     */
    public static ArrayList<Product> getGlutenList() {
        ArrayList<Product> productsGluten = new ArrayList<>();
        for (Product product : getShopingList()) {
            if (product.isGluten()) {
                productsGluten.add(product);
            }
        }
        return productsGluten;
    }


}