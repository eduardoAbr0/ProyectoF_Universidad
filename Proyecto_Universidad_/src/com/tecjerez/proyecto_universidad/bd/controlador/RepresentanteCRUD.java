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
        String sql = "INSERT INTO representante(nombre, primer_apellido, segundo_apellido, telefono, clase_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
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
        }
    }

    @Override
    public void actualizar(Representante r) {
        String sql = "UPDATE representante SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ?, clase_id = ? WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getPrimer_apellido());
            ps.setString(3, r.getSegundo_apellido());
            ps.setInt(4, r.getTelefono());
            ps.setInt(5, r.getClase());
            ps.setInt(6, r.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Representante actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar representante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM representante WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Representante eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar representante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Representante convertir(ResultSet rs, int id) throws SQLException {
        String nombre = rs.getString("nombre");
        String primerApellido = rs.getString("primer_apellido");
        String segundoApellido = rs.getString("segundo_apellido");
        int telefono = rs.getInt("telefono");
        int clase = rs.getInt("clase_id");

        return new Representante(id, nombre, primerApellido, segundoApellido, telefono, clase);
    }

    @Override
    public Representante buscar(Integer id) throws SQLException {
        String sql = "SELECT * FROM representante WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return convertir(rs, id);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el representante", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
    }

    @Override
    public List<Representante> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM representante";
        List<Representante> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                lista.add(convertir(rs, id));
            }
        }
        return lista;
    }
}

