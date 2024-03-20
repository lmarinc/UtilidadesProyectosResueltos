package Utilidades;

import org.example.enums.CargoJunta;
import org.example.enums.DiaSalida;
import org.example.enums.TipoCuota;
import org.example.modelos.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class UtilidadesSemanaSanta {

    /**
     * Ejercicio 1 (1 pt)
     *
     * @param hermandades
     * @param cuotaMaxima
     * @param tipoCuota
     * @return
     */
    public static List<Hermandad> hermandadesConCuota(List<Hermandad> hermandades, Double cuotaMaxima, TipoCuota tipoCuota) {
        return hermandades.stream().filter(h -> h.getCuotaHermano() < cuotaMaxima && h.getTipoCuota().equals(tipoCuota)).collect(Collectors.toList());
    }


    /**
     * Ejercicio 2 (1 pt)
     *
     * @param hermandades
     * @return
     */
    public static Map<DiaSalida, Integer> numHermandadesPorDiaSalida(List<Hermandad> hermandades) {
        return hermandades.stream().collect(Collectors.groupingBy(Hermandad::getDiaSalida, Collectors.summingInt(v -> 1)));
    }


    /**
     * Ejercico 3 (2 pts)
     *
     * @param hermandad
     * @return
     */
    public static Map<Hermano, Integer> hermanosConMayorAntiguedad(Hermandad hermandad) {
        LocalDate fechaActual = LocalDate.now();
        //Map<Hermano,Integer> mapaFechaInscripcion = hermandad.getHermanos().stream().collect(h->h.get)

        Map<Hermano, Integer> mapaFinal = new HashMap<>();
        Map<Hermano, Integer> mapaFinalFiltrado = new HashMap<>();

        List<Hermano> hermanosOrdenados = hermandad.getHermanos().stream().sorted(Comparator.comparing(Hermano::getFechaInscripcionHermandad)).toList();


        for (Hermano h : hermanosOrdenados) {
            if (mapaFinal.size() < 5) {
                Integer antiguedad = obtenerAntiguedad(h, fechaActual);
                mapaFinal.put(h, antiguedad);

            }

        }

//

        return mapaFinal;


//        personaje.forEach(p-> levelTo(p,18));
//
//        //Calular el más poderoso
//        Hermano masantiguo = hermandad.getHermanos()
//                .stream()
//                .max(Comparator.comparing(obtenerAntiguedad(hermandad.getHermanos(),fechaActual);


    }

    private static int obtenerAntiguedad(Hermano h, LocalDate fechaActual) {
        return Period.between(h.getFechaInscripcionHermandad(), fechaActual).getYears();
    }


    /**
     * Ejercico 4 (2.5  pts)
     *
     * @param hermandad
     * @return
     */
    public static JuntaGobierno elegirJuntaDeGobierno(Hermandad hermandad) {
        LocalDate fechaActual = LocalDate.now();
        JuntaGobierno nuevaJunta = new JuntaGobierno();
        nuevaJunta.setHermandad(hermandad);
        nuevaJunta.setFechaInicioMandato(fechaActual);
        nuevaJunta.setFechaFinMandato(fechaActual.plusYears(4));


        List<Hermano> hermanosOrdenados = new ArrayList<>(hermandad.getHermanos().stream().sorted(Comparator.comparing(Hermano::getFechaInscripcionHermandad)).toList());

        Map<CargoJunta, Hermano> mapaJunta = new HashMap<>();
        for (CargoJunta c : CargoJunta.values()) {
            mapaJunta.put(c, hermanosOrdenados.get(0));
            hermanosOrdenados.remove(0);
        }
        nuevaJunta.setPersonalJunta(mapaJunta);

     return nuevaJunta;
    }




    /**
     * Ejercicio 5 (3.5 pts)
     *
     * @param hermandad
     * @return
     */
    public static InformeHermandad informeHermandad(Hermandad hermandad){
        Integer totalHermanos = hermandad.getHermanos().size();
        Integer totalTitulares = hermandad.getTitulares().size();
        Integer totalPasos = hermandad.getPasos().size();




        InformeHermandad informeFinal = new InformeHermandad();
        informeFinal.setHermandad(hermandad);
        informeFinal.setTotalHermanos(totalHermanos);
        informeFinal.setTotalTitulares(totalTitulares);
        informeFinal.setTotalPasos(totalPasos);
        informeFinal.setTotalCostaleros(hermandad.getPasos().stream().mapToInt(e->e.getNumCostaleros()*e.getNumCuadrillas()).sum());
        Map<Paso, Integer> costalerosPorPaso = hermandad.getPasos().stream().collect(Collectors.toMap(h->h,h->h.getNumCostaleros()*h.getNumCuadrillas()));
        informeFinal.setCostalerosPorPaso(costalerosPorPaso);
        Map<Paso, Integer> figurasPorPaso = hermandad.getPasos().stream().collect(Collectors.toMap(h->h,h->h.getTitulares().size()+h.getNumFigurasSegundarias()));
        informeFinal.setPasoTotalFiguras(figurasPorPaso);

        return informeFinal;



    }


}
