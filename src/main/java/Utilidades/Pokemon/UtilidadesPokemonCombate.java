package Utilidades.Pokemon;

import modelos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesPokemonCombate {

    private  static  Integer getPuntosEntrenador(Entrenador e){

        return null;
    }

    public static Map<Entrenador,Integer> repartirPokemon(List<Entrenador> entrenadores, List<Pokemon> pokemon) {
        // Inicializar el mapa de jugadores y puntos
        Map<Entrenador,Integer> resultados = new HashMap<>();

        // Calcular el número de pokémon por entrenador
        int numEntrenadores = entrenadores.size();
        int numPokemonPorEntrenador = pokemon.size() / numEntrenadores;

        // Repartir los pokémon equitativamente entre los entrenadores
        int index = 0;
        for (Entrenador entrenador : entrenadores) {
            List<Pokemon> pokemonesAsignados = new ArrayList<>();
            for (int i = 0; i < numPokemonPorEntrenador; i++) {
                pokemonesAsignados.add(pokemon.get(index++));
            }
            entrenador.setEquipoPokemon(pokemonesAsignados);
        }

        // Contar tipos favoritos y asignar puntos
        for (Entrenador entrenador : entrenadores) {
            List<TipoPokemon> tiposFavoritos = entrenador.getTiposPreferidos();
            int puntos = 0;
            for (Pokemon pokemonEntrenador : entrenador.getEquipoPokemon()) {
                if (tiposFavoritos.contains(pokemonEntrenador.getTipos())) {
                    puntos++;
                }
            }
            resultados.put(entrenador, puntos);
        }

        // Devolver el mapa de jugadores y puntos totales del reparto
        return resultados;
    }






    public static void subirAlNivel(Pokemon pokemon , Integer nivel){

        pokemon.setNivel(nivel);
        pokemon.getStats().setAt(pokemon.getStats().getAt()+(2*nivel));
        pokemon.getStats().setDf(pokemon.getStats().getDf()+(2*nivel));
        pokemon.getStats().setSpa(pokemon.getStats().getSpa()+(2*nivel));
        pokemon.getStats().setSpd(pokemon.getStats().getSpd()+(2*nivel));
        pokemon.getStats().setSpdf(pokemon.getStats().getSpdf()+(2*nivel));
        pokemon.getStats().setPs(pokemon.getStats().getPs()+(2*nivel));

//        Integer nivelObtenido = pokemon.getNivel();
//        Integer sumStatsObtenida = pokemon.getStats().getAt() + pokemon.getStats().getDf() +
//                pokemon.getStats().getSpa() + pokemon.getStats().getSpd() + pokemon.getStats().getSpdf() +
//                pokemon.getStats().getPs();
    }



    public static boolean puedeEvolucionar1(Pokemon pokemon){
        return false;

    }




    public static boolean puedeEvolucionar(Pokemon pokemon) {
//        boolean validador = false; // Inicializamos el validador como falso por defecto
//
//        List<LineaEvolutiva> lineaEvolutiva = pokemon.getLineaEvolutiva();
//
//        // Comprobamos si la línea evolutiva del Pokémon tiene más de un Pokémon en ella
//        if (lineaEvolutiva.size() > 1) {
//            // Iteramos sobre la línea evolutiva
//            for (int i = 1; i < lineaEvolutiva.size(); i++) {
//                LineaEvolutiva pokemonEnLinea = lineaEvolutiva.get(i);
//
//                // Comprobamos si el nivel requerido del Pokémon en la línea evolutiva es igual o menor que el del Pokémon actual
//                if (pokemonEnLinea.getNivelParaEvolucionar() <= pokemon.getNivel()) {
//                    validador = true;
//                    break; // Si encontramos un Pokémon que cumple la condición, no necesitamos seguir iterando
//                }
//            }
//        }
//
//        return validador; // Devolvemos el resultado de la comprobación

        return pokemon.getLineaEvolutiva().stream().anyMatch(p->p.getOrden()>1 && p.getNivelParaEvolucionar()<=pokemon.getNivel());
    }


 
    public static Map<Entrenador, Integer> asignarEquipoPorTipos(List<Pokemon> pokemons, List<Entrenador> entrenadores) {


        return new HashMap<>();

    }


    private  static  Integer getPuntosEntrenador(Entrenador e, Pokemon pokemon){
      return null;
    }



    private  static  Entrenador dameElQueMenosTenga(List<Entrenador> entrenadors){
       return null;
    }


    public List<Movimiento> movimientosQuePuedeAprender1(Pokemon pokemon, List<Movimiento> movimientos){
       return new ArrayList<>();
    }

    public List<Movimiento> movimientosQuePuedeAprender(Pokemon pokemon, List<Movimiento> movimientos){

        return movimientos.stream().filter(movimiento -> pokemon.getTipos().contains(movimiento.getTipo())).collect(Collectors.toList());

    }

    public static Pokemon obtenerEvolucionPosible(Pokemon pokemon) {
        Pokemon pokemonEvolucionado = pokemon.getLineaEvolutiva().get(1).getPokemon();

        if (UtilidadesPokemonCombate.puedeEvolucionar(pokemon)) {
            return pokemonEvolucionado;
        } else {
            return null;

        }
    }



    public  static Map<Entrenador, Integer> coincidencias(List<Entrenador> entrenadors, List<TipoPokemon> tipos){

        Map<Entrenador, Integer> resultado = new HashMap<>();


        for(Entrenador e: entrenadors){
            Integer favoritos = 0;
            List<TipoPokemon> tiposFavoritos = new ArrayList<>();

            for(TipoPokemon t: tipos){
                if(e.getTiposPreferidos().contains(t)){
                    favoritos++;
                    tiposFavoritos.add(t);
                }
            }
            resultado.put(e, favoritos);
        }
     return resultado;

    }






}
