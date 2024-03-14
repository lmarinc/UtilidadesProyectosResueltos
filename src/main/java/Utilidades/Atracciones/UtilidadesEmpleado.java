package Utilidades.Atracciones;

import modelos.Empleado;
import modelos.TipoEmpleado;

import java.util.List;
import java.util.stream.Collectors;

public class UtilidadesEmpleado {

    /**
     * Ejercicio 1
     *
     * Que devuelve los empleados que:
     * i. Son coordinadores.
     * ii. Tienen un salario mayor de 1000 euros.
     * iii. Tienen la letra del DNI vocal (A,E,I,O,U).
     *
     * @param empleados
     *
     * @return
     */
    public List<Empleado> getCordinadoresConRestricciones(List<Empleado> empleados){
        //return empleados.stream().filter(e->e.getTipoEmpleado().equals(TipoEmpleado.COORDINADOR) && e.getSalario() > 1000 && e.getDni().substring(8).matches("[a-uA-U]")).collect(Collectors.toList());
        return empleados.stream().filter(e->e.getTipoEmpleado().equals(TipoEmpleado.COORDINADOR) && e.getSalario()>1000 && dniConLetraVocal(e)).collect(Collectors.toList());
    }

    private static boolean dniConLetraVocal(Empleado e) {
        return e.getDni().substring(8).matches("[]");
    }
}
