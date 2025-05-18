
package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.Evento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class EventoCRUD implements DAOEvento {

    @Override
    public void insertar(Evento t) {
        String sql = "INSERT INTO evento(Fecha, Nombre) VALUES(TO_DATE(?, 'YYYY-MM-DD'), ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setString(1, t.getFecha());
            preparedStatement.setString(2, t.getNombre());
            
            System.out.println(t.getFecha());

            if (preparedStatement.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Evento agregado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar evento", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actualizar(Evento t) {
        String sql = "UPDATE evento SET Fecha = TO_DATE(?, 'YYYY-MM-DD'), Nombre = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setString(1, t.getFecha());
            preparedStatement.setString(2, t.getNombre());
            preparedStatement.setInt(3, t.getId());

            if (preparedStatement.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Evento actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el evento", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar evento", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM evento WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Evento eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar evento", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Evento convertir(ResultSet rs, int id) throws SQLException {
        String fecha = rs.getString("fecha");
        String nombre = rs.getString("nombre");

        return new Evento(id, nombre, fecha);
    }

    @Override
    public Evento buscar(Integer id) {
        String sql = "SELECT * FROM evento WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Evento evento = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                evento = convertir(resultSet, id);
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No se encontró el evento", "Error", JOptionPane.WARNING_MESSAGE);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return evento;
    }

    @Override
    public List<Evento> buscarTodos(){
        String sql = "SELECT * FROM evento";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Evento> eventos = new ArrayList<>();

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                eventos.add(convertir(resultSet, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return eventos;
    }
}

