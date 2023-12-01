package libs;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    //todo comentar
    private static final String URL = "jdbc:sqlite:target/escuela.dat";

    public static Connection conectar() {
        //todo comentar
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection((URL));
        } catch (SQLException e) {
            System.out.println("Error en la conexión. " + e.getMessage());
        } catch (InvalidModuleDescriptorException e) {
            System.out.println("Error PAM");
        }
        return conexion;
    }
}
