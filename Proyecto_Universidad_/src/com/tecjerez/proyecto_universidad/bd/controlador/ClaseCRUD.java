/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Clase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ClaseCRUD implements DAOClase {

    @Override
    public void insertar(Clase t) {
        String sql = "INSERT INTO clase(Anio_Clase, Nivel, Carrera) VALUES(TO_DATE(?, 'YYYY'),?,?)";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, t.getAnio_clase());
            ps.setString(2, t.getNivel());
            ps.setString(3, t.getCarrera());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Clase agregada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar clase", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actualizar(Clase t) {
        String sql = "UPDATE clase SET Anio_Clase = TO_DATE(?, 'YYYY'), Nivel = ?, Carrera = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, t.getAnio_clase());
            ps.setString(2, t.getNivel());
            ps.setString(3, t.getCarrera());
            ps.setInt(4, t.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Clase actualizada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la clase", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar clase", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM clase WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Clase eliminada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar clase", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Clase convertir(ResultSet rs, int id) throws SQLException {
        String anio = rs.getString("Anio_Clase");
        String nivel = rs.getString("Nivel");
        String carrera = rs.getString("Carrera");

        return new Clase(id, anio, nivel, carrera);
    }

    @Override
    public Clase buscar(Integer id){
        String sql = "SELECT * FROM clase WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Clase clase = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                clase = convertir(rs, id);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la clase", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar clase", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {  
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        }

        return clase;
    }

    @Override
    public List<Clase> buscarTodos() {
        String sql = "SELECT * FROM clase";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Clase> lista = new ArrayList<>();

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                lista.add(convertir(rs, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar todas las clases", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {  
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        }

        return lista;
    }
}
