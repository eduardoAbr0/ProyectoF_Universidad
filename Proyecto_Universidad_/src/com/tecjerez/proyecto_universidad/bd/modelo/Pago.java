package com.tecjerez.proyecto_universidad.bd.modelo;

public class Pago {
    private int id;
    private String fecha;
    private double monto;
    private String metodo;
    //Foreign
    private int garantia;

    public Pago(int id, String fecha, double monto, String metodo, int garantia) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.metodo = metodo;
        this.garantia = garantia;
    }
    
    public Pago(int id, double monto, String metodo, int garantia) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
        this.garantia = garantia;
    }
    
    public Pago(double monto, String metodo, int garantia) {
        this.monto = monto;
        this.metodo = metodo;
        this.garantia = garantia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }
    
    
     
}

