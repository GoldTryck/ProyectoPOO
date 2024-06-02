package eq.poo2496.veterinaria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

@Component
public class Controller {

    @FXML
    public HBox cental_content;
    public HBox inside_content;

    @FXML
    private void MascotaView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/mascotaView.fxml"));
            HBox subCont = loader.load();

            cental_content.getChildren().clear();
            cental_content.getChildren().add(subCont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void PersonalView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/personalView.fxml"));
            HBox subCont = loader.load();

            cental_content.getChildren().clear();
            cental_content.getChildren().add(subCont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ConsultaView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/consultaView.fxml"));
            HBox subCont = loader.load();

            cental_content.getChildren().clear();
            cental_content.getChildren().add(subCont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void CitaView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/citaView.fxml"));
            HBox subCont = loader.load();

            cental_content.getChildren().clear();
            cental_content.getChildren().add(subCont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clienteView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/clienteView.fxml"));
            HBox subCont = loader.load();

            cental_content.getChildren().clear();
            cental_content.getChildren().add(subCont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void registroMascota(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/eq/poo2496/veterinaria/registroMascota.fxml"));
        HBox innerContent = loader.load();

        inside_content.getChildren().clear();
        inside_content.getChildren().add(innerContent);
    }

    @FXML
    private void limpiarEscena() {
        // Limpia el contenido del contenedor principal
        cental_content.getChildren().clear();
    }
}
