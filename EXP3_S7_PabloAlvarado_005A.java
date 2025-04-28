package exp3_s7_pabloalvarado_005a;

/** Experiencia de Aprendizaje 03, Semana 07
 * Fundamentos de Programacion
 * @author Pablo Alvarado Hernandez - 005A
 */

//01.1 Importacion de Librerias

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


//02. Definicion de Clases y Variables

//02.1 Definición de Clase

public class EXP3_S7_PabloAlvarado_005A {

    
//02.2 Definición de Constantes 
    private static final String NOMBRE_TEATRO = "Teatro Moro";
    private static final String[] UBICACIONES = {"VIP", "Platea", "Balcon"};
    private static final double[] PRECIOS = {50000, 30000, 20000};
    private static final double DESCUENTO_ESTUDIANTE = 0.10;
    private static final double DESCUENTO_TERCERA_EDAD = 0.15;

//02.3 Definicion de Variables de Instancia
    private static final ArrayList<String> ventasUbicacion = new ArrayList<>();
    private static final ArrayList<Double> ventasPrecioFinal = new ArrayList<>();
    private static final ArrayList<String> ventasDescuento = new ArrayList<>();

//02.4 Definicion de Variables estaticas
    private static int totalEntradas = 0;
    private static double ingresosTotales = 0.0;
    
    
    
    //03.1 Metodo Principal de funcionamiento del Programa

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> ventaEntrada(scanner);
                case 2 -> mostrarResumenVentas();
                case 3 -> generarBoletas();
                case 4 -> mostrarIngresosTotales();
                case 5 -> {
                    System.out.println("\nGracias por su compra. Vuelva pronto");
                    continuar = false;
                }
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    
    
    //03.2 Metodo de Funcionamiento de Menu
    
    private static void mostrarMenu() {
        System.out.println("\n=== Bienvenido al " + NOMBRE_TEATRO + " ===");
        System.out.println("1. Venta de Entradas");
        System.out.println("2. Visualizar Resumen de Ventas");
        System.out.println("3. Generar Boletas");
        System.out.println("4. Calcular Ingresos Totales");
        System.out.println("5. Salir");
    }

    
    
    //03.3 Metodo de Venta de Entradas
    
    private static void ventaEntrada(Scanner scanner) {
        System.out.println("\nSeleccione ubicacion:");
        for (int i = 0; i < UBICACIONES.length; i++) {
            System.out.println((i + 1) + ". " + UBICACIONES[i] + " - $" + PRECIOS[i]);
        }

        int ubicacionSeleccionada = leerEntero(scanner, "Ingrese el numero de la ubicacion: ") - 1;

        if (ubicacionSeleccionada < 0 || ubicacionSeleccionada >= UBICACIONES.length) {
            System.out.println("Ubicacion invalida.");
            return;
        }

        double precioBase = PRECIOS[ubicacionSeleccionada];
        double descuentoAplicado = 0;
        String tipoDescuento = "Sin descuento";

        
        //03.3.1. Generar condicion para Estudiantes - Tercera Edad
        
        
        System.out.print("Es estudiante? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            descuentoAplicado = DESCUENTO_ESTUDIANTE;
            tipoDescuento = "Descuento Estudiante (10%)";
        } else {
            System.out.print("Es persona de la tercera edad? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                descuentoAplicado = DESCUENTO_TERCERA_EDAD;
                tipoDescuento = "Descuento Tercera Edad (15%)";
            }
        }

        double precioFinal = precioBase * (1 - descuentoAplicado);
        

        //03.3.2 Metodo para guardar venta
        
        ventasUbicacion.add(UBICACIONES[ubicacionSeleccionada]);
        ventasPrecioFinal.add(precioFinal);
        ventasDescuento.add(tipoDescuento);

        totalEntradas++;
        ingresosTotales += precioFinal;

        System.out.println("\nEntrada vendida exitosamente");
    }

    
    
    //04. Metodo para mostrar Detalle de Entradas
    
    private static void mostrarResumenVentas() {
        System.out.println("\n=== Resumen de Ventas ===");
        if (ventasUbicacion.isEmpty()) {
            System.out.println("No hay ventas realizadas todavia.");
            return;
        }

        for (int i = 0; i < ventasUbicacion.size(); i++) {
            System.out.printf("%d. Ubicacion: %s | Precio Final: $%.0f | %s%n",
                    (i + 1), ventasUbicacion.get(i), ventasPrecioFinal.get(i), ventasDescuento.get(i));
        }
    }

    
    
    
    //05. Metodo para Generar Boletas
    
    private static void generarBoletas() {
        System.out.println("\n=== Boletas Generadas ===");
        if (ventasUbicacion.isEmpty()) {
            System.out.println("No hay ventas para generar boletas.");
            return;
        }

        for (int i = 0; i < ventasUbicacion.size(); i++) {
            String ubicacion = ventasUbicacion.get(i);
            double costoBase = obtenerCostoBase(ubicacion);
            double precioFinal = ventasPrecioFinal.get(i);
            String descuento = ventasDescuento.get(i);

            System.out.println("\n--------------------------------");
            System.out.println("         Boleta de Compra        ");
            System.out.println("--------------------------------");
            System.out.println("Teatro: " + NOMBRE_TEATRO);
            System.out.println("Ubicacion: " + ubicacion);
            System.out.printf("Costo Base: $%.0f%n", costoBase);
            System.out.println("Descuento Aplicado: " + descuento);
            System.out.printf("Costo Final: $%.0f%n", precioFinal);
            System.out.println("--------------------------------");
            System.out.println("Gracias por preferirnos");
        }
    }

    
    
    //06. Metodo para Mostrar Cantidad de Ingresos
    
    private static void mostrarIngresosTotales() {
        System.out.println("\n=== Ingresos Totales ===");
        System.out.println("Total de entradas vendidas: " + totalEntradas);
        System.out.printf("Ingresos totales: $%.0f%n", ingresosTotales);
    }

    
    
    
    //07. Metodos auxiliares
    
    //07.1. Metodo para obtener costo base
    
    private static double obtenerCostoBase(String ubicacion) {
        for (int i = 0; i < UBICACIONES.length; i++) {
            if (UBICACIONES[i].equalsIgnoreCase(ubicacion)) {
                return PRECIOS[i];
            }
        }
        return 0;
    }

    
    //07.2. Metodo para Leer Numeros enteros
    private static int leerEntero(Scanner scanner, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Ingrese un numero.");
                scanner.nextLine(); // limpiar entrada erronea
            }
        }
    }
}

//Fin del Programa