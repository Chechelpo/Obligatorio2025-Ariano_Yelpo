package ui;

import java.util.Scanner;
import Services.administrativo;

public class Menu {

    public void mostrar() {
        Scanner scanner = new Scanner(System.in);
        administrativo admin = null;

        while (true) {
            System.out.println("Seleccione la opción que desee:");
            System.out.println("1. Carga de datos");
            System.out.println("2. Ejecutar consultas");
            System.out.println("3. Salir");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    long inicioCarga = System.currentTimeMillis();
                    admin = new administrativo();
                    long finCarga = System.currentTimeMillis();
                    System.out.println("Carga de datos exitosa, tiempo de ejecución de la carga: " + (finCarga - inicioCarga) + "ms");
                    break;

                case "2":
                    if (admin == null) {
                        System.out.println("Primero debe cargar los datos.");
                        break;
                    }

                    mostrarMenuConsultas(scanner, admin);
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void mostrarMenuConsultas(Scanner scanner, administrativo admin) {
        while (true) {
            System.out.println("1. Top 5 de las películas que más calificaciones por idioma.");
            System.out.println("2. Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.");
            System.out.println("3. Top 5 de las colecciones que más ingresos generaron.");
            System.out.println("4. Top 10 de los directores que mejor calificación tienen.");
            System.out.println("5. Actor con más calificaciones recibidas en cada mes del año.");
            System.out.println("6. Usuarios con más calificaciones por género");
            System.out.println("7. Salir");

            String opcion = scanner.nextLine();

            if (opcion.equals("7")) return;

            try {
                int nroReporte = Integer.parseInt(opcion);
                long inicio = System.currentTimeMillis();
                admin.executeReport(nroReporte);
                long fin = System.currentTimeMillis();
                System.out.println("Tiempo de ejecución de la consulta: " + (fin - inicio) + "ms");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número.");
            }
        }
    }
}
