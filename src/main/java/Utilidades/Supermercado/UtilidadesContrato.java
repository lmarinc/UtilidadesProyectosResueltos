package Utilidades.Supermercado;

import modelos.Contrato;
import modelos.enumerados.TipoContrato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesContrato {

    /**
     * Calcula el salario medio de los contratos de un tipo concreto
     * @param contratos es una lista de contratos
     * @return Map<TipoContrato, Double>
     */
    public static Map<TipoContrato, Double> getSalarioMedioTipoContrato(List<Contrato> contratos){


        Map<TipoContrato, Double> mediaContratoTIpo = new HashMap<>();

        for(TipoContrato tipoContrato : TipoContrato.values()){

            Double salarioTotal = 0.0;
            List<Contrato>  contratosTipo = new ArrayList<>();

            for(Contrato c : contratos){
                if(c.getTipoContrato().equals(tipoContrato)){
                    contratosTipo.add(c);
                    salarioTotal += c.getSalario();
                }
            }

            mediaContratoTIpo.put(tipoContrato, salarioTotal/ contratosTipo.size());

        }
        return mediaContratoTIpo;

    }

    /**
     * Calcula el salario medio de los contratos de un tipo concreto usando Stream
     * @param contratos es una lista de contratos
     * @return Map<TipoContrato, Double>
     */
    public static Map<TipoContrato, Double> getSalarioMedioTipoContratoStream(List<Contrato> contratos) {
        return contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getTipoContrato, Collectors.averagingDouble(Contrato::getSalario)));

    }

    /**
     * Calcula el numero de contratos de un tipo concreto
     * @param contratos es una lista de contratos
     * @return Map<TipoContrato, Integer>
     */
    public static Map<TipoContrato, Integer> getNumContratosPorTipo(List<Contrato> contratos){
        Map<TipoContrato, Integer> numContratoPorTipo = new HashMap<>();

        for(TipoContrato tipoContrato : TipoContrato.values()){
            Integer numTotal = 0;
            List<Contrato>  contratosTipo = new ArrayList<>();

            for(Contrato c : contratos){
                if(c.getTipoContrato().equals(tipoContrato)){
                    contratosTipo.add(c);
                    numTotal +=1;
                }
            }
            numContratoPorTipo.put(tipoContrato,numTotal);

        }
        return numContratoPorTipo;

    }


    /**
     * Calcula el numero de contratos de un tipo concreto usando Stream
     * @param contratos es una lista de contratos
     * @return Map<TipoContrato, Integer>
     */
    public static Map<TipoContrato, List<Contrato> > getListContratosPorTipo(List<Contrato> contratos) {
        Map<TipoContrato, List<Contrato>> contratoPorTipo = new HashMap<>();
        for (TipoContrato tipoContrato : TipoContrato.values()) {

            List<Contrato> contratosTipo = new ArrayList<>();

            for (Contrato c : contratos) {
                if (c.getTipoContrato().equals(tipoContrato)) {
                    contratosTipo.add(c);
                }
            }
            contratoPorTipo.put(tipoContrato, contratosTipo);
        }
        return contratoPorTipo;

    }

    public static Map<TipoContrato, List<Contrato>> getListContratosPorTipoStream(List<Contrato> contratos) {
        return contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getTipoContrato, Collectors.toList()));
    }

    public static Map<TipoContrato, Integer> getNumContratosPorTipoStream(List<Contrato> contratos){
        return contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getTipoContrato, Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));


    }

    }






