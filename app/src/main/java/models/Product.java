package models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String nombre_producto;
    private String nota_info;
    private boolean estado_compra;
    private boolean lactosa;
    private boolean gluten;


    public Product(int id, String nombre_producto, String nota_info, boolean estado_compra, boolean lactosa, boolean gluten) {
        this.id = id;
        this.nombre_producto = nombre_producto;
        this.nota_info = nota_info;
        this.estado_compra = estado_compra;
        this.lactosa=lactosa;
        this.gluten=gluten;
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public Product() {
    }

    public boolean getEstado_compra() {

        return estado_compra;
    }

    public int getId() {

        return id;
    }

    public String getNombre_producto() {

        return nombre_producto;
    }

    public String getNota_info() {

        return nota_info;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNota_info(String nota_info) {
        this.nota_info = nota_info;
    }

    public void setEstado_compra(boolean estado_compra) {
        this.estado_compra = estado_compra;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id == product.id; // O compara otro atributo si es más relevante
    }

    @Override
    public String toString() {
        return "Producto{" +
                "  id='" + id + '\'' +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", nota_info='" + nota_info + '\'' +
                ", estado_compra='" + estado_compra + '\'' +
                '}';
    }
}
