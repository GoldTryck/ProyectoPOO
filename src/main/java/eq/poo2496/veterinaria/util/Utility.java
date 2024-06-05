//Autor: Medrano Reyes Julio Cesar
//Descripcion: La clase Utility simplifica la experiencia del usuario al mostrar alertas, crear listas de casillas de verificación, y facilitar la selección de opciones con botones de opción
package eq.poo2496.veterinaria.util;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Utility {


    public static void showDialog(String title, String message, Alert.AlertType type){
        //Declara un método estático llamado showDialog que recibe tres parámetros: title (de tipo String), message (de tipo String) y type (de tipo Alert.AlertType).
        Alert alert = new Alert(type); //Crea una instancia de Alert con el tipo de alerta especificado (type).
        alert.setTitle(title); //Establece el título del cuadro de diálogo con el valor de title.
        alert.setHeaderText(null); //Elimina cualquier texto del encabezado del cuadro de diálogo (lo establece en null).
        alert.setContentText(message); //Establece el mensaje del contenido del cuadro de diálogo con el valor de message.
        alert.showAndWait(); //Muestra el cuadro de diálogo y espera a que el usuario lo cierre antes de continuar con la ejecución del programa.
    }
    public static <E extends Enum<E>> void cbList(ListView<CheckBox> listView, Class<E> enumType) {
        //Declara un método estático genérico cbList que recibe dos parámetros: listView (de tipo ListView<CheckBox>) y enumType (de tipo Class<E>, donde E es un enum).
        List<CheckBox> checkboxes = new ArrayList<>();

        for (E enumConstant : enumType.getEnumConstants()) { //Itera sobre todos los valores constantes del enum enumType.
            CheckBox checkBox = new CheckBox(enumConstant.name()); //Crea un CheckBox con el nombre del valor del enum actual (enumConstant).
            checkBox.setUserData(enumConstant); //Establece los datos de usuario del CheckBox con el valor del enum actual (enumConstant).
            checkboxes.add(checkBox); //Añade el CheckBox a la lista checkboxes.
        }
        listView.setItems(FXCollections.observableArrayList(checkboxes));
        //Establece los CheckBox en la ListView como elementos observables, convirtiendo la lista checkboxes en una lista observable.
    }

    private static List<String> selectedCB(ListView<CheckBox> selectedCB){
        //Declara un método estático privado llamado selectedCB que recibe un parámetro selectedCB (de tipo ListView<CheckBox>).
        List<String> selected = new ArrayList<>(); //Crea una lista vacía de String llamada selected.
        for (CheckBox checkBox : selectedCB.getItems()) { //Itera sobre todos los CheckBox en la ListView selectedCB.
            if (checkBox.isSelected()) { //Si el CheckBox está seleccionado (isSelected retorna true),
                selected.add(checkBox.getText()); //ñade el texto del CheckBox a la lista selected
            }
        }
        return selected; //Retorna la lista de textos de CheckBox seleccionados.
    }
    public static <E extends Enum<E>> ToggleGroup loadRadioButtons(VBox vbox, Class<E> enumType) {
        //Declara un método estático genérico loadRadioButtons que recibe dos parámetros: vbox (de tipo VBox) y enumType (de tipo Class<E>, donde E es un enum)
        List<RadioButton> radioButtons = new ArrayList<>(); //Crea una lista vacía de RadioButton
        ToggleGroup toggleGroup = new ToggleGroup(); //Crea una instancia de ToggleGroup para agrupar los RadioButton.

        for (E enumConstant : enumType.getEnumConstants()) { //Itera sobre todos los valores constantes del enum enumType.
            RadioButton radioButton = new RadioButton(enumConstant.name()); //Crea un RadioButton con el nombre del valor del enum actual (enumConstant).
            radioButton.setUserData(enumConstant); //Establece los datos de usuario del RadioButton con el valor del enum actual (enumConstant).
            radioButton.setToggleGroup(toggleGroup); //Añade el RadioButton al ToggleGroup para que se comporten como un grupo exclusivo.
            radioButtons.add(radioButton); //Añade el RadioButton a la lista radioButtons.
        }

        vbox.getChildren().addAll(radioButtons); //Añade todos los RadioButton al VBox.
        return toggleGroup; //Retorna el ToggleGroup.
    }

    public static List<String>getSelectedCB(ListView<CheckBox> listV){
        return selectedCB(listV);
    }
    //Declara un método estático público llamado getSelectedCB que recibe un parámetro listV (de tipo ListView<CheckBox>).
}
