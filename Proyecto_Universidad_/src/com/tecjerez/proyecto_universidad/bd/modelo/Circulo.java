package com.tecjerez.proyecto_universidad.bd.modelo;

public class Circulo {
    private int id;
    private String nombre;
    private double monto_minimo;

    public Circulo(int id, String nombre, double monto_minimo) {
        this.id = id;
        this.nombre = nombre;
        this.monto_minimo = monto_minimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto_minimo() {
        return monto_minimo;
    }

    public void setMonto_minimo(double monto_minimo) {
        this.monto_minimo = monto_minimo;
    }
    
    
}
