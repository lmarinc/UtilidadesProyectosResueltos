package Utilidades.Lol.utilidades;

import com.opencsv.CSVReader;
import modelos.*;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilidadesFichero {

    public static final char SEPARATOR=',';
    public static final char QUOTE='"';
    public static final String filePath = new File("").getAbsolutePath();


    public static List<Personaje> leerPersonajes() {

        List<Personaje> personaje = new ArrayList<>();
        CSVReader reader = null;


        try {
            reader = new CSVReader(new FileReader(filePath + "\\src\\main\\java\\documentos\\personajes.csv"),SEPARATOR,QUOTE);
            String[] nextLine= null ;
            int count = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(count >0) {
                    String[] valores = nextLine;
                    Personaje p = new Personaje();
                    p.setId(Integer.parseInt(valores[0]));
                    p.setNombre(valores[1]);
                    p.setFechaCreacion(LocalDate.parse(valores[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    p.setAtaqueBase(Double.valueOf(valores[3]));
                    p.setDefensaBase(Double.valueOf(valores[4]));
                    p.setVidaBase(Double.valueOf(valores[5]));
                    p.setManaBase(Double.valueOf(valores[6]));
                    p.setRegion(Region.valueOf(valores[7]));
                    personaje.add(p);
                }

                count++;
            }

            reader.close();

        } catch (Exception e) {
           System.out.println(e.toString());
        }

        return  personaje;

    }




    public static List<Item> leerItems() {

        List<Item> items = new ArrayList<>();
        CSVReader reader = null;


        try {
            reader = new CSVReader(new FileReader(filePath + "\\src\\main\\java\\documentos\\items.csv"),SEPARATOR,QUOTE);
            String[] nextLine= null ;
            int count = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(count >0) {
                    String[] valores = nextLine;
                    Item i = new Item();
                    i.setId(Integer.parseInt(valores[0]));
                    i.setNombre(valores[1]);
                    i.setAumentoDaño(Double.valueOf(valores[2]));
                    i.setAumentoDefensa(Double.valueOf(valores[3]));
                    i.setAumentoSalud(Double.valueOf(valores[4]));
                    i.setAumentoMana(Double.valueOf(valores[5]));
                    items.add(i);
                }

                count++;
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return  items;

    }




    public static List<Personaje> leerYAprenderHabilidades() {

        List<Personaje> personajes = leerPersonajes();
        Map<Integer,List<Habilidad>> habilidadesPorPersonaje = leerHabilidades();
        personajes.stream().filter(p-> habilidadesPorPersonaje.containsKey(p.getId())).forEach(p-> p.setHabilidad(new ArrayList<>(habilidadesPorPersonaje.get(p.getId()))));
        return  personajes;

    }


    private static  Map<Integer,List<Habilidad>> leerHabilidades() {
        Map<Integer,List<Habilidad>> mapaHabilidades = new HashMap<>();
        CSVReader reader = null;


        try {
            reader = new CSVReader(new FileReader(filePath + "\\src\\main\\java\\documentos\\habilidades.csv"),SEPARATOR,QUOTE);
            String[] nextLine= null ;
            int count = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(count >0) {
                    String[] valores = nextLine;
                    Habilidad h = new Habilidad();
                    Integer idPersonaje = Integer.valueOf(valores[1]);
                    h.setId(Integer.parseInt(valores[0]));
                    h.setNombre(valores[2]);
                    h.setDañoBase(Double.valueOf(valores[3]));
                    h.setCosteMana(Double.valueOf(valores[4]));
                    h.setTipoHabilidad(TipoHabilidad.valueOf(valores[5]));

                    if(!mapaHabilidades.containsKey(idPersonaje)){
                        mapaHabilidades.put(idPersonaje, new ArrayList<>());
                    }

                    mapaHabilidades.get(idPersonaje).add(h);
                }

                count++;
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return mapaHabilidades;
    }


}
