package database;

import libs.ConexionBD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EstructuraBD {
    public static void crearBD() {
        try (Connection miCon = ConexionBD.conectar()) {
            //todo comentar
            if (miCon != null) {
                DatabaseMetaData meta = miCon.getMetaData();
                System.out.println("Base de datos creada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void crearTablas() {
        crearTablaCentros();
        crearTablaEspecialidad();
        crearTablaAsignaturas();
        crearTablaProfesores();
        crearTablaAsigprof();
    }
    public static void borrarBD() {
        Path p = Path.of("target/escuela.dat");

        if(p.toFile().exists()) {
            try {
                Files.delete(p);
                System.out.println("Base de datos borrada");
            } catch (IOException e) {
                System.err.println(">>> Error: Error al borrar la base de datos");
            }
        } else {
            System.err.println(">>> Error: Ruta introducida no existe");
        }
    }


    //Creacion de las tablas
    public static void crearTablaCentros() {
        try(Connection miCon = ConexionBD.conectar()) {
            //Sentencias SQL para crear tabla asigprof
            String tablaCentros = """
                    CREATE TABLE C1_CENTROS (
                        COD_CENTRO NUMERIC(4) NOT NULL PRIMARY KEY ,
                        NOM_CENTRO VARCHAR(20),
                        DIRECTOR NUMERIC(4),
                        DIRECCION VARCHAR(25),
                        LOCALIDAD VARCHAR(20),
                        PROVINCIA VARCHAR(20)
                    );""";

            //Sentencias SQL para añadir valores a la tabla
            List<String> addCentros = Arrays.asList(
                    "INSERT INTO C1_CENTROS VALUES (1000,'IES El Quijote', 1000, 'Avda. Los Molinos 25', 'GUADALAJARA', 'GUADALAJARA');",

                    "INSERT INTO C1_CENTROS VALUES (1015,'CP Los Danzantes', 1010, 'C/Las Musas s/n','PASTRANA', 'GUADALAJARA');",

                    "INSERT INTO C1_CENTROS VALUES (1022, 'IES Planeta Tierra',2000, 'C/Mina 45', 'AZUQUECA', 'GUADALAJARA');",

                    "INSERT INTO C1_CENTROS VALUES (1045, 'CP Manuel Hidalgo', NULL, 'C/Granada 5', 'GUADALAJARA', 'GUADALAJARA');",

                    "INSERT INTO C1_CENTROS VALUES (1050, 'IES Antoñete', NULL, 'C/Los Toreros 21', 'SIGUENZA', 'GUADALAJARA');");

            //Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaCentros = miCon.createStatement();

            //Borrado de la tabla antes de volver a crearla
            crearTablaCentros.executeUpdate("DROP TABLE IF EXISTS C1_CENTROS");
            crearTablaCentros.executeUpdate(tablaCentros);

            //Se añade los contenidos a la tabla
            for (String centro : addCentros) {
                crearTablaCentros.executeUpdate(centro);
            }
        }catch (SQLSyntaxErrorException e) {
            System.err.println("Error en la sintaxis de la sentencia SQL (1)" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos (1)" + e.getMessage());
        }catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos (1)");
        }
    }   //1
    public static void crearTablaEspecialidad() {
        try(Connection miCon = ConexionBD.conectar()) {
            //Sentencias SQL para crear tabla asigprof
            String tablaEspecialidad = """
                    CREATE TABLE C1_ESPECIALIDAD (
                        ESPECIALIDAD CHAR(2) NOT NULL PRIMARY KEY,
                        NOMBRE_ESPE VARCHAR(25)
                    );""";

            //Sentencias SQL para añadir valores a la tabla
            List<String> addEspecialidad = Arrays.asList(
                    "insert into C1_ESPECIALIDAD VALUES ('IF','Informática');",
                    "insert into C1_ESPECIALIDAD VALUES ('IN','Inglés');",
                    "insert into C1_ESPECIALIDAD VALUES ('FQ','Física y Química');",
                    "insert into C1_ESPECIALIDAD VALUES ('GH','Geografía e Historia');",
                    "insert into C1_ESPECIALIDAD VALUES ('TG','Tecnología');",
                    "insert into C1_ESPECIALIDAD VALUES ('LG','Lengua');",
                    "insert into C1_ESPECIALIDAD VALUES ('DB','Dibujo');",
                    "insert into C1_ESPECIALIDAD VALUES ('MT','Matemáticas');");

            //Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaEspecialidades = miCon.createStatement();

            //Borrado de la tabla antes de volver a crearla
            crearTablaEspecialidades.executeUpdate("DROP TABLE IF EXISTS C1_ESPECIALIDAD");
            crearTablaEspecialidades.executeUpdate(tablaEspecialidad);

            //Se añade los contenidos a la tabla
            for (String especialidad : addEspecialidad) {
                crearTablaEspecialidades.executeUpdate(especialidad);
            }
        }catch (SQLSyntaxErrorException e) {
            System.err.println("Error en la sintaxis de la sentencia SQL (2)" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos (2)" + e.getMessage());
        }catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos (2)");
        }
    }   //2
    public static void crearTablaAsignaturas() {
        try(Connection miCon = ConexionBD.conectar()) {
            //Sentencias SQL para crear tabla asigprof
            String tablaAsignaturas = """
                    CREATE TABLE C1_ASIGNATURAS (
                        COD_ASIG CHAR(6) NOT NULL PRIMARY KEY,
                        	NOMBRE_ASI VARCHAR(30)
                    );""";

            //Sentencias SQL para añadir valores a la tabla
            List<String> addAsignatura = Arrays.asList(
                    "insert into C1_ASIGNATURAS VALUES ('IF0001','DAHC');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0002','RAL');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0003','IMSI');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0004','DPEG');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0006','PLE');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0007','FPE');",

                    "insert into C1_ASIGNATURAS VALUES ('LG0001','Lengua 1 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0002','Lengua 2 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0003','Lengua 3 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0004','Lengua 4 ESO');",

                    "insert into C1_ASIGNATURAS VALUES ('DB0001','Plástica');",
                    "insert into C1_ASIGNATURAS VALUES ('DB0002','Taller cerámica');",
                    "insert into C1_ASIGNATURAS VALUES ('DB0003','Dibujo Técnico');",

                    "insert into C1_ASIGNATURAS VALUES ('MT0001','Matemáticas 1 BAC');",
                    "insert into C1_ASIGNATURAS VALUES ('MT0002','Matemáticas 2 BAC');");

            //Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaAsignatura = miCon.createStatement();

            //Borrado de la tabla antes de volver a crearla
            crearTablaAsignatura.executeUpdate("DROP TABLE IF EXISTS C1_ASIGNATURAS");
            crearTablaAsignatura.executeUpdate(tablaAsignaturas);

            //Se añade los contenidos a la tabla
            for (String asignatura : addAsignatura) {
                crearTablaAsignatura.executeUpdate(asignatura);
            }
        }catch (SQLSyntaxErrorException e) {
            System.err.println("Error en la sintaxis de la sentencia SQL (3)" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos (3)" + e.getMessage());
        }catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos (3)");
        }
    }   //3
    public static void crearTablaProfesores() {
        try(Connection miCon = ConexionBD.conectar()) {
            //Sentencias SQL para crear tabla asigprof
            String tablaProfesores = """
                    CREATE TABLE C1_PROFESORES (
                        COD_PROF NUMERIC(4) NOT NULL PRIMARY KEY,\s
                        NOMBRE_APE VARCHAR(30),
                        ESPECIALIDAD CHAR(2) REFERENCES C1_ESPECIALIDAD(ESPECIALIDAD ),
                        JEFE_DEP NUMERIC(4),
                        FECHA_NAC DATE,
                        SEXO CHAR(1),
                        COD_CENTRO NUMERIC(4) NOT NULL REFERENCES C1_CENTROS (COD_CENTRO)
                    );""";

            //Sentencias SQL para añadir valores a la tabla
            List<String> addProfesor = Arrays.asList(
                    "INSERT INTO C1_PROFESORES VALUES (1000, 'Martínez Salas, Fernando', 'IF', 1001, '1961-09-07', 'H', 1000);",
                    "INSERT INTO C1_PROFESORES VALUES (1001, 'Bueno Zarco, Elisa', 'IF',NULL, '1960-02-17', 'M', 1000);",
                    "INSERT INTO C1_PROFESORES VALUES (2002, 'Rivera Silvestre, Ana','DB',3000, '1950-10-10', 'M',1000);",
                    "INSERT INTO C1_PROFESORES VALUES (3000, 'De Lucas Fdez, M.Angel','DB',NULL, '1980-09-09','M',1000);",

                    "INSERT INTO C1_PROFESORES VALUES (1010, 'Montes García, M.Pilar', 'MT', 1011,'1970-10-10', 'M', 1015);",
                    "INSERT INTO C1_PROFESORES VALUES (1011, 'Arroba Conde, Manuel', 'MT', NULL,'1970-10-12', 'H', 1015);",
                    "INSERT INTO C1_PROFESORES VALUES (1022, 'Ruiz Lafuente, Manuel','MT',1011, '1966-11-11', 'H',1015);",

                    "INSERT INTO C1_PROFESORES VALUES (2000, 'Ramos Ruiz, Luis','LG',2003, '1963-08-08', 'H',1022 );",
                    "INSERT INTO C1_PROFESORES VALUES (2003, 'Segura Molina, Irene','LG',NULL, '1963-07-08', 'M',1022 );",
                    "INSERT INTO C1_PROFESORES VALUES (1045, 'Serrano Laguía, María','IF',NULL,'1976-01-02', 'M', 1022)");

            //Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaProfesores = miCon.createStatement();

            //Borrado de la tabla antes de volver a crearla
            crearTablaProfesores.executeUpdate("DROP TABLE IF EXISTS C1_PROFESORES");
            crearTablaProfesores.executeUpdate(tablaProfesores);

            //Se añade los contenidos a la tabla
            for (String profesor : addProfesor) {
                crearTablaProfesores.executeUpdate(profesor);
            }
        }catch (SQLSyntaxErrorException e) {
            System.err.println("Error en la sintaxis de la sentencia SQL (4)" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos (4)" + e.getMessage());
        }catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos (4)");
        }
    }   //4
    public static void crearTablaAsigprof() {
        try(Connection miCon = ConexionBD.conectar()) {
            //Sentencias SQL para crear tabla asigprof
            String tablaAsigprof = """
                    CREATE TABLE C1_ASIGPROF (
                        COD_ASIG CHAR(6) NOT NULL REFERENCES C1_ASIGNATURAS (COD_ASIG),
                        COD_PROF NUMERIC(4) NOT NULL REFERENCES C1_PROFESORES (COD_PROF),
                        PRIMARY KEY (COD_ASIG,COD_PROF)
                    );""";

            //Sentencias SQL para añadir valores a la tabla
            List<String> addAsigprof = Arrays.asList(
                    "insert into C1_ASIGPROF VALUES ('IF0002',1001);",
                    "insert into C1_ASIGPROF VALUES ('IF0003',1001);",
                    "insert into C1_ASIGPROF VALUES ('IF0001',1000);",

                    "insert into C1_ASIGPROF VALUES ('LG0001',2000);",
                    "insert into C1_ASIGPROF VALUES ('LG0002',2000);",
                    "insert into C1_ASIGPROF VALUES ('LG0003',2003);",
                    "insert into C1_ASIGPROF VALUES ('LG0004',2003);",

                    "insert into C1_ASIGPROF VALUES ('DB0001',2002);",
                    "insert into C1_ASIGPROF VALUES ('DB0002',2002);",
                    "insert into C1_ASIGPROF VALUES ('DB0003',3000);",

                    "insert into C1_ASIGPROF VALUES ('MT0001',1010);",
                    "insert into C1_ASIGPROF VALUES ('MT0001',1011);",
                    "insert into C1_ASIGPROF VALUES ('MT0001',1022);",
                    "insert into C1_ASIGPROF VALUES ('MT0002',1010);");

            //Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaAsigprof = miCon.createStatement();

            //Borrado de la tabla antes de volver a crearla
            crearTablaAsigprof.executeUpdate("DROP TABLE IF EXISTS C1_ASIGPROF");
            crearTablaAsigprof.executeUpdate(tablaAsigprof);

            //Se añade los contenidos a la tabla
            for (String asigprof : addAsigprof) {
                crearTablaAsigprof.executeUpdate(asigprof);
            }
        }catch (SQLSyntaxErrorException e) {
            System.err.println("Error en la sintaxis de la sentencia SQL (5)" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos (5)" + e.getMessage());
        }catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos (5)");
        }
    }   //5
}
