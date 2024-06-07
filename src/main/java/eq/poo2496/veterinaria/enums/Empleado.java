package eq.poo2496.veterinaria.enums;

import lombok.Getter;

@Getter
public enum Empleado {
    VETERINARIO("Veterinario"),
    ASISTENTE("Asistente"),
    GERENTE("Grente");

    private final String nombre;

    Empleado(String nombre) {
        this.nombre = nombre;
    }

}
