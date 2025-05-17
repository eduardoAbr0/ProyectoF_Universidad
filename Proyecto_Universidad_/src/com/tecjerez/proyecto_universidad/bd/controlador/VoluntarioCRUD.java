package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Voluntario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VoluntarioCRUD implements DAOVoluntario {

    @Override
    public void insertar(Voluntario v) {
        String sql = "INSERT INTO voluntario (Nombre, Primer_Apellido, Segundo_Apellido, Telefono) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, v.getNombre());
            ps.setString(2, v.getPrimer_apellido());
            ps.setString(3, v.getSegundo_apellido());
            ps.setInt(4, v.getTelefono());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Voluntario agregado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar voluntario", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Voluntario v) {
        String sql = "UPDATE voluntario SET Nombre = ?, Primer_Apellido = ?, Segundo_Apellido = ?, Telefono = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, v.getNombre());
            ps.setString(2, v.getPrimer_apellido());
            ps.setString(3, v.getSegundo_apellido());
            ps.setInt(4, v.getTelefono());
            ps.setInt(5, v.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Voluntario actualizado con éxito.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el voluntario.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar voluntario.", "Error", JOptionPane.ERROR_MESSAGE);
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
        String sql = "DELETE FROM voluntario WHERE id = ?";
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

    public Voluntario convertir(ResultSet rs, int id) throws SQLException {
        String nombre = rs.getString("Nombre");
        String primer_apellido = rs.getString("Primer_Apellido");
        String segundo_apellido = rs.getString("Segundo_Apellido");
        int telefono = rs.getInt("Telefono");

        return new Voluntario(id, nombre, primer_apellido, segundo_apellido, telefono);
    }

    @Override
    public Voluntario buscar(Integer id) {
        String sql = "SELECT * FROM voluntario WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Voluntario voluntario = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                voluntario = convertir(rs, id);
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No se encontró el voluntario", "Error", JOptionPane.WARNING_MESSAGE);
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

        return voluntario;
    }

    @Override
    public List<Voluntario> buscarTodos(){
        String sql = "SELECT * FROM voluntario";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voluntario> voluntarios = new ArrayList<>();

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                voluntarios.add(convertir(rs, id));
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

        return voluntarios;
    }
}

