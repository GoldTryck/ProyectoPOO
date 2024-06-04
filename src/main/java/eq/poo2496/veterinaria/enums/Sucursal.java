package eq.poo2496.veterinaria.enums;

import lombok.Getter;

@Getter
public enum Sucursal {
    SUCURSAL_A("Sucursal A", Zona.ZONA_1),
    SUCURSAL_B("Sucursal B", Zona.ZONA_2),
    SUCURSAL_C("Sucursal C", Zona.ZONA_3);

    private final String nombre;
    private final Zona zona;

    Sucursal(String nombre, Zona zona) {
        this.nombre = nombre;
        this.zona = zona;
    }
}