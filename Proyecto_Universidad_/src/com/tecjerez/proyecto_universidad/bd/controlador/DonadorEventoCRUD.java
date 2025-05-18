/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecjerez.proyecto_universidad.bd.controlador;

import com.tecjerez.proyecto_universidad.bd.conexionDB;
import com.tecjerez.proyecto_universidad.bd.modelo.DonadorEvento;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author ed308
 */
public class DonadorEventoCRUD implements DAODonadorEvento{

    @Override
    public void insertar(DonadorEvento t) {
        String sql = "INSERT INTO donadorEvento (iddonador, idevento, donativo) VALUES (?,?,?)";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, t.getIdDonador());
            ps.setInt(2, t.getIdEvento());
            ps.setDouble(3, t.getDonativo());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donativo agregado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar donativo", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actualizar(DonadorEvento t) {
        String sql = "UPDATE donadorEvento SET iddonador = ?, idevento = ?, donativo = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            ps.setInt(1, t.getIdDonador());
            ps.setInt(2, t.getIdEvento());
            ps.setDouble(3, t.getDonativo());
            ps.setInt(4, t.getId());

            if (ps.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donativo actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el donativo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar donativo", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM donadorEvento WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Donativo eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar donativo", "Mensaje", JOptionPane.ERROR_MESSAGE);
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
    
    public DonadorEvento convertir(ResultSet rs, int id) throws SQLException {
        int idDonador = rs.getInt("iddonador");
        int idEvento = rs.getInt("idevento");
        double donativo = rs.getDouble("donativo");

        return new DonadorEvento(id, idDonador, idEvento, donativo);
    }

    @Override
    public DonadorEvento buscar(Integer id) {
        String sql = "SELECT * FROM donadorEvento WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DonadorEvento donadorEvento = null;

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                donadorEvento = convertir(resultSet, id);
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "No se encontró el donativo", "Error", JOptionPane.WARNING_MESSAGE);
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

        return donadorEvento;
    }

    @Override
    public List<DonadorEvento> buscarTodos() {
        String sql = "SELECT * FROM donadorevento";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<DonadorEvento> donadorEventos = new ArrayList<>();

        try {
            preparedStatement = conexionDB.getInstancia().getConexion().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                donadorEventos.add(convertir(resultSet, id));
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

        return donadorEventos;
    }
    
}
