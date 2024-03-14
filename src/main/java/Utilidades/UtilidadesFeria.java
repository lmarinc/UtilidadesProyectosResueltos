package utilidades;

import modelos.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesFeria {


    /**
     * Devuelve los productos que devuelve los productos comunes entre todos los caterings pasados (1.5 puntos)
     *
     * @param caterings
     * @return
     */
    public static List<Producto> getProductosCoincidente(List<Catering> caterings){

        if(caterings.isEmpty()){
            return new ArrayList<>();
        }else if(caterings.size() ==1){
            return caterings.get(0).getProductos();
        }else{
            List<Producto> productosSimilares = new ArrayList<>(caterings.get(0).getProductos());

            for(Catering c : caterings.subList(1,caterings.size()-1)){
                productosSimilares.retainAll(c.getProductos());
            }
            return productosSimilares;
        }
    }


    /**
     * Agrupa los productos del catering de una caseta por  tipo (1 punto)
     *
     * @param caseta
     * @return
     */
    public static Map<TipoProducto, List<Producto>> getProductosPorTipo(Caseta caseta){

//        Map<TipoProducto, List<Producto>> mapa = new HashMap<>();
//
//
//        for (Producto p : caseta.getCatering().getProductos()) {
//
//            if(mapa.containsKey(p.getProducto())){
//                mapa.get(p.getProducto()).add(p);
//            }else{
//                mapa.put(p.getProducto(), new ArrayList<>(List.of(p)));
//            }
//        }
//
//        return mapa;

        return caseta.getCatering().getProductos()
                .stream()
                .collect(Collectors.groupingBy(Producto::getProducto));

    }


    /**
     * Filtra los asistentes obteniendo los mayores de edad , es decir los que tienen más de 18 años,
     * agrupándolos por año de nacimiento (2 puntos)
     *
     * @param asistentes
     * @return
     */
    public static Map<Integer, List<Asistente>> getMayoresDeEdadPorAnyoNacimiento(List<Asistente> asistentes){

        Map<Integer, List<Asistente>> mapa = new HashMap<>();

        for(Asistente a: asistentes){
            Integer edad = Period.between(a.getFechaNacimiento(), LocalDate.now()).getYears();
            if(edad >=18){
                Integer anyo = a.getFechaNacimiento().getYear();
                if(mapa.containsKey(anyo)){
                    mapa.get(anyo).add(a);
                }else{
                    mapa.put(anyo,new ArrayList<>(List.of(a)));
                }
            }
        }

        return mapa;
    }


    /**
     * Devuelve la lista de asistentes de la feria que son de alguno de los tipos pasados,
     * y que son socios de más de una caseta. (1.5 puntos)
     *
     * @param feria
     * @return
     */
    public static List<Asistente> getPorTipoYSocios(Feria feria, List<TipoAsistente> tipoAsistentes){

//        List<Asistente> filtrados = new ArrayList<>();
//
//        for(Asistente a : feria.getAsistentes()){
//
//            Integer numCasetasSocio = a.getCasetasMiembro().size();
//
//            if(numCasetasSocio > 1  && tipoAsistentes.contains(a.getTipoAsistente())){
//                filtrados.add(a);
//            }
//        }
//
//        return filtrados;

        return feria.getAsistentes()
                .stream()
                .filter(a -> a.getCasetasMiembro().size()>1 && tipoAsistentes.contains(a.getTipoAsistente()))
                .collect(Collectors.toList());
    }


    /**
     * Devuelve el total de la suma de productos por cada Catering (1 punto)
     *
     * @param caterings
     * @return
     */
    public static Map<Catering, Double> sumImporteProductosCaterind(List<Catering> caterings){

//        Map<Catering, Double> mapa = new HashMap<>();
//
//        for(Catering c : caterings){
//            double total = 0.0;
//            for(Producto p : c.getProductos()){
//                total+= p.getPrecio();
//            }
//
//            mapa.put(c,total);
//        }
//        return mapa;

        return caterings
                .stream()
                .collect(Collectors.toMap(c->c, c-> c.getProductos().stream().mapToDouble(Producto::getPrecio).sum()));
    }


    /**
     * Rellena los datos del resumen del día del asistente (3 puntos)
     *
     * @param asistente
     * @param consumo
     * @param viajesAtraccion
     * @return
     */
    public static  ResumenDia obtenerResumenDia(Asistente asistente, Map<Producto, Integer> consumo, Map<Atraccion, Integer> viajesAtraccion){

        ResumenDia resumenDia = new ResumenDia();

        //A
        resumenDia.setAsistente(asistente);

        //B
        resumenDia.setCantidadProductos(consumo);

        //C
        Map<TipoProducto, Double> totalGastadoPorTipo = new HashMap<>();

        for(Producto p: consumo.keySet()){
            Double total = p.getPrecio() * consumo.get(p);
            if(totalGastadoPorTipo.containsKey(p.getProducto())){
                totalGastadoPorTipo.put(p.getProducto(), totalGastadoPorTipo.get(p.getProducto()) + total);
            }else{
                totalGastadoPorTipo.put(p.getProducto(),  total);
            }
        }

        resumenDia.setTotalGastadoPorTipoProducto(totalGastadoPorTipo);


        //D
        resumenDia.setViajesPorAtraccion(viajesAtraccion);

        //E
        Map<Atraccion, Double> totalGastadoAtraccion = new HashMap<>();

        for(Atraccion a : viajesAtraccion.keySet()){
            //PRECIO * NUMVIAJES
            Double totalAtraccion = a.getPrecioPorViaje() * viajesAtraccion.get(a);
            totalGastadoAtraccion.put(a, totalAtraccion);
        }

        resumenDia.setTotalGastadoPorAtraccion(totalGastadoAtraccion);

        //F
        Double totalDeTotales = 0.0;

        for(Double miniTotal :totalGastadoPorTipo.values()){
            totalDeTotales += miniTotal;
        }

        for(Double minitotal : totalGastadoAtraccion.values()){
            totalDeTotales += minitotal;
        }


        resumenDia.setTotalGastado(totalDeTotales);


        return resumenDia;



    }



}