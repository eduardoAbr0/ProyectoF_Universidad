package com.tecjerez.proyecto_universidad.bd;

import com.tecjerez.proyecto_universidad.bd.controlador.CRUDDonador;
import com.tecjerez.proyecto_universidad.bd.controlador.PagoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.VoluntarioCRUD;
import com.tecjerez.proyecto_universidad.bd.modelo.Donador;
import com.tecjerez.proyecto_universidad.bd.modelo.Pago;
import com.tecjerez.proyecto_universidad.bd.modelo.Voluntario;

public class hilos extends Thread {

    String operacio;
    Object objeto;
    int id;
    CRUDDonador donador = new CRUDDonador();
    VoluntarioCRUD voluntario = new VoluntarioCRUD();

    public hilos(String operacion) {
        this.operacio = operacion;
        this.objeto = objeto;
    }

    @Override
    public void run() {
        switch (operacio) {
            case "insertarDonador":
                donador.insertar((Donador) objeto);
                break;
            case "consultarDonador":
                objeto = donador.buscar(id);
                break;
            case "eliminarDonador":
                donador.eliminar(id);
                break;
            case "cambiarDonador":
                donador.actualizar((Donador) objeto);
                break;
            case "consultarTDonador":
                objeto = donador.buscarTodos();
                break;
            case "insertarVoluntario":
                voluntario.insertar((Voluntario) objeto);
                break;
            case "eliminarVoluntario":
                voluntario.eliminar(id);
                break;
            case "cambiarVoluntario":
                voluntario.actualizar((Voluntario) objeto);
                break;
            case "consultarVoluntario":
                objeto = voluntario.buscar(id);
                break;
            case "consultarTVoluntario":
                objeto = voluntario.buscarTodos();
                break;
        }
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public void setId(int id) {
        this.id = id;
    }
}
