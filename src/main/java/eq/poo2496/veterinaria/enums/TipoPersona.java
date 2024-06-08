package eq.poo2496.veterinaria.enums;

import lombok.Getter;

@Getter
public enum TipoPersona {
    CLIENTE("Cliente"),
    ASISTENTE("Asistente"),
    GERENTE("Grente"),
    VETERINARIO("Veterinario");

    private final String nombre;

    TipoPersona(String nombre) {
        this.nombre = nombre;
    }

}