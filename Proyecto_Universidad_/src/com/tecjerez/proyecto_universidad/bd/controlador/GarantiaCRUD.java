
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
        String sql = "INSERT INTO garantia (cantidad, estado, donador, circulo) VALUES (?, ?, ?, ?)";
        String sqlCir = "SELECT obtener_circulo_id(?) FROM DUAL";
        
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sqlCir);
            ps.setDouble(1, g.getCantidad());
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            int cir = rs.getInt(1);
            if (cir != 0) {
                cir = rs.getInt(1);
                rs.close();
                ps.close();
                
                ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
                ps.setDouble(1, g.getCantidad());
                ps.setString(2, g.getEstado());
                ps.setInt(3, g.getDonador());
                ps.setInt(4, cir);
                
                if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Garantia agregada correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al insertar la garantia", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay circulo para asignar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar garantía", "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Garantia g) {
        String sql = "UPDATE garantia SET Cantidad = ?, Estado = ?, Donador = ?, Circulo = ? WHERE id = ?";
        String sqlCir = "SELECT obtener_circulo_id(?) FROM DUAL";
        
        PreparedStatement ps = null;
        
        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sqlCir);
            ps.setDouble(1, g.getCantidad());
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            int cir = rs.getInt(1);
            
            if (cir != 0) {
                cir = rs.getInt(1);
                rs.close();
                ps.close();
                
                ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
                ps.setDouble(1, g.getCantidad());
                ps.setString(2, g.getEstado());
                ps.setInt(3, g.getDonador());
                ps.setInt(4, cir);
                ps.setInt(5, g.getId());
                
                System.out.println("CIR " + cir);
                
                if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Garantia actualizada correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar la garantia", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay circulo para actualizar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar garantia.", "Error", JOptionPane.ERROR_MESSAGE);
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
        String sql = "DELETE FROM garantia WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Voluntario eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar voluntario", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Garantia convertir(ResultSet rs, int id) throws SQLException{
        Garantia g = new Garantia(
            id,
            rs.getString("fecha"),
            rs.getDouble("cantidad"),
            rs.getString("estado"),
            rs.getInt("donador"),
            rs.getInt("circulo")
        );
        
        return g;
    }

    @Override
    public Garantia buscar(Integer id) {
        String sql = "SELECT * FROM garantia WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Garantia garantia = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                garantia = convertir(rs, id);
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No se encontró la garantia", "Error", JOptionPane.WARNING_MESSAGE);
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

        return garantia;
    }

    @Override
    public List<Garantia> buscarTodos() {
        String sql = "SELECT * FROM garantia";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Garantia> garantias = new ArrayList<>();

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                garantias.add(convertir(rs, id));
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

        return garantias;
    }
}

