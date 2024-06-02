package eq.poo2496.veterinaria.util;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Utility {


    public static void showDialog(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static <E extends Enum<E>> void cbList(ListView<CheckBox> listView, Class<E> enumType) {
        List<CheckBox> checkboxes = new ArrayList<>();

        for (E enumConstant : enumType.getEnumConstants()) {
            CheckBox checkBox = new CheckBox(enumConstant.name());
            checkBox.setUserData(enumConstant);
            checkboxes.add(checkBox);
        }
        listView.setItems(FXCollections.observableArrayList(checkboxes));
    }

    private static List<String> selectedCB(ListView<CheckBox> selectedCB){
        List<String> selected = new ArrayList<>();
        for (CheckBox checkBox : selectedCB.getItems()) {
            if (checkBox.isSelected()) {
                selected.add(checkBox.getText());
            }
        }
        return selected;
    }
    public static <E extends Enum<E>> ToggleGroup loadRadioButtons(VBox vbox, Class<E> enumType) {
        List<RadioButton> radioButtons = new ArrayList<>();
        ToggleGroup toggleGroup = new ToggleGroup();

        for (E enumConstant : enumType.getEnumConstants()) {
            RadioButton radioButton = new RadioButton(enumConstant.name());
            radioButton.setUserData(enumConstant);
            radioButton.setToggleGroup(toggleGroup);
            radioButtons.add(radioButton);
        }

        vbox.getChildren().addAll(radioButtons);
        return toggleGroup;
    }

    public static List<String>getSelectedCB(ListView<CheckBox> listV){
        return selectedCB(listV);
    }
}
