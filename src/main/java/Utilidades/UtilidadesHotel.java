package Utilidades;

import enumerados.FranjaEdad;
import enumerados.ServicioHotel;
import enumerados.TipoHabitacion;
import modelos.Habitacion;
import modelos.Hotel;
import modelos.Reserva;
import modelos.Viajero;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesHotel {


    /**
     * EJERCICIO 1 (1 punto)
     * Devuelve los hoteles en cuyos servicios se encuentran al menos tres de los servicios requeridos
     *
     * @param hoteles
     * @param serviciosRequeridos
     * @return
     */
    public static List<Hotel> getConAlMenos3Servicios(List<Hotel> hoteles, List<ServicioHotel> serviciosRequeridos){
        return hoteles.stream().filter(h->h.getServicios().stream().filter(serviciosRequeridos::contains).count()>=3).toList();
    }


    /**
     * EJERCICIO 2 (1 punto)
     * Devuelve el número de reservas por tipo de habitación a parir de una lista de habitaciones.
     *
     * @param habitaciones
     * @return
     */
    public static Map<TipoHabitacion,Integer> getNumReservasPorTipoHabitacion(List<Habitacion> habitaciones){
        return habitaciones.stream().collect(Collectors.groupingBy(Habitacion::getTipoHabitacion,Collectors.summingInt(h->h.getReservas().size())));
    }


    /**
     * EJERCICIO 3 (1.5 puntos)
     *
     * Devuelve un mapa con el número de viajeros por franja de edad, teniendo en cuenta su fecha de nacimiento,
     * las franjas de edad son las siguientes:
     * BABY -> 0-5 años
     * INFANTIL -> 6-12 años
     * JUVENIL -> 13-17 años
     * ADULTO -> 18-70 años
     * ANCIANO -> +70 años
     *
     * @param viajeros
     * @return
     */
    public static Map<FranjaEdad,Integer> getNumViajerosPorFranjaEdad(List<Viajero> viajeros){
        LocalDate fechaActual = LocalDate.now();
        return viajeros.stream()
                .collect(Collectors.groupingBy(v -> calcularFranjaEdad(v.getFechaNacimiento(), fechaActual),
                        Collectors.summingInt(v -> 1)));
//        Map<FranjaEdad,Integer> mapaFinal = new HashMap<>();
//        for(Viajero viajero : viajeros){
//            if(mapaFinal.containsKey(calcularFranjaEdad(viajero.getFechaNacimiento(),fechaActual))){
//                mapaFinal.put(calcularFranjaEdad(viajero.getFechaNacimiento(),fechaActual),mapaFinal.get(calcularFranjaEdad(viajero.getFechaNacimiento(),fechaActual))+1);
//            }
//        }
//        return mapaFinal;
    }

    private static FranjaEdad calcularFranjaEdad(LocalDate fechaNacimiento, LocalDate fechaActual) {
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();
        if (edad <= 5) {
            return FranjaEdad.BABY;
        } else if (edad <= 12) {
            return FranjaEdad.INFANTIL;
        } else if (edad <= 17) {
            return FranjaEdad.JUVENIL;
        } else if (edad <= 70) {
            return FranjaEdad.ADULTO;
        } else {
            return FranjaEdad.ANCIANO;
        }
    }


    /**
     * EJERCICIO 4 (1.5 puntos)
     * Devuelve true si la habitación que se pasa como parámetro no tiene ninguna reserva en el rango de días que se pasa
     * como parámetro a través de la fecha inicio y fin que se pasa
     *
     * @param fechaInicio
     * @param fechaFin
     * @param habitacion
     * @return
     */
    public static boolean habitacionDisponibleFechas(LocalDate fechaInicio , LocalDate fechaFin, Habitacion habitacion){
        return habitacion.getReservas().stream().noneMatch(r->r.getFechaInicio().isBefore(fechaFin) && r.getFechaFin().isAfter(fechaInicio));
    }


    /**
     * EJERCICIO 5 (2.5 puntos)
     * Crea una reserva teniendo en cuenta los siguientes aspectos:
     * - El código de la reserva de rellenará con un código aleatorio único que empiece por "CR"
     * - El hotel de la reserva es el hotel pasado como parámetro
     * - La fecha de inicio de la reserva es la fechaInicio pasada como parámetro
     * - La fecha de fin de la reserva es la fechaFin pasada como parámetro
     * - La habitación de la reserva es la habitación que se pasa como parámetro
     * - El número de días de la reserva se calcula hayando el número de días que hay entre la fecha inicio y la fechaFin
     * - El precio de la reserva se calcula multiplicando el precio del tipo habitación de la habitacion seleccionada para el hotel de la reserva,
     *     multiplicado por el número de días y el número de viajeros.
     *
     * @param hotel
     * @param habitacion
     * @param fechaInicio
     * @param fechaFin
     * @param viajeros
     * @return
     */
    public static Reserva realizarReserva(Hotel hotel, Habitacion habitacion,LocalDate fechaInicio, LocalDate fechaFin, List<Viajero> viajeros){
        Reserva reserva = new Reserva();
        reserva.setCodigo("CR"+(int)(Math.random()*1000));
        reserva.setHotel(hotel);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setHabitacion(habitacion);
        reserva.setNumDias((int)(fechaFin.toEpochDay()-fechaInicio.toEpochDay())+1);
        reserva.setPrecioTotal(hotel.getTipoHabitacionPrecio().get(habitacion.getTipoHabitacion())*reserva.getNumDias()*viajeros.size());
        return reserva;
    }


    /**
     * EJERCICIO 6 (2.5 puntos)
     *
     * Devuelve true si todas las reservas que se pasan como parámetro tienen su precio bien calculado, según la fórmula
     *  del ejercicio anterior, y además el número de viajeros de la reserva no supera el máximo de huéspedes de ese tipo de habitación para el hotel de la reserva.
     *  Si cualquiera de las reservas que se pasa como parámetro no cumple las conciones este método devolverá false
     *
     * @param reservas
     * @return
     */
    public static boolean reservasCorrectas(List<Reserva> reservas){
//        Boolean validador = true;
//
//        for(Reserva r : reservas){
//            if(precioCorrecto(r)){
//                validador = false;
//                break;
//            }
//            if(viajerosCorrectos(r)){
//                validador = false;
//                break;
//            }
//        }
//        return validador;
        return reservas.stream().noneMatch(r->precioCorrecto(r) || viajerosCorrectos(r));
    }

    private static boolean viajerosCorrectos(Reserva r) {
        return r.getViajeros().size() > r.getHotel().getTipoHabitacionNumPersonas().get(r.getHabitacion().getTipoHabitacion());
    }

    private static boolean precioCorrecto(Reserva r) {
        return r.getPrecioTotal() != r.getHotel().getTipoHabitacionPrecio().get(r.getHabitacion().getTipoHabitacion()) * r.getNumDias() * r.getViajeros().size();
    }


}
