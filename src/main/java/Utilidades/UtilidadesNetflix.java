package Utilidades;

import modelos.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesNetflix {



    /**
     *
     * Devuelve todas las películas cuyo género y país coninciden con los pasados
     * y cuya duración es mayor a la que se manda
     *
     * @param peliculas
     * @param pais
     * @param genero
     * @param duracionMinima
     * @return
     */
    public static List<Pelicula> getPorGeneroPaisDuracion(List<Pelicula> peliculas, String pais, Genero genero, int duracionMinima){
//        List<Pelicula> listaFinal = new ArrayList<>();
//        for(Pelicula p : peliculas){
//            if(p.getGenero().equals(genero) && p.getPais().equals(pais) && p.getDuracion()>duracionMinima){
//                listaFinal.add(p);
//            }
//
//        }
//
//        return listaFinal;
        return peliculas.stream().filter(p->p.getPais().equals(pais) && p.getGenero().equals(genero) && p.getDuracion()>duracionMinima).collect(Collectors.toList());

//
//
    }


    /**
     * Devuelve si el plan de subscripcion del usuario es válido para ser válido tiene que cumplir lo siguiente
     *
     * -> Que la fecha de inicio sea anterior o igual a la actual.
     * -> Que la fecha de fin coincida sumandole a la fecha inicio los meses del tipo de plan.
     * -> Que la fecha de fin sea posteiror a la fecha actual.
     *
     * @param usuario
     * @return
     */
    public static boolean  tienePlanDeSubscripcionValido(Usuario usuario){

        LocalDate fechaActual = LocalDate.now();

        //AÑADIR CONDICIONES AL MESES;
        Integer meses =0;
        if(usuario.getPlanSubscripcion().getTipo().equals(Tipo.ANUAL)){
            meses = 12;
        }else if(usuario.getPlanSubscripcion().equals(Tipo.CUATRIMESTRAL)){
            meses = 4;
        }else if(usuario.getPlanSubscripcion().equals(Tipo.TRIMESTRAL)) {
            meses = 3;
        }else{
            meses =1;
        }

        if((usuario.getPlanSubscripcion().getFechaInicio().isBefore(fechaActual) || usuario.getPlanSubscripcion().getFechaFin().isEqual(fechaActual)) &&
                (usuario.getPlanSubscripcion().getFechaFin().isEqual(usuario.getPlanSubscripcion().getFechaInicio().plusMonths(meses))) &&
                (usuario.getPlanSubscripcion().getFechaFin().isAfter(fechaActual))
        ){
            return true;
        }
            return false;

    }


    /**
     * Devuelvo de los capítulos pasados, los que son de la temporada indicada, ordenados por orden.
     * Finalmente establece a la temporada los capítulos ordenados.
     *
     * @param temporada
     * @param capitulos
     * @return
     */
    public static List<Capitulo> ordenarCapitulosTemporada(Temporada temporada, List<Capitulo> capitulos){

//        List<Capitulo> listaOrdenada = new ArrayList<>();
//        for(Capitulo c : capitulos){
//            if(c.getTemporada().equals(temporada)){
//                listaOrdenada.add(c);
//            }
//        }
        return capitulos.stream().sorted(Comparator.comparing(Capitulo::getOrden)).collect(Collectors.toList());

    }


//





    /**
     * Devuelve los capítulos de la serie asociados a su número de temporada
     *
     * @param serie
     * @return
     */
    public static Map<Integer, List<Capitulo>> CapitulosPorNumeroTemporada(Serie serie){

        return serie.getTemporadas().stream()
                .collect(Collectors.groupingBy(Temporada::getNumTemporada,
                        Collectors.flatMapping(temporada -> temporada.getCapitulos().stream(),
                                Collectors.toList())));

    }


    /**
     * Devuelve el mapa de las series del género pasado, con sus capítulos asociados a su número de temporada
     *
     * @param series
     * @param genero
     * @return
     */
    public static Map<Serie,Map<Integer, List<Capitulo>>> CapitulosPorNumeroTemporadaSerieGenero(List<Serie> series, Genero genero){


        return series.stream().filter(s->s.getGenero().equals(genero)).collect(Collectors.toMap(serie -> serie,UtilidadesNetflix::CapitulosPorNumeroTemporada));
    }


    /**
     * Devuelve el mapa de la media de valoracion por serie, que se calcula de las valoraciones aportadas
     * que pertenezcan a la serie o capítulos de la serie y calcule la media todas sus valoraciones por serie
     *
     * @param series
     * @param valoracions
     * @return
     */
    public static Map<Serie, Double> mediaValoracion(List<Serie> series,List<Valoracion> valoracions){

      return series.stream()
              .collect(Collectors.toMap(serie -> serie,
                      serie -> valoracions.stream()
                              .filter(valoracion -> valoracion.getSerie().equals(serie))
                              .mapToInt(Valoracion::getRating)
                              .average()
                              .orElse(0.0)));




    }
}
