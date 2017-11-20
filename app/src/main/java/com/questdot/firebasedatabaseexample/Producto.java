package com.questdot.firebasedatabaseexample;

public class Producto {

    String key;

    String name;
    int precio;
    String link;

    public Producto(String key, String nombre1, int precio) {
        this.key = key;
        this.name = nombre1;
        this.precio = precio;

    }
    public Producto(String key, String nombre1, int precio,String link) {
        this.key = key;
        this.name = nombre1;

        this.precio = precio;
        this.link = link;

    }

    public Producto() {
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
    public String getnombre() {
        return name;
    }



    public int getPrecio() {
        return precio;
    }
    public String getLink() {
        return link;
    }

}
