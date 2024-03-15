package Utilidades.Pokemon;

import modelos.Movimiento;
import modelos.Pokemon;
import modelos.TipoPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesPokemon {

    public UtilidadesPokemon() {
    }

    public static List<Pokemon> obtenerPokemonConTipos(List<Pokemon> pokemons, List<TipoPokemon> tipos){

//        List<Pokemon> listaPokemon = new ArrayList<>();
//
//
//        for(Pokemon p: pokemons){
//
//            List<TipoPokemon> tipopokemon = new ArrayList<>(p.getTipos());
//
//            tipopokemon.retainAll(tipos);
//
//            if(!tipopokemon.isEmpty()){
//                listaPokemon.add(p);
//            }
//
//        }
//
//        return listaPokemon;
//        return pokemons.stream().filter(p -> p.getTipos().stream().anyMatch(tipos::contains)).collect(Collectors.toList());
          return pokemons.stream()
                  .filter(pokemon->pokemon.getTipos().stream().anyMatch(tipos::contains))
                  .collect(Collectors.toList());
    }

    public static List<Pokemon> obtenerPokemonConTipos1(List<Pokemon> pokemons, List<TipoPokemon> tipos){

        return pokemons.stream()
                .filter(pokemon -> pokemon.getTipos().stream()
                        .filter(tipos::contains)
                        .findFirst()
                        .isPresent())
                .collect(Collectors.toList());
    }



    public static Map<TipoPokemon, List<Pokemon>> obtenerPokemonPurosPorTipo(List<Pokemon> pokemons){

        return pokemons.stream().filter(p -> p.getTipos().size() == 1).collect(Collectors.groupingBy(p->p.getTipos().get(0)));

    }

    public static Map<TipoPokemon, List<Pokemon>> obtenerPokemonPurosPorTipo1(List<Pokemon> pokemons){

        return pokemons.stream().filter(p->p.getTipos().size()==1).collect(Collectors.groupingBy(pokemon -> pokemon.getTipos().get(0)));

    }

    public List<Movimiento> movimientosQuePuedeAprender(Pokemon pokemon, List<Movimiento>movimientos){

        return movimientos.stream().filter((m->m.getTipo().equals(pokemon.getTipos().get(0)) || m.getTipo().equals(pokemon.getTipos().get(1)))).collect(Collectors.toList());

    }

}
