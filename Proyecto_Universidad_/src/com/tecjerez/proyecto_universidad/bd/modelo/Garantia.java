package com.tecjerez.proyecto_universidad.bd.modelo;

public class Garantia {
    private int id;
    private String fecha;
    private double cantidad;
    private String estado;
    //Foreing
    private int donador;
    private int circulo;
    private int pago;

    public Garantia(int id, String fecha, double cantidad, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.estado = estado;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getDonador() {
        return donador;
    }

    public void setDonador(int donador) {
        this.donador = donador;
    }

    public int getCirculo() {
        return circulo;
    }

    public void setCirculo(int circulo) {
        this.circulo = circulo;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }
    
    
}
