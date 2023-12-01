import static libs.Leer.pedirCadena;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion = "";
        do {
            System.out.println("""
                    0. Salir
                    "1. Crear Base de Datos
                    "2. Borrar Base de Datos
                    "3. Listar profesores por especialidad
                    "4. Insertar nuevo profesor""");

            opcion = pedirCadena("Introduce una opción");
            switch (opcion) {
                case "0" -> salir = true;
                case "1" -> {
                    database.EstructuraBD.crearBD();
                    database.EstructuraBD.crearTablas();
                }
                case "2" -> database.EstructuraBD.borrarBD();
                case "3" -> {database.ListarProfesores.listarPorEspecialidad();}
                case "4" -> {}
                default -> System.out.println("Opción incorrecta");
            }
        } while (!salir);
    }
}