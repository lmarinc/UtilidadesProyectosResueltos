package Utilidades.Lol.utilidades;

import modelos.Jugador;
import modelos.Partida;
import modelos.Personaje;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UtilidadesPartida {



    public static void inicializarPartida(Partida partida, List<Jugador> participantes, List<Personaje> personajesDisponibles) {

        //Realizar las elecciones
        Map<Jugador, Personaje> elecciones = new HashMap<>();

        participantes.forEach(jugador-> {
            List<Personaje> personajesFavoritosJugador = new ArrayList<>(jugador.getPersonajesFavoritos());
            personajesFavoritosJugador.retainAll(personajesDisponibles);
            Personaje eleccion;
            if(personajesFavoritosJugador.isEmpty()) {
                eleccion = personajesDisponibles.get(0);
            }else{
                eleccion = personajesFavoritosJugador.get(0);
            }
            personajesDisponibles.remove(eleccion);
            elecciones.put(jugador,eleccion);
        });

        partida.setElecciones(elecciones);


        //Asignar los equipos
        Map<Integer, Set<Jugador>> jugadoresPorEquipo = new HashMap<>();
        jugadoresPorEquipo.put(1,new HashSet<>());
        jugadoresPorEquipo.put(2, new HashSet<>());
        participantes.forEach(j -> {
            if (participantes.indexOf(j) % 2 == 0) {
                jugadoresPorEquipo.get(2).add(j);
            } else {
                jugadoresPorEquipo.get(1).add(j);
            }
        });

        partida.setJugadoresPorEquipo(jugadoresPorEquipo);


        //Establecer fechaInicio
        partida.setInicioPartida(LocalDateTime.now());


    }


    public static void finalizarPartida(Partida partida, Integer equipoVencedor) {

        //Establece fechaFin
        partida.setFinPartida(LocalDateTime.now());

        //Duración partida
        partida.setDuracionPartida((int) partida.getInicioPartida().until(partida.getFinPartida(), ChronoUnit.SECONDS));

        //Establece equipo vencedor
        partida.setEquipoVencedor(equipoVencedor);


        //Actualiza Estadisticas
        partida.getJugadoresPorEquipo().get(equipoVencedor).forEach(j ->{
            Personaje campeonElegido = partida.getElecciones().get(j);
            if(j.getPartidasGanadas().containsKey(campeonElegido)){
                j.getPartidasGanadas().put(campeonElegido, j.getPartidasGanadas().get(campeonElegido) +1);
            }else{
                j.getPartidasGanadas().put(campeonElegido,1);
            }

        });




    }
}
