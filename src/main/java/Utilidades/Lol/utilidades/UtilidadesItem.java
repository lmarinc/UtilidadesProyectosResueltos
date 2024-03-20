package Utilidades.Lol.utilidades;

import modelos.Item;
import modelos.Personaje;

public class UtilidadesItem {

    public UtilidadesItem() {
    }

    public static void equiparItem(Personaje personaje, Item item){

        personaje.getEquipamiento().add(item);
        personaje.setAtaque(personaje.getAtaque() + item.getAumentoDaño());
        personaje.setDefensa(personaje.getDefensa() + item.getAumentoDefensa());
        personaje.setVida(personaje.getVida() + item.getAumentoSalud());
        personaje.setMana(personaje.getMana() + item.getAumentoMana());
    }






}
