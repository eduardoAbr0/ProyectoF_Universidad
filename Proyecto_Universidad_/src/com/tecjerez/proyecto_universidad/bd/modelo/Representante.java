package com.tecjerez.proyecto_universidad.bd.modelo;

public class Representante {
    private int id;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private int telefono;
    private int clase;

    public Representante(int id, String nombre, String primer_apellido, String segundo_apellido, int telefono, int clase) {
        this.id = id;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.clase = clase;
    }
    
    public Representante( String nombre, String primer_apellido, String segundo_apellido, int telefono, int clase) {
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.clase = clase;
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

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }
    
    
}
