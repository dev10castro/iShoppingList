package dataBase;

import java.util.ArrayList;
import java.util.List;

import models.Product;

public class DataBase {

    public static List<Product> productList;
    public static List<Product> purchasedProductList;
    public static List<Product> pendingProductList;

    public static void inicializeList() {

        productList = new ArrayList<Product>();

        Product p1 = new Product(1, "Leche Descremada", "Leche descremada 1L, ideal para dietas bajas en grasa.", true);
        Product p2 = new Product(2, "Pan Integral", "Pan de trigo integral con alto contenido en fibra.", false);
        Product p3 = new Product(3, "Queso Gouda", "Queso gouda holandés, suave y cremoso. Paquete de 200g.", true);
        Product p4 = new Product(4, "Avena", "Cereal de avena con frutos rojos, paquete de 500g.", false);
        Product p5 = new Product(6, "Jabón Líquido", "Jabón líquido antibacterial, fragancia a lavanda, 400ml.", false);
        Product p6 = new Product(7, "Spaghetti", "Pasta de trigo duro, paquete de 1kg.", true);
        Product p7 = new Product(8, "Tomate Triturado", "Tomate triturado natural, lata de 400g.", false);
        Product p8 =new Product(9, "Café Molido", "Café molido 100% arábica, paquete de 250g.", true);
        Product p9 =new Product(10, "Azúcar Moreno", "Azúcar moreno orgánico, bolsa de 1kg.", false);
        Product p10 =new Product(11, "Yogur Griego", "Yogur griego natural, alto en proteínas, 500g.", true);
        Product p11 = new Product(12,"Platanos","De canarias", true);

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


    // Método para inicializar solo los productos pendientes de compra
    public static void initializePendingProducts() {
         pendingProductList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getEstado_compra()) {
                pendingProductList.add(product);
            }
        }
    }

    // Método para inicializar solo los productos ya comprados
    public static void initializePurchasedProducts() {
        purchasedProductList = new ArrayList<>();
        for (Product product : productList) {
            if (!product.getEstado_compra()) {
                purchasedProductList.add(product);
            }
        }
    }
}
