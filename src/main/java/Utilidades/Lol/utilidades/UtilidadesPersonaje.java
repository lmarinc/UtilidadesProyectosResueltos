package Utilidades.Lol.utilidades;

import modelos.Personaje;
import modelos.Region;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesPersonaje {


    public UtilidadesPersonaje() {
    }

    public static void levelTo(Personaje personaje, Integer nivel){

        personaje.setNivel(nivel);
        actualizarStasAlNivelActual(personaje);

    }

    public static Personaje levelUp(Personaje personaje){


        personaje.setNivel(personaje.getNivel() + 1);
        actualizarStasAlNivelActual(personaje);


        return personaje;


    }

    private static void actualizarStasAlNivelActual(Personaje personaje) {
        personaje.setAtaque(personaje.getAtaqueBase() + (personaje.getEscalabilidad().getIncrementoDañoNivel() * personaje.getNivel()));
        personaje.setVida(personaje.getVidaBase() + (personaje.getEscalabilidad().getIncrementoSaludNivel() * personaje.getNivel()));
        personaje.setMana(personaje.getManaBase() + (personaje.getEscalabilidad().getIncrementoSaludNivel() * personaje.getNivel()));
        personaje.setDefensa(personaje.getAtaqueBase() + (personaje.getEscalabilidad().getIncrementoDefensaNivel() * personaje.getNivel()));
    }


    public static Map<Region, List<Personaje>> getPersonajesPorRegion(List<Personaje> personajes){
        return personajes.stream().collect(Collectors.groupingBy(Personaje::getRegion));
    }



    public static Personaje getMasPoderoso(List<Personaje> personaje){
        //Leveleando al 18
        personaje.forEach(p-> levelTo(p,18));

        //Calular el más poderoso
        Personaje masPoderoso = personaje
                .stream()
                .max(Comparator.comparing(p-> p.getAtaque() + p.getDefensa() + p .getMana() + p.getVida())).orElse(null);


        return masPoderoso;
    }


    public static Map<Region, Personaje> getMasPoderosoPorRegion(List<Personaje> personajes){

        Map<Region,List<Personaje>> personajesPorRegion = getPersonajesPorRegion(personajes);
        Map<Region,Personaje> personajeMasPoderosoPorRegion = new HashMap<>();
        personajesPorRegion.keySet().forEach(k-> personajeMasPoderosoPorRegion.put(k,getMasPoderoso(personajesPorRegion.get(k))));

        return personajeMasPoderosoPorRegion;


    }







}
