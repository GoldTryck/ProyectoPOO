package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.entity.Gerente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.entity.Veterinario;
import eq.poo2496.veterinaria.enums.Sucursal;
import eq.poo2496.veterinaria.service.ClienteService;
import eq.poo2496.veterinaria.service.Services;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

abstract class personaController extends Services {
    @FXML
    protected Button bajapersonal;
    @FXML
    protected Button register;
    @FXML
    protected TextField nombre;
    @FXML
    protected TextField ap;
    @FXML
    protected TextField am;
    @FXML
    protected TextField curp;
    @FXML
    protected DatePicker fn;
    @FXML
    protected TextField cedula;

    @FXML
    protected TextField mascota;
    @FXML
    protected Button consultapersonal;
    @FXML
    protected Button altapersonal;

    @FXML
    protected TextField cmascota;

    protected Sucursal sucursal;

    protected static String nombreS;
    protected static String apS;
    protected static String amS;
    protected static String curpS;
    protected static Date fnD;
    protected static Mascota mascotaM;
    protected static String cedulaS;

    protected static final String validated = "-fx-border-color: green";
    protected static final String notValidated = "-fx-border-color: red";

    abstract void validateFields();
    @FXML
    abstract void registerButton();

    protected Date getFechaNacimiento() {
        LocalDate localDate = fn.getValue();
        if (localDate != null) {
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }
    protected void validateDatePicker(DatePicker datePicker, LocalDate newValue) {
        if (newValue != null && Period.between(newValue, LocalDate.now()).getYears() >= 18) {
            datePicker.setStyle("-fx-border-color: green");
        } else {
            datePicker.setStyle("-fx-border-color: red");
        }
    }
    protected void validateName(TextField field, String newValue) {
        if (newValue.matches("^[a-zA-Z]{2,19}(\\s[a-zA-Z]{2,19})?$")) {
            field.setStyle("-fx-border-color: green");
        } else {
            field.setStyle("-fx-border-color: red");
        }
    }
    protected void validateCurp(){
        curp.textProperty().addListener((observable, oldValue, newValue) -> {
            curp.setText(newValue.toUpperCase());
            if (ClienteService.isValidCURP(newValue)) {
                curp.setStyle("-fx-border-color: green");
            } else {
                curp.setStyle("-fx-border-color: red");
            }
        });
    }
    protected void validateText(TextField field){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            field.setText(newValue.toUpperCase());
            validateName(field, newValue);
            validateFields();
        });
    }
    protected void validateFn(){
        fn.valueProperty().addListener((observable, oldValue, newValue) -> {
            validateDatePicker(fn, newValue);
            validateFields();
        });
    }
    protected void validateCedula(){
        cedula.textProperty().addListener((observable, oldValue, newValue) -> {
            cedula.setText(newValue.toUpperCase());
            if(newValue.length() == 8){
                cedula.setStyle(validated);
            }else{
                cedula.setStyle(notValidated);
            }
            validateFields();
        });
    }
    protected void tableListener(TableView table) {
        table.getSelectionModel().
                selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (observable != null) {
                        validateFields();
                    }
                });
    }
    protected Asistente saveAsistente(){
        Asistente asistente = new Asistente();
        asistente.setNombre(nombreS);
        asistente.setApellidoPaterno(apS);
        asistente.setApellidoMaterno(amS);
        asistente.setCurp(curpS);
        asistente.setFechaNacimiento(fnD);
        asistenteS.persistAsistente(asistente);
        return asistente;
    }
    protected Gerente saveGerente(){
        Gerente gerente = new Gerente();
        gerente.setNombre(nombreS);
        gerente.setApellidoPaterno(apS);
        gerente.setApellidoMaterno(amS);
        gerente.setCurp(curpS);
        gerente.setFechaNacimiento(fnD);
        gerente.setSucursal(sucursal);
        gerenteS.persistGerente(gerente);
        return gerente;
    }
    protected Veterinario saveVeterinario(){
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(nombreS);
        veterinario.setApellidoPaterno(apS);
        veterinario.setApellidoMaterno(amS);
        veterinario.setCurp(curpS);
        veterinario.setFechaNacimiento(fnD);
        veterinario.setCedula(cedulaS);
        veterinarioS.persistVeterinario(veterinario);
        return veterinario;
    }
}
