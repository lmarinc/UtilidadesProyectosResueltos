package Utilidades.Supermercado;

import modelos.Almacen;
import modelos.Producto;
import modelos.enumerados.TipoProducto;

import java.util.ArrayList;
import java.util.List;

public class UtilidadesProducto {

    public static List<Producto> getPorTipo(List<Producto> productos, TipoProducto tipo){

        List<Producto> productosConTipo = new ArrayList<>();


        for(Producto producto : productos){
            if(producto.getTipoProducto().equals(tipo)){
                productosConTipo.add(producto);
            }
        }

        return productosConTipo;

    }


    public static List<Producto> getPorAlmacen(List<Producto> productos, Almacen almacen){

        List<Producto> productosFinal = new ArrayList<>();

        for(Producto p : productos){
            if(p.getAlmacen() != null && p.getAlmacen().equals(almacen)){
                productosFinal.add(p);
            }
        }
        return productosFinal;

    }
}
