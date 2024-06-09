package eq.poo2496.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class headerController {
    @Autowired
    MainController mainController;

    //Metodos para cargar las vistas de Mascota: registro, adopcion y devolucion
    public void registroMascota() {
        mainController.cargaData("/eq/poo2496/veterinaria/altaMascota.fxml");
    }
    public void adopcion() {
        mainController.cargaData("/eq/poo2496/veterinaria/altaAdopcion.fxml");
    }
    public void devolucion() {
        mainController.cargaData("/eq/poo2496/veterinaria/altaDevolucion.fxml");
    }

    //Metodos para cargar las vistas de Personal: alta, baja y consulta
    public void altaPersonal() {
        mainController.cargaData("/eq/poo2496/veterinaria/altaPersonal.fxml");
    }
    public void bajaPersonal() {
        mainController.cargaData("/eq/poo2496/veterinaria/bajaPersonal.fxml");
    }
    public void consultaPersonal() {
        mainController.cargaData("/eq/poo2496/veterinaria/consultaPersonal.fxml");
    }

    //Metodos para cargar las vistas de Servicio
    public void altaServicio() {
        mainController.cargaData("/eq/poo2496/veterinaria/altaServicio.fxml");
    }
    public void editarServicio(){ mainController.cargaData("/eq/poo2496/veterinaria/editarServicio.fxml");}
    public void consultaServicio(){ mainController.cargaData("/eq/poo2496/veterinaria/consultaServicio.fxml");}

    public void altaPaquete(){mainController.cargaData("/eq/poo2496/veterinaria/altaPaquete.fxml");}
    public void consultaPaquete(){mainController.cargaData("/eq/poo2496/veterinaria/consultaPaquete.fxml");}
}
