import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class test {

    public static void main(String[] args) {
        
        String jdbcUrl = "jdbc:oracle:thin:@192.168.1.57:1521/freepdb1"; 
        String usuario = "universidad";
        String contraseña = "universidad123";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

 
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a Oracle!");
            
            String sql = "INSERT INTO circulo(nombre, valor) VALUES (?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, "Círculo Oro");
            stmt.setDouble(2, 55000.00);
            if(stmt.executeUpdate()==1){
                System.out.println("Ejecucion sql");
            }
           

            // Cerrar conexión
            conexion.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el driver JDBC de Oracle.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectarse a Oracle:");
            e.printStackTrace();
        }
    }
}