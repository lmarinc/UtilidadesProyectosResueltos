package Utilidades.Atracciones;

import modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesParqueTematico {


    /**
     *
     * Ejercicio 3
     *
     * Devuelve todos los parques cuyas atracciones contienen alguna que sea para niños, es decir que su tipo
     * sea INFANTIL
     *
     * @param parqueTematicos
     * @return
     */
    public static List<ParqueTematico> getParquesConAtraccionesInfantiles(List<ParqueTematico> parqueTematicos){

//        List<ParqueTematico> listaFinal = new ArrayList<>();
//        for(ParqueTematico p : parqueTematicos){
//            for(Atraccion a : p.getAtracciones()){
//                if(a.getTipo().equals(TipoAtraccion.INFANTIL)){
//                    listaFinal.add(p);
//                }
//
//            }
//
//        }return listaFinal;

        return parqueTematicos.stream().filter(p->p.getAtracciones().stream().anyMatch(a->a.getTipo().equals(TipoAtraccion.INFANTIL))).collect(Collectors.toList());

    }





    /**
     *
     * Ejercicio 4
     *
     * Devuelve todos los parques cuya ubicación coincida con el código postal pasado  y en cuyas atracciones contengan
     * alguna para adultos
     *
     * @param parqueTematicos
     * @return
     */

    public static List<ParqueTematico> getParquesPorUbicacionAdultos(List<ParqueTematico> parqueTematicos, Integer codigoPostal){
        return parqueTematicos.stream().filter(p->p.getUbicacion().getCodigoPostal().equals(codigoPostal) && p.getAtracciones().stream().anyMatch(a->a.getTipo().equals(TipoAtraccion.ADULTOS))).collect(Collectors.toList());
    }

    /**
     *
     * Devuelvo los parques temáticos cuya Tarifas teniendo en cuenta los tipo de persona y el número de
     * personas pasado , no supera el presupuesto pasado
     *
     * @param parqueTematicos
     * @param mapa
     * @param presupuesto
     * @return
     */
    public static List<ParqueTematico> getPosibles(List<ParqueTematico> parqueTematicos,
                                                   Map<TipoPersona, Integer> mapa, Double presupuesto){


        List<ParqueTematico> parqueFinal = new ArrayList<>();


    }






    private static Double calcularPrecioParquePorTipoPersona(TipoPersona tipoPersona, Integer numeroPersonas, ParqueTematico parqueTematico){


        return 0D;

    }


}
