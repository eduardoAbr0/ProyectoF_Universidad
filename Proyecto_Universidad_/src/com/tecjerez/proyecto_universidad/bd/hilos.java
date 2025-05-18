package com.tecjerez.proyecto_universidad.bd;

import com.tecjerez.proyecto_universidad.bd.controlador.CRUDDonador;
import com.tecjerez.proyecto_universidad.bd.controlador.CirculoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.ClaseCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.DonadorEventoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.EventoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.GarantiaCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.PagoCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.RepresentanteCRUD;
import com.tecjerez.proyecto_universidad.bd.controlador.VoluntarioCRUD;
import com.tecjerez.proyecto_universidad.bd.modelo.Circulo;
import com.tecjerez.proyecto_universidad.bd.modelo.Clase;
import com.tecjerez.proyecto_universidad.bd.modelo.Donador;
import com.tecjerez.proyecto_universidad.bd.modelo.DonadorEvento;
import com.tecjerez.proyecto_universidad.bd.modelo.Evento;
import com.tecjerez.proyecto_universidad.bd.modelo.Garantia;
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
    PagoCRUD pago = new PagoCRUD();
    CirculoCRUD circulo = new CirculoCRUD();
    GarantiaCRUD garantia = new GarantiaCRUD();
    EventoCRUD evento = new EventoCRUD();
    ClaseCRUD clase = new ClaseCRUD();
    DonadorEventoCRUD donadorEvento = new DonadorEventoCRUD();
    

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
            
            case "insertarGarantia" -> garantia.insertar((Garantia) objeto);
            case "eliminarGarantia" -> garantia.eliminar(id);
            case "cambiarGarantia" -> garantia.actualizar((Garantia) objeto);
            case "consultarGarantia" -> objeto = garantia.buscar(id);
            case "consultarTGarantia" -> objeto = garantia.buscarTodos();
            
            case "insertarCirculo" -> circulo.insertar((Circulo) objeto);
            case "eliminarCirculo" -> circulo.eliminar(id);
            case "cambiarCirculo" -> circulo.actualizar((Circulo) objeto);
            case "consultarCirculo" -> objeto = circulo.buscar(id);
            case "consultarTCirculo" -> objeto = circulo.buscarTodos();
            
            case "insertarPago" -> pago.insertar((Pago) objeto);
            case "eliminarPago" -> pago.eliminar(id);
            case "cambiarPago" -> pago.actualizar((Pago) objeto);
            case "consultarPago" -> objeto = pago.buscar(id);
            case "consultarTPago" -> objeto = pago.buscarTodos();
            
            case "insertarEvento" -> evento.insertar((Evento) objeto);
            case "eliminarEvento" -> evento.eliminar(id);
            case "cambiarEvento" -> evento.actualizar((Evento) objeto);
            case "consultarEvento" -> objeto = evento.buscar(id);
            case "consultarTEvento" -> objeto = evento.buscarTodos();
            
            case "insertarClase" -> clase.insertar((Clase) objeto);
            case "eliminarClase" -> clase.eliminar(id);
            case "cambiarClase" -> clase.actualizar((Clase) objeto);
            case "consultarClase" -> objeto = clase.buscar(id);
            case "consultarTClase" -> objeto = clase.buscarTodos();
            
            case "insertarDonadorEvento" -> donadorEvento.insertar((DonadorEvento) objeto);
            case "eliminarDonadorEvento" -> donadorEvento.eliminar(id);
            case "cambiarDonadorEvento" -> donadorEvento.actualizar((DonadorEvento) objeto);
            case "consultarDonadorEvento" -> objeto = donadorEvento.buscar(id);
            case "consultarTDonadorEvento" -> objeto = donadorEvento.buscarTodos();
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
