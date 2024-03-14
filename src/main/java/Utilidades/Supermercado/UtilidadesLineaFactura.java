package Utilidades.Supermercado;

import modelos.Factura;
import modelos.LineaFactura;

import java.time.LocalDate;

public class UtilidadesLineaFactura {

    public static Boolean esFacturaVencida(Factura factura) {


        LocalDate diadehoy = LocalDate.now();

        if (factura.getFechaVencimiento().isBefore(diadehoy)) {
            return true;
        } else {
            return false;
        }
    }

    public static Double calcularBaseFactura(Factura factura){
        Double Total = 0D;
        for(LineaFactura linea: factura.getLineaFactura()){
            Double importePrecio = linea.getProducto().getPrecio();
            Integer cantidad = linea.getCantidad();
            Total += importePrecio * cantidad;
        }
        return Total;
    }

    public static Double calcularTotalAPagar(Factura factura){
        Double totalapagar = (calcularBaseFactura(factura)-factura.getDescuento())*factura.getIva();
        return totalapagar;
    }




}
