package Utilidades;

import modelos.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesF1 {

    public UtilidadesF1() {
    }





    /**
     * Devuelve la lista de pilotos cuya escudería tiene la marca que se pasa como parámetro.
     *
     * @param pilotos
     * @param marca
     * @return
     */
    public static List<Piloto> getPilotosPorMarcaEscuderia(List<Piloto> pilotos, Marca marca){
        return pilotos.stream().filter(p->p.getEscuderia().getMarca().equals(marca)).toList();
    }


    /**
     * Devuelve los pilotos agrupados por escudería
     *
     * @param pilotos
     * @return
     */
    public static Map<Escuderia, List<Piloto>>  pilotosPorEscuderia(List<Piloto> pilotos){
        return pilotos.stream().collect(Collectors.groupingBy(Piloto::getEscuderia));
    }


    /**
     * Devuelvo los coches cuya suma de puntuacion -> (velocidadPunta + aceleracion - tiempoMedioParadaBoxes - probabilidadAveria )
     * es mayor a la que se pasa , ORDENADOR POR PUNTUACIÓN DE MAYOR A MENOR
     *
     * @param coches
     * @param minimoPuntuacionRequerida
     * @return
     */
    public static List<Coche> topMejoresCoches(List<Coche> coches, Double minimoPuntuacionRequerida){
        Double puntuacion = 0D;
        Comparator<Coche> comparator = Comparator.comparing(c->c.getVelocidadPunta() + c.getAceleracion() - c.getProbabilidadAveria() - c.getProbabilidadAveria());

        return coches.stream().filter(c->(c.getVelocidadPunta() + c.getAceleracion() - c.getProbabilidadAveria() - c.getProbabilidadAveria())> minimoPuntuacionRequerida)
                .sorted(comparator.reversed()).toList();
    }


    /**
     * Devuelve el porcentaje de victoria de un piloto , que se calcula con la siguiente fórmula:
     *
     * -> puntuación total coche del su escuderia  (velocidadPunta + aceleracion - tiempoMedioParadaBoxes - probabilidadAveria)
     * -> +
     * -> puntosRanking de su escuderia
     * -> +
     * -> puntosRanking piloto
     *
     * @param piloto
     * @return
     */
    public static Double porcentajeVictoriaPiloto(Piloto piloto){
        Double puntuacionTotal = piloto.getEscuderia().getCoche().getVelocidadPunta() + piloto.getEscuderia().getCoche().getAceleracion() -  piloto.getEscuderia().getCoche().getProbabilidadAveria() -  piloto.getEscuderia().getCoche().getTiempoMedioParadaBoxes();

        return puntuacionTotal + piloto.getPuntosRanking() + piloto.getEscuderia().getPuntosRanking();

    }


    /**
     * Devuelve el piloto con mayor porcentaje de victoria de los dos que se pasa,
     * el porcentaje de victoria se calcula del ejercicio anterior.
     *
     */
     public static Piloto getMejorPiloto(Piloto piloto1, Piloto piloto2){

         Double puntuacion1 = UtilidadesF1.porcentajeVictoriaPiloto(piloto1);
         Double puntuacion2 = UtilidadesF1.porcentajeVictoriaPiloto(piloto2);

         if(puntuacion1>puntuacion2){
             return piloto1;
         }else return piloto2;
    }


    /**
     * Devuelve el mapa de las posiciones obtenidas por las escuderías del ranking de la temporada que se pasa como parámetro,
     * teniendo en cuenta que sólo hay un ranking por temporada.
     *
     * Las claves del mapa son el orden obtenido de mayor a menor , tras ordenar las temporadas del ranking por su "posicionEnRanking"
     *
     * @param rankingEscuderias
     * @param temporada
     * @return
     */
    public static Map<Integer,Escuderia> getRankigPorEscuderias(List<RankingEscuderias> rankingEscuderias, Integer temporada){
        return rankingEscuderias.stream().filter(r->r.getTemporada().equals(temporada)).flatMap(e->e.getEscuderias().stream()).collect(Collectors.toMap(Escuderia::getPosicionEnRanking,e->e));
    }


    /**
     * Devuelve un mapa de los pilotos con la suma de puntos que tengan de las carreras que se pasa como parámetro.
     * Los puntos se obtienen sacando la posición en la que queda el piloto del mapa de "posiciones" y de los puntos
     * que correspondan a esa posición en el mapa "puntosPorPosicion"
     *
     * @param carreras
     * @return
     */
    public static Map<Piloto, Double> totalPuntuacion(List<Carrera> carreras) {

        Map<Piloto, Double> mapaFinal = new HashMap<>();

        for(Carrera c : carreras){
            for(Integer i :c.getPosiciones().keySet()){
                if(mapaFinal.containsKey(c.getPosiciones().get(i))){
                    mapaFinal.put(c.getPosiciones().get(i),mapaFinal.get(c.getPosiciones().get(i))+c.getPuntosPorPosicion().get(i));
                }else{
                    mapaFinal.put(c.getPosiciones().get(i),c.getPuntosPorPosicion().get(i));
                }
            }
        }

        return mapaFinal;


//        Map<Piloto, Double> mapaFinal = new HashMap<>();
//
//        for (Carrera c : carreras) {
//            for (Integer i : c.getPosiciones().keySet()) {
//                Piloto piloto = c.getPosiciones().get(i);
//                Double puntosPorCarrera = c.getPuntosPorPosicion().get(i);
//                mapaFinal.merge(piloto, puntosPorCarrera, Double::sum);
//            }
//        }
//
//        return mapaFinal;
//    }
    }

}
