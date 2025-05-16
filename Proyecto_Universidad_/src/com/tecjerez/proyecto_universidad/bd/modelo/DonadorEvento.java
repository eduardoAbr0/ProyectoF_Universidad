package com.tecjerez.proyecto_universidad.bd.modelo;

public class DonadorEvento {
    private int idDonador;
    private int idEvento;
    private double donativo;

    public DonadorEvento(int idDonador, int idEvento, double donativo) {
        this.idDonador = idDonador;
        this.idEvento = idEvento;
        this.donativo = donativo;
    }

    public int getIdDonador() {
        return idDonador;
    }

    public void setIdDonador(int idDonador) {
        this.idDonador = idDonador;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public double getDonativo() {
        return donativo;
    }

    public void setDonativo(double donativo) {
        this.donativo = donativo;
    }
    
    
}
