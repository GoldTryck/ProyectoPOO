/*Author: Larios Ponce Hector
Description: Este código define una excepción personalizada llamada CitaOcupadaException, que extiende de la clase
Exception. La excepción tiene un constructor que acepta un mensaje de error, que se pasa al constructor de la clase
padre Exception. Esta excepción podría ser lanzada cuando se intenta programar una cita en un horario que ya está ocupado.*/

// Definición de una excepción personalizada llamada CitaOcupadaException, que extiende de la clase Exception
package eq.poo2496.veterinaria.exceptions;

// Definición de una excepción personalizada llamada CitaOcupadaException, que extiende de la clase Exception
public class CitaOcupadaException extends Exception {
    public CitaOcupadaException(String message) {
        super(message);
    }
}
