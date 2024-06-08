package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.enums.Sucursal;
import eq.poo2496.veterinaria.enums.TipoPersona;
import eq.poo2496.veterinaria.service.Services;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class personaController extends Services {
    @FXML
    public Button register;
    @FXML
    public TextField nmbr;
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

    @FXML
    public TableView<Persona> persona;
    @FXML
    public TableColumn<Persona, TipoPersona> tipo;
    @FXML
    public TableColumn<Persona, String> pnombre;
    @FXML
    public TableColumn<Persona, String> pap;
    @FXML
    public TableColumn<Persona, String> pam;


    public static String nombreS;
    public static String apS;
    public static String amS;
    public static String curpS;
    public static Date fnD;
    public static Mascota mascotaM;
    public static String cedulaS;
    List<Persona> combinedList = new ArrayList<>();
    @FXML
    public Pane radioPane;

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
    public void mapTable() {
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        pnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        pap.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        pam.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
    }

    protected Cliente saveCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre(nombreS);
        cliente.setApellidoPaterno(apS);
        cliente.setApellidoMaterno(amS);
        cliente.setCurp(curpS);
        cliente.setFechaNacimiento(fnD);
        cliente.setTipo(TipoPersona.CLIENTE);
        cliente.setMascota(mascotaM);
        clienteS.persistCliente(cliente);
        return cliente;
    }
    protected Asistente saveAsistente(){
        Asistente asistente = new Asistente();
        asistente.setNombre(nombreS);
        asistente.setApellidoPaterno(apS);
        asistente.setApellidoMaterno(amS);
        asistente.setCurp(curpS);
        asistente.setFechaNacimiento(fnD);
        asistente.setTipo(TipoPersona.ASISTENTE);
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
        gerente.setTipo(TipoPersona.GERENTE);
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
        veterinario.setTipo(TipoPersona.VETERINARIO);
        veterinario.setCedula(cedulaS);
        veterinarioS.persistVeterinario(veterinario);
        return veterinario;
    }

    public void clearFields() {
        nmbr.clear(); nmbr.setStyle(null);
        ap.clear(); ap.setStyle(null);
        am.clear(); am.setStyle(null);
        curp.clear(); curp.setStyle(null);
    }
}
