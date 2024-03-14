package Utilidades.Atracciones;

import modelos.Entrada;
import modelos.InformeDiarioParque;
import modelos.ParqueTematico;
import modelos.TipoPersona;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesEntrada {

    /**
     * Ejercicio 1
     *
     * Devuelve si la entrada es valida, que lo es si aparece en las tarifas de parque su tarifa correspondiente
     *
     * @param entrada
     * @param parqueTematico
     * @return
     */
    public static boolean esEntradaValida(Entrada entrada, ParqueTematico parqueTematico){
        return parqueTematico.getTarifas().contains(entrada.getTarifa());
    }

    /**
     * Ejercicio 2
     *
     * devuelve un infome con el total de dinero ingresado,el numero de personas por tipo, id y fecha
     *
     * @param entradas
     * @return
     */
    public static InformeDiarioParque InformeDiarioParqueCreacion(List<Entrada> entradas){

        InformeDiarioParque informeDiarioParque = new InformeDiarioParque();

        informeDiarioParque.setId(1);
        informeDiarioParque.setFecha(LocalDate.now());
        Map<TipoPersona,Integer> mapa = entradas.stream().collect(Collectors.groupingBy(e->e.getTarifa().getTipo(), Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        //Map<TipoPersona,Integer> mapa = entradas.stream().collect(Collectors.groupingBy(e->e.getTarifa().getTipo(), Collectors.summingInt(e->1))); otra forma
        informeDiarioParque.setNumeroPersonasPorTipo(mapa);
        informeDiarioParque.setTotalDineroIngresado(entradas.stream().mapToDouble(e->e.getTarifa().getPrecio()).sum());

        return informeDiarioParque;


    }
}
