package eq.poo2496.veterinaria.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class sideBarController{
    @Autowired
    private MainController mainC;

    public ToggleButton mainMenu;
    public ToggleButton cliente;
    public ToggleButton mascota;
    public ToggleButton personal;
    public ToggleButton servicio;
    public ToggleButton cita;
    public Button salir;
    public ToggleButton pago;
    public ToggleButton paquete;

    @FXML
    public void initialize() {
        cliente.setOnAction(actionEvent -> {
                mainC.cargaMenu("/eq/poo2496/veterinaria/menuCliente.fxml");
                mainC.cargaData("/eq/poo2496/veterinaria/altaCliente.fxml");
        });
        mascota.setOnAction(actionEvent -> mainC.cargaMenu("/eq/poo2496/veterinaria/menuMascota.fxml"));
        personal.setOnAction(actionEvent -> mainC.cargaMenu("/eq/poo2496/veterinaria/menuPersonal.fxml"));
        servicio.setOnAction(actionEvent -> mainC.cargaMenu("/eq/poo2496/veterinaria/menuServicio.fxml"));
        cita.setOnAction(actionEvent -> mainC.cargaMenu("/eq/poo2496/veterinaria/menuCita.fxml"));
        //pago.setOnAction(actionEvent -> loadMenu("/eq/poo2496/veterinaria/pagoMenu.fxml"));
        paquete.setOnAction(actionEvent -> {
            mainC.cargaMenu("/eq/poo2496/veterinaria/menuPaquete.fxml");
        });

    }
    @FXML
    public void endApp() {
        System.exit(0);
    }

}
