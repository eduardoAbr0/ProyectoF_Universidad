package com.tecjerez.proyecto_universidad.bd;

import com.tecjerez.proyecto_universidad.bd.controlador.CRUDDonador;
import com.tecjerez.proyecto_universidad.bd.controlador.PagoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.RepresentanteCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.VoluntarioCRUD;
import com.tecjerez.proyecto_universidad.bd.modelo.Donador;
import com.tecjerez.proyecto_universidad.bd.modelo.Pago;
import com.tecjerez.proyecto_universidad.bd.modelo.Representante;
import com.tecjerez.proyecto_universidad.bd.modelo.Voluntario;

public class hilos extends Thread {

    String operacio;
    Object objeto;
    int id;
    CRUDDonador donador = new CRUDDonador();
    VoluntarioCRUD voluntario = new VoluntarioCRUD();
    RepresentanteCRUD representante = new RepresentanteCRUD();

    public hilos(String operacion) {
        this.operacio = operacion;
        this.objeto = objeto;
    }

    @Override
    public void run() {
        switch (operacio) {
            case "insertarDonador" -> donador.insertar((Donador) objeto);
            case "consultarDonador" -> objeto = donador.buscar(id);
            case "eliminarDonador" -> donador.eliminar(id);
            case "cambiarDonador" -> donador.actualizar((Donador) objeto);
            case "consultarTDonador" -> objeto = donador.buscarTodos();
            case "insertarVoluntario" -> voluntario.insertar((Voluntario) objeto);
            case "eliminarVoluntario" -> voluntario.eliminar(id);
            case "cambiarVoluntario" -> voluntario.actualizar((Voluntario) objeto);
            case "consultarVoluntario" -> objeto = voluntario.buscar(id);
            case "consultarTVoluntario" -> objeto = voluntario.buscarTodos();
            case "insertarRepresentante" -> representante.insertar((Representante) objeto);
            case "eliminarRepresentante" -> representante.eliminar(id);
            case "cambiarRepresentante" -> representante.actualizar((Representante) objeto);
            case "consultarRepresentante" -> objeto = representante.buscar(id);
            case "consultarTRepresentante" -> objeto = representante.buscarTodos();
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
