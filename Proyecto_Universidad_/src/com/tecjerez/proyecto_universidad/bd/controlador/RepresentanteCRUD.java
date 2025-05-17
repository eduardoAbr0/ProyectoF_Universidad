package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Representante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RepresentanteCRUD implements DAORepresentante {

    @Override
    public void insertar(Representante r) {
        String sql = "INSERT INTO representante(nombre, primer_apellido, segundo_apellido, telefono, clase) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        
        try{
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getPrimer_apellido());
            ps.setString(3, r.getSegundo_apellido());
            ps.setInt(4, r.getTelefono());
            ps.setInt(5, r.getClase());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Representante agregado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar representante", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    @Override
    public void actualizar(Representante r) {
        String sql = "UPDATE representante SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ?, clase = ? WHERE id = ?";
        PreparedStatement ps = null;
        
        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getPrimer_apellido());
            ps.setString(3, r.getSegundo_apellido());
            ps.setInt(4, r.getTelefono());
            ps.setInt(5, r.getClase());
            ps.setInt(6, r.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Representante actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "No se encontró el representante.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar representante", "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM representante WHERE id = ?";
        PreparedStatement ps = null;
        
        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Representante eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar representante", "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    public Representante convertir(ResultSet rs, int id) throws SQLException {
        String nombre = rs.getString("nombre");
        String primerApellido = rs.getString("primer_apellido");
        String segundoApellido = rs.getString("segundo_apellido");
        int telefono = rs.getInt("telefono");
        int clase = rs.getInt("clase");

        return new Representante(id, nombre, primerApellido, segundoApellido, telefono, clase);
    }

    @Override
    public Representante buscar(Integer id) {
        String sql = "SELECT * FROM representante WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Representante representante = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                representante = convertir(rs, id);
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No se encontró el representante", "Error", JOptionPane.WARNING_MESSAGE);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return representante;
    }

    @Override
    public List<Representante> buscarTodos() {
        String sql = "SELECT * FROM representante";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Representante> representantes = new ArrayList<>();

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                representantes.add(convertir(rs, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return representantes;
    }
}

