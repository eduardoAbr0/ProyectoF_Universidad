
package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Garantia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GarantiaCRUD implements DAOGarantia {

    @Override
    public void insertar(Garantia g) {
        String sql = "INSERT INTO garantia (fecha, cantidad, estado, donador_id, circulo_id, pago_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, g.getFecha());
            ps.setDouble(2, g.getCantidad());
            ps.setString(3, g.getEstado());
            ps.setInt(4, g.getDonador());
            ps.setInt(5, g.getCirculo());
            ps.setInt(6, g.getPago());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Garantía insertada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar garantía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actualizar(Garantia g) {
        String sql = "UPDATE garantia SET fecha = ?, cantidad = ?, estado = ?, donador_id = ?, circulo_id = ?, pago_id = ? WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, g.getFecha());
            ps.setDouble(2, g.getCantidad());
            ps.setString(3, g.getEstado());
            ps.setInt(4, g.getDonador());
            ps.setInt(5, g.getCirculo());
            ps.setInt(6, g.getPago());
            ps.setInt(7, g.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Garantía actualizada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Garantía no encontrada", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar garantía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM garantia WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Garantía eliminada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la garantía", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar garantía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Garantia convertir(ResultSet rs, int id) throws SQLException {
        Garantia g = new Garantia(
            id,
            rs.getString("fecha"),
            rs.getDouble("cantidad"),
            rs.getString("estado")
        );
        g.setDonador(rs.getInt("donador_id"));
        g.setCirculo(rs.getInt("circulo_id"));
        g.setPago(rs.getInt("pago_id"));
        return g;
    }

    @Override
    public Garantia buscar(Integer id) throws SQLException {
        String sql = "SELECT * FROM garantia WHERE id = ?";
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertir(rs, id);
                } else {
                    JOptionPane.showMessageDialog(null, "Garantía no encontrada", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            }
        }
    }

    @Override
    public List<Garantia> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM garantia";
        List<Garantia> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                lista.add(convertir(rs, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar todas las garantías", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}

