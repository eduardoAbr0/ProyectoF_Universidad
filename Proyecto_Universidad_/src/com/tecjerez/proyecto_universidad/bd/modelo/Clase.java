package com.tecjerez.proyecto_universidad.bd.modelo;

public class Clase {
    private int id;
    private String anio_clase;
    private String nivel;
    private String carrera;

    public Clase(int id, String anio_clase, String nivel, String carrera) {
        this.id = id;
        this.anio_clase = anio_clase;
        this.nivel = nivel;
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnio_clase() {
        return anio_clase;
    }

    public void setAnio_clase(String anio_clase) {
        this.anio_clase = anio_clase;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
}
