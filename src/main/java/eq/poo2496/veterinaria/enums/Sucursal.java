//Autor: Medrano Reyes Julio Cesar
//Descripcion:
package eq.poo2496.veterinaria.enums;

import lombok.Getter; //Esta anotación genera automáticamente métodos get para los campos de la clase

@Getter //Genera autonamitcamente los métodos get
public enum Sucursal { //Declaración de los ENUMS
    SUCURSAL_A("Sucursal A", Zona.ZONA_1),
    SUCURSAL_B("Sucursal B", Zona.ZONA_2),
    SUCURSAL_C("Sucursal C", Zona.ZONA_3);
//Define las constantes enumeradas dentro de Sucursal, que representan diferentes sucursales. Cada constante tiene un nombre y una zona asociada.
    private final String nombre;
    private final Zona zona;
//Declara dos campos finales: nombre, que representa el nombre de la sucursal
    Sucursal(String nombre, Zona zona) {
        this.nombre = nombre;
        this.zona = zona;
        //Define un constructor para el enum Sucursal que toma un nombre y una zona como argumentos y los asigna a los campos correspondientes de la instancia de Sucursal.
    }
}