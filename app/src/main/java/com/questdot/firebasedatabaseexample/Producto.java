package com.questdot.firebasedatabaseexample;

public class Producto {

    String key;

    String nombre;
    int precio;
    String link;

    /*public Producto(String key, String nombre, int precio) {
        this.key = key;
        this.nombre = nombre;
        this.precio = precio;

    }*/
    public Producto(String key, String nombre, int precio,String link) {
        this.key = key;
        this.nombre = nombre;

        this.precio = precio;
        this.link = link;

    }

    public Producto() {
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return nombre;
    }



    public int getPrecio() {
        return precio;
    }
    public String getLink() {
        return link;
    }

}
