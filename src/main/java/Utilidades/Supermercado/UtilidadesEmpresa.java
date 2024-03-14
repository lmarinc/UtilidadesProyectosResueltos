package Utilidades.Supermercado;

import modelos.Empleado;
import modelos.Empresa;
import modelos.enumerados.TipoContrato;
import modelos.enumerados.TipoEmpresa;

import java.util.*;
import java.util.stream.Collectors;


public class UtilidadesEmpresa {

    public static List<Empleado> getEmpleadosPorContrato(Empresa empresa,TipoContrato tipoContrato) {
        List<Empleado> listaFinal = new ArrayList<>();
        for (Empleado empleado : empresa.getEmpleados()) {
            if (empleado.getContrato().getTipoContrato().equals(tipoContrato)) {
                listaFinal.add(empleado);
            }
        }
        return listaFinal;
    }

    public static List<Empleado> getEmpleadosPorContratoStream(Empresa empresa,TipoContrato tipoContrato) {
        return empresa.getEmpleados().stream().filter(e->e.getContrato().getTipoContrato().equals(tipoContrato)).collect(Collectors.toList());

    }

    public static List<Empleado> getMileuristasOrdenadosPorSalario(Empresa empresa){
        List<Empleado> listaFinal = new ArrayList<>();
        for (Empleado empleado: empresa.getEmpleados()){
            if(empleado.getContrato().getSalario()>=1000){
            listaFinal.add(empleado);
            }
        }
        listaFinal.sort(Comparator.comparing(e-> e.getContrato().getSalario()));
        listaFinal = listaFinal.reversed();

        return listaFinal;
    }
    public static List<Empleado> getMileuristasOrdenadosPorSalarioStream(Empresa empresa){
        return empresa.getEmpleados()
                .stream()
                .filter(e->e.getContrato().getSalario()>=1000)
                .sorted(Comparator.comparing(e->e.getContrato().getSalario()))
                .collect(Collectors.toList())
                .reversed();

    }

    public static Double fondoSalarialEmpresa(Empresa empresa){
        Double salarioTotal = 0.0;
        for(Empleado empleado: empresa.getEmpleados()){
            salarioTotal+= empleado.getContrato().getSalario();
        }
        return salarioTotal;
    }

    public static Double fondoSalarialEmpresaStream(Empresa empresa){
        return empresa.getEmpleados().stream().mapToDouble(e->e.getContrato().getSalario()).sum();
    }

    public static Empleado getMejorPagado(List<Empresa> empresas){
        Empleado mejorPagado = new Empleado();
        Double  mejorSalario = 0.0;

        for(Empresa empresa: empresas){
            for(Empleado empleado: empresa.getEmpleados()){
                if(empleado.getContrato().getSalario()>= mejorSalario){
                    mejorSalario = empleado.getContrato().getSalario();
                    mejorPagado = empleado;
                }

            }
        }
        return mejorPagado;
    }
    public static Empleado getMejorPagadoStream(List<Empresa> empresas) {
        return empresas.stream().flatMap(e -> e.getEmpleados().stream()).sorted(Comparator.comparing(e -> e.getContrato().getSalario())).collect(Collectors.toList()).reversed().get(0);
    }

    public static Empleado getMejorPagado1(List<Empresa> empresas){
        //juntar los empleados de todas las empresas en la misma lista
        List<Empleado> empleados = new ArrayList<>();
        for(Empresa e: empresas){
            empleados.addAll(e.getEmpleados());
        }
        //ordenar por salario
        empleados.sort(Comparator.comparing(e->e.getContrato().getSalario()));
        //Coger el ultimo elemento

        Empleado empleadoMasPagado = empleados.get(empleados.size()-1);
        return empleadoMasPagado;
    }

    public static Map<TipoEmpresa, Integer> getNumEmpresasPorTipo(List<Empresa> empresas){
        Map<TipoEmpresa, Integer> numEmpresaPorTipo = new HashMap<>();


        for(TipoEmpresa tipoEmpresa : TipoEmpresa.values()){
            Integer numTotal = 0;
            List<Empresa>  empresaTipo = new ArrayList<>();

            for(Empresa e: empresas){
                if(e.getTipoEmpresa().equals(tipoEmpresa)){
                    empresaTipo.add(e);
                    numTotal +=1;
                }
            }
            numEmpresaPorTipo.put(tipoEmpresa,numTotal);

        }
        return numEmpresaPorTipo;

    }
    public static Map<TipoEmpresa,Integer> getNumEmpleadosPorTipoEmpresa(List<Empresa> empresas) {
        Map<TipoEmpresa, Integer> numEmpresaPorTipo = new HashMap<>();

        for (TipoEmpresa tipoEmpresa : TipoEmpresa.values()) {
            Integer numTotal = 0;

            for (Empresa e : empresas) {
                if (e.getTipoEmpresa().equals(tipoEmpresa)) {
                    numTotal += e.getEmpleados().size();
                }
            }
            numEmpresaPorTipo.put(tipoEmpresa, numTotal);

        }
        return numEmpresaPorTipo;
    }

    public static Map<TipoContrato, List<Empleado>> getEmpleadosPorTipoContrato(Empresa empresas){
        Map<TipoContrato, List<Empleado>> contratoPorTipo = new HashMap<>();

        for (TipoContrato tipoContrato : TipoContrato.values()) {

            List<Empleado> contratosTipo = new ArrayList<>();

            for (Empleado e : empresas.getEmpleados()) {
                if (e.getContrato().getTipoContrato().equals(tipoContrato)) {
                    contratosTipo.add(e);
                }
            }
            contratoPorTipo.put(tipoContrato, contratosTipo);
        }
        return contratoPorTipo;
    }

    public static Map<Empresa,Map<TipoContrato, List<Empleado>>>getEmpleadosPorTipoContrato(List<Empresa> empresas){
        Map<Empresa,Map<TipoContrato, List<Empleado>>> contratoPorTipo = new HashMap<>();

        for(Empresa e:empresas){
            Map<TipoContrato, List<Empleado>> mapa2 = UtilidadesEmpresa.getEmpleadosPorTipoContrato(e);
            contratoPorTipo.put(e,mapa2);
        }
        return contratoPorTipo;

    }

    public static Map<TipoEmpresa,Integer> getNumEmpleadosPorTipoEmpresaStream(List<Empresa> empresas) {
        return empresas.stream()
                .collect(Collectors.groupingBy(Empresa::getTipoEmpresa,Collectors.collectingAndThen(Collectors.summingLong(empresa -> empresa.getEmpleados().size()),Long::intValue)));


    }

    public static Map<TipoEmpresa,Long> getNumEmpleadosPorTipoEmpresaStream1(List<Empresa> empresas) {
        return empresas.stream()
                .collect(Collectors.groupingBy(Empresa::getTipoEmpresa, Collectors.mapping(empresa -> empresa.getEmpleados(), Collectors.counting())));

    }
    public static Map<TipoContrato, List<Empleado>> getEmpleadosPorTipoContratoStream(Empresa empresas){
        return empresas.getEmpleados().stream().collect(Collectors.groupingBy(empleado -> empleado.getContrato().getTipoContrato()));

    }
    public static Map<Empresa,Map<TipoContrato, List<Empleado>>>getEmpleadosPorTipoContratoStreamLista(List<Empresa> empresas){
        Map<Empresa,Map<TipoContrato, List<Empleado>>> mapa = new HashMap<>();
        empresas.stream().forEach(k->mapa.put(k,getEmpleadosPorTipoContratoStream(k)));
        return mapa;
    }








}
