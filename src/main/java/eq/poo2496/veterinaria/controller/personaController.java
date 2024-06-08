package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.enums.Sucursal;
import eq.poo2496.veterinaria.service.Services;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class personaController extends Services {
    @FXML
    public Button register;
    @FXML
    public TextField nombre;
    @FXML
    public TextField ap;
    @FXML
    public TextField am;
    @FXML
    public TextField curp;
    @FXML
    public DatePicker fn;
    @FXML
    public TextField cedula;
    @FXML
    public TextField mascota;
    @FXML
    public TextField cmascota;
    @FXML
    public Sucursal sucursal;

    public static String nombreS;
    public static String apS;
    public static String amS;
    public static String curpS;
    public static Date fnD;
    public static Mascota mascotaM;
    public static String cedulaS;
    @FXML
    public HBox radiogroup;

    protected static final String validated = "-fx-border-color: green";
    protected static final String notValidated = "-fx-border-color: red";

    abstract void validateFields();
    @FXML
    abstract void registerButton();

    protected Date getFn() {
        LocalDate localDate = fn.getValue();
        if (localDate != null) {
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }
    protected void isValidDate(DatePicker datePicker, LocalDate newValue) {
        if (newValue != null && Period.between(newValue, LocalDate.now()).getYears() >= 18) {
            datePicker.setStyle("-fx-border-color: green");
        } else {
            datePicker.setStyle("-fx-border-color: red");
        }
    }
    protected void isValidName(TextField field, String newValue) {
        if (newValue.matches("^[a-zA-Z]{2,19}(\\s[a-zA-Z]{2,19})?$")) {
            field.setStyle("-fx-border-color: green");
        } else {
            field.setStyle("-fx-border-color: red");
        }
    }
    protected void isValidCURP(){
        curp.textProperty().addListener((observable, oldValue, newValue) -> {
            curp.setText(newValue.toUpperCase());
            if (validateCURP(newValue)) {
                curp.setStyle("-fx-border-color: green");
            } else {
                curp.setStyle("-fx-border-color: red");
            }
            validateFields();
        });
    }
    protected void isValidText(TextField field){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            field.setText(newValue.toUpperCase());
            isValidName(field, newValue);
            validateFields();
        });
    }
    protected void isValidFn(){
        fn.valueProperty().addListener((observable, oldValue, newValue) -> {
            isValidDate(fn, newValue);
            validateFields();
        });
    }
    protected void isValidCedula(){
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
    private boolean validateCURP(String curp) {
        String CURP_REGEX =
                "^[A-Z][AEIOU][A-Z]{2}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[HM](AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d][0-9]$";

        Pattern pattern = Pattern.compile(CURP_REGEX);
        Matcher matcher = pattern.matcher(curp);
        return matcher.matches();
    }
    protected void tableListener(TableView<?> table) {
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
