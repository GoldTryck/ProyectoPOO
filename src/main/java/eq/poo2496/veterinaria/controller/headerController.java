package eq.poo2496.veterinaria.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class headerController {
    @Autowired
    MainController mainController;

    //Metodos para cargar las vistas de Mascota: registro, adopcion y devolucion
    public void registroMascota(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/altaMascota.fxml");
    }
    public void adopcion(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/altaAdopcion.fxml");
    }
    public void devolucion(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/altaDevolucion.fxml");
    }

    //Metodos para cargar las vistas de Personal: alta, baja y consulta
    public void altaPersonal(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/altaPersonal.fxml");
    }
    public void bajaPersonal(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/personalBaja.fxml");
    }
    public void consultaPersonal(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/personalConsulta.fxml");
    }

    //Metodos para cargar las vistas de Servicio
    public void altaServicio(ActionEvent actionEvent) {
        mainController.cargaData("/eq/poo2496/veterinaria/altaServicio.fxml");
    }
}
