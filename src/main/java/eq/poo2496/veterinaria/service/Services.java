package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public abstract class Services {
    @Autowired
    protected MainController main;
    @Autowired
    protected MascotaService mascotaS;
    @Autowired
    protected ClienteService clienteS;
    @Autowired
    protected AsistenteService asistenteS;
    @Autowired
    protected GerenteService gerenteS;
    @Autowired
    protected VeterinarioService veterinarioS;
    @Autowired
    protected ServicioIndividualService servicioIndividualS;
    @Autowired
    protected ServicioService servicioS;
    @Autowired
    protected PaqueteService paqueteS;

}