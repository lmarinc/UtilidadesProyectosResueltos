package Utilidades.Lol.utilidades;

import modelos.Habilidad;
import modelos.Item;
import modelos.Personaje;

import java.util.ArrayList;
import java.util.List;

public class UtilidadesHabilidad {

    public UtilidadesHabilidad() {
    }


    public static void golpearConHabilidad(Personaje emisor, Personaje receptor, Habilidad habilidadEmisor){


        //SubirPersonajesAl18
        UtilidadesPersonaje.levelTo(emisor,18);
        UtilidadesPersonaje.levelTo(receptor,18);

        //SumarAtributosItems
        if (emisor.getEquipamiento() != null) {
            List<Item> itemsEmisor = new ArrayList<>(emisor.getEquipamiento());
            itemsEmisor.forEach(i-> UtilidadesItem.equiparItem(emisor,i));
        }
        if(receptor.getEquipamiento()!=null){
            List<Item> itemsReceptor = new ArrayList<>(receptor.getEquipamiento());
            itemsReceptor.forEach(i-> UtilidadesItem.equiparItem(receptor,i));
        }

        //PotenciadeHabilidad
        Double dañoHabilidad = habilidadEmisor.getDañoBase() + (0.2 * emisor.getAtaque()) - (0.1 * receptor.getDefensa());

        //RestamosmanaEmisor
        emisor.setMana(emisor.getMana() - habilidadEmisor.getCosteMana());

        //RestamosVidaReceptor
        receptor.setVida(receptor.getVida() - dañoHabilidad);

    }




}
