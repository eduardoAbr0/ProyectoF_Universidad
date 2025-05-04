package com.tecjerez.proyecto_universidad.bd.modelo;

public class Parto {
    private int id;
    private String fechaParto;
    private String hora;
    private String tipo ;
    private String observaciones;
    private int idPaciente;
    private int idEmpleado;

    public Parto(int id, String fechaParto, String hora, String tipo, String observaciones) {
        this.id = id;
        this.fechaParto = fechaParto;
        this.hora = hora;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaParto() {
        return fechaParto;
    }

    public void setFechaParto(String fechaParto) {
        this.fechaParto = fechaParto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
}
