package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Donador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CRUDDonador implements DAODonador {

    @Override
    public void insertar(Donador d) {
        String sql = "INSERT INTO donador (nombre, primer_apellido, segundo_apellido, telefono, tipo, calle, num_casa, cp, colonia, representante, voluntario, clase) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getPrimer_apellido());
            ps.setString(3, d.getSegundo_apellido());
            ps.setInt(4, d.getTelefono());
            ps.setString(5, d.getTipo());
            ps.setString(6, d.getCalle());
            ps.setInt(7, d.getNum_casa());
            ps.setInt(8, d.getCp());
            ps.setString(9, d.getColonia());
            ps.setInt(10, d.getRepresentante());
            ps.setInt(11, d.getVoluntario());
            ps.setInt(12, d.getClase());
            
            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donador agregado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar donador.");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Donador d) {
        String sql = "UPDATE donador SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ?, tipo = ?, calle = ?, num_casa = ?, cp = ?, colonia = ?, representante = ?, voluntario = ?, clase = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getPrimer_apellido());
            ps.setString(3, d.getSegundo_apellido());
            ps.setInt(4, d.getTelefono());
            ps.setString(5, d.getTipo());
            ps.setString(6, d.getCalle());
            ps.setInt(7, d.getNum_casa());
            ps.setInt(8, d.getCp());
            ps.setString(9, d.getColonia());
            ps.setInt(10, d.getRepresentante());
            ps.setInt(11, d.getVoluntario());
            ps.setInt(12, d.getClase());
            ps.setInt(13, d.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donador actualizado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar donador.");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM donador WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donador eliminado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar donador.");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Donador convertir(ResultSet rs, int id) throws SQLException {
        Donador d = new Donador(
            id,
            rs.getString("nombre"),
            rs.getString("primer_apellido"),
            rs.getString("segundo_apellido"),
            rs.getInt("telefono"),
            rs.getString("tipo"),
            rs.getString("calle"),
            rs.getInt("num_casa"),
            rs.getInt("cp"),
            rs.getString("colonia")
        );

        d.setRepresentante(rs.getInt("representante"));
        d.setVoluntario(rs.getInt("voluntario"));
        d.setClase(rs.getInt("clase"));

        return d;
    }

    @Override
    public Donador buscar(Integer id){
        String sql = "SELECT * FROM donador WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Donador d = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                d = convertir(rs, id);
            }else {
                SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "No se encontró registro", "Error", JOptionPane.WARNING_MESSAGE);
            });
            }
        }catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }  
        }

        return d;
    }

    @Override
    public List<Donador> buscarTodos(){
        String sql = "SELECT * FROM donador";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                donadores.add(convertir(rs, id));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        }
            
        }
        
        return donadores;
    }
}

