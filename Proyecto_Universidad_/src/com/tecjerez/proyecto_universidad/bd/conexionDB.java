
package com.tecjerez.proyecto_universidad.bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
    private static conexionDB instancia;
    protected Connection conexion;

    private conexionDB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String connectionUrl = "jdbc:oracle:thin:@192.168.1.57:1521/freepdb1";

            String user = "universidad";
            String password = "universidad123";

            conexion = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("Conexión exitosa con Oracle VM");
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en el DRIVER");
        } catch (SQLException e) {
            //System.out.print(e);
            System.out.println("Error en la URL");
        }
    }
    public static conexionDB getInstancia(){
        if(instancia == null){
            instancia = new conexionDB();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void desconectar(){
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
            }
        }

    }
}
