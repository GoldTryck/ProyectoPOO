package eq.poo2496.veterinaria;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

@Component
public class Controller {

    @FXML
    public HBox cental_content;

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

    @FXML
    public void endApp() {
        System.exit(0);
    }
}
