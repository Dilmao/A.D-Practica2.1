package database;

import libs.ConexionBD;

import java.io.IOException;
import java.sql.*;

public class ListarProfesores {
    // Método para listar profesores basado en la especialida seleccionada
    public static void listarProfesores() {
        // Obtener la especialidad elegida por el usuario
        String especialidad = listarEspecialidades();

        try (Connection miCon = ConexionBD.conectar()) {
            // Consulta SQL para obtener profesores por especialidad
            String profesores = "SELECT * FROM C1_PROFESORES WHERE ESPECIALIDAD = ?";
            PreparedStatement statement = miCon.prepareStatement(profesores);
            statement.setString(1, especialidad);
            ResultSet resultSet = statement.executeQuery();

            // Mostrar los profesores de la especialidad seleccionada
            while (resultSet.next()) {
                int codigoProfesor = resultSet.getInt("COD_PROF");
                String nombreApellido = resultSet.getString("NOMBRE_APE");
                int jefeDep = resultSet.getInt("JEFE_DEP");
                String fechaNac = resultSet.getString("FECHA_NAC");
                String sexo = resultSet.getString("SEXO");
                int codCentro = resultSet.getInt("COD_CENTRO");

                // Imprimir los detalles del profesor
                System.out.println("Codigo: " + codigoProfesor);
                System.out.println("    Nombre: " + nombreApellido);
                System.out.println("    Especialidad: " + especialidad);
                System.out.println("    Jefe de departamento: " + jefeDep);
                System.out.println("    Fecha de nacimiento: " + fechaNac);
                System.out.println("    Sexo: " + sexo);
                System.out.println("    Codigo de centro: " + codCentro + "\n");
            }
        } catch (SQLException e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    // Método para mostrar las especialidades disponibles
    public static String listarEspecialidades() {
        String especialidad = "";
        try (Connection miCon = ConexionBD.conectar()) {
            // Consulta SQL para obtener las especialidades y sus nombres
            String especialidades = "SELECT ESPECIALIDAD, NOMBRE_ESPE FROM C1_ESPECIALIDAD";
            PreparedStatement statement = miCon.prepareStatement(especialidades);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Especialidades: ");
            while (resultSet.next()) {
                // Mostrar al usuario las especialidades disponibles
                String idEsp = resultSet.getString("ESPECIALIDAD");
                String nombreEsp = resultSet.getString("NOMBRE_ESPE");
                System.out.println("    " + idEsp + ". " + nombreEsp);
            }
            especialidad = libs.Leer.pedirCadena("Introduzca el id de la especialidad por la que listar");

        } catch (SQLException e) {
            System.err.println(">>> Error: " + e.getMessage());
        }

        return especialidad;
    }
}
