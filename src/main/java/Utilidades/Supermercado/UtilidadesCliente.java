package Utilidades.Supermercado;

import modelos.Cliente;

public class UtilidadesCliente {

    /**
     * Comprueba si el dni de un cliente es valido
     * @param cliente
     * @return boolean
     */

    public static boolean esDniValido(Cliente cliente){

        if (cliente.getDni() == null){
            return false;
        }
        String dni = cliente.getDni();

        //12345678M
        //comprobacion tamaño y parte numerica
        boolean tamanyoValido = dni.length() == 9;
        String parteNumerica = dni.substring(0,8);
        boolean sonNumeros = parteNumerica.matches("[0-9]{8}");

        String letra = dni.substring(8);
        boolean esLetra = letra.matches("[a-zA-Z]");

        return tamanyoValido && sonNumeros && esLetra;

    }
}
