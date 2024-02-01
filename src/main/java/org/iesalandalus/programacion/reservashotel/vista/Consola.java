package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Consola {

    private Consola(){

    }

    public static void mostrarMenu() {

        for (Opcion opcion : Opcion.values()) {

            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {

        System.out.print("Elige una opci�n: ");

        int opcionElegida = Entrada.entero();

        return Opcion.values()[opcionElegida];
    }

    public static Huesped leerHuesped() {

        System.out.print("Introduce el nombre del hu�sped: ");

        String nombre = Entrada.cadena();

        System.out.print("Introduce el DNI del hu�sped: ");

        String dni = Entrada.cadena();

        System.out.print("Introduce el correo del hu�sped: ");

        String correo = Entrada.cadena();

        System.out.print("Introduce el tel�fono del hu�sped: ");

        String telefono = Entrada.cadena();

        System.out.print("Introduce la fecha de nacimiento del hu�sped: ");

        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());

        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Huesped leerHuespedPorDni() {

        System.out.print("Introduce el DNI del hu�sped: ");

        String dni = Entrada.cadena();

        return new Huesped(leerHuesped().getDni());
    }

    public static LocalDate leerFecha() {

        LocalDate fecha = null;

        boolean fechaValida = false;

        while (!fechaValida) {

            System.out.print("Introduce la fecha (formato dd-mm-yyyy): ");
            try {

                fecha = LocalDate.parse(Entrada.cadena());

                fechaValida = true;

            } catch (Exception e) {

                System.out.println("Error: Formato de fecha incorrecto.");
            }
        }
        return fecha;
    }

    public static Habitacion leerHabitacion() {

        System.out.print("Introduce el n�mero de planta de la habitaci�n: ");

        int planta = Entrada.entero();

        System.out.print("Introduce el n�mero de puerta de la habitaci�n: ");

        int puerta = Entrada.entero();

        System.out.print("Introduce el precio de la habitaci�n: ");

        double precio = Entrada.realDoble();

        System.out.println("Introduce el tipo de habitacion: ");

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador() {

        System.out.print("Introduce el identificador de la habitaci�n: ");

        String identificador = Entrada.cadena();

        try {

            return new Habitacion (leerHabitacion().getIdentificador());

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return null; // o puedes lanzar una excepci�n espec�fica aqu� si lo prefieres
        }
    }

    public static TipoHabitacion leerTipoHabitacion() {

        System.out.println("Tipos de habitaci�n:");

        for (TipoHabitacion tipo : TipoHabitacion.values()) {

            System.out.println(tipo);

        }

        System.out.print("Elige un tipo de habitaci�n: ");

        int tipoElegido = Entrada.entero();

        return TipoHabitacion.values()[tipoElegido];
    }

    public static Regimen leerRegimen() {

        System.out.println("Tipos de r�gimen:");

        for (Regimen regimen : Regimen.values()) {

            System.out.println(regimen);
        }

        System.out.print("Elige un tipo de r�gimen: ");

        int regimenElegido = Entrada.entero();

        return Regimen.values()[regimenElegido];
    }

    public static int leerNumeroPersonas() {
        System.out.print("Introduce el n�mero de personas: ");

        while (true) {
            try {
                int numeroPersonas = Entrada.entero();

                if (numeroPersonas <= 0) {

                    throw new IllegalArgumentException("El n�mero de personas debe ser mayor que cero.");
                }

                return numeroPersonas;

            } catch (InputMismatchException e) {

                System.out.println("Error: Debes introducir un n�mero entero.");

                Entrada.cadena();

            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());
            }
        }
    }


    public static Reserva leerReserva() {

        System.out.println("Introduce los datos de la reserva:");

        Huesped huesped = leerHuesped();
        Habitacion habitacion = leerHabitacion();
        Regimen regimen=leerRegimen();
        LocalDate fechaInicioReserva = leerFecha();
        LocalDate fechaFinReserva = leerFecha();
        int numeroPersonas= leerNumeroPersonas();

        return new Reserva(huesped, habitacion, regimen, fechaInicioReserva, fechaFinReserva, numeroPersonas);
    }

}
