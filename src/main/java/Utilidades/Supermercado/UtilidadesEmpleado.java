package Utilidades.Supermercado;

import modelos.Empleado;
import modelos.Empresa;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesEmpleado {
    public static Map<String, List<Empleado>> getEmpleadosPorLetraDNI(List<Empleado> empleados) {
        return empleados.stream()
                .collect(Collectors.groupingBy(e -> e.getDni().substring(8),Collectors.toList()));
    }
    public static Map<Empresa, List<Empleado>> getEmpleadosPorEmpresaStream(List<Empleado> empleados) {
//        return empleados.stream().sorted(Comparator.comparing(empresa -> empresa.getIdentificador())).collect(Collectors.groupingBy(Empleado::getEmpresa));
        return empleados.stream()
                .sorted(Comparator.comparing(Empleado::getEmpresa, Comparator.comparing(Empresa::getIdentificador)))
                .collect(Collectors.groupingBy(
                        Empleado::getEmpresa,
                        LinkedHashMap::new,  // Conserva el orden de inserción
                        Collectors.toList()
                ));

    }
}
