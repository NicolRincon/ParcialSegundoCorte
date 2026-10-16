package co.edu.poli.examen2_Soto.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import co.edu.poli.examen2_Soto.modelo.Credito;
import co.edu.poli.examen2_Soto.modelo.Debito;
import co.edu.poli.examen2_Soto.modelo.Tarjeta;
import co.edu.poli.examen2_Soto.modelo.Titular;
import co.edu.poli.examen2_Soto.servicios.DAOTarjeta;
import co.edu.poli.examen2_Soto.servicios.DAOTitular;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Platform;
import co.edu.poli.examen2_Soto.modelo.EstadoTarjeta;

/**
 * Controlador de la interfaz gráfica para la gestión y control de tarjetas.
 * Maneja las operaciones de consulta, creación y validación de datos en el formulario FXML.
 */
public class ControlFormCard {

	@FXML
	private Button bttConsulta;
	@FXML
	private TextField txtTarjeta1;
	@FXML
	private TextArea txtAreaResultado;
	@FXML
	private Button bttCreacion;
	@FXML
	private TextField txtTarjeta2;
	@FXML
	private DatePicker datepk1;
	@FXML
	private ComboBox<Titular> cmbTitular;
	@FXML
	private ComboBox<EstadoTarjeta> cmbEstado; // HU-01: estado obligatorio
	@FXML
	private RadioButton radio1; // Débito
	@FXML
	private RadioButton radio2; // Crédito
	@FXML
	private ToggleGroup tipo;

	/** Objeto DAO para interactuar con las operaciones de tarjetas en la base de datos. */
	private DAOTarjeta daoTarjeta;

	/** Objeto DAO para gestionar la información de los titulares. */
	private DAOTitular daoTitular;

	/**
	 * Método de inicialización ejecutado automáticamente al cargar la vista FXML.
	 * Configura los DAOs, establece valores por defecto y añade los listeners de validación.
	 */
	@FXML
	private void initialize() {
		daoTarjeta = new DAOTarjeta();
		daoTitular = new DAOTitular();

		datepk1.setValue(LocalDate.now());

		List<Titular> lista = null;
		try {
			lista = daoTitular.readall();
		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}
		cmbTitular.getItems().setAll(lista);
		cmbEstado.getItems().setAll(EstadoTarjeta.values());

		txtTarjeta1.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtTarjeta1);
		});

		txtTarjeta2.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtTarjeta2);
		});

	}

	/**
	 * Maneja el evento de consulta de una tarjeta por su número identificador.
	 * 
	 * @param event Evento de acción disparado por el botón de consulta.
	 */
	@FXML
	private void pressConsulta(ActionEvent event) {
		txtAreaResultado.setText("");
		if (!txtTarjeta1.getText().isBlank() || !txtTarjeta1.getText().isEmpty()) {
			try {
				Tarjeta t = daoTarjeta.readone(txtTarjeta1.getText());

				if (t != null)
					txtAreaResultado.setText(t.toString());
				else
					mostrarAlerta("No existe el numero de tarjeta");
			} catch (Exception e) {
				mostrarAlerta(e.getMessage());
			}
		} else
			mostrarAlerta("Ingrese número de tarjeta");
	}

	/**
	 * Maneja el evento de creación y registro de una nueva tarjeta (Débito o Crédito)
	 * validando que todos los campos requeridos estén completos.
	 * 
	 * @param event Evento de acción disparado por el botón de creación.
	 */
	@FXML
	private void pressCreacion(ActionEvent event) {

		String numero = txtTarjeta2.getText().trim();
		if (numero.isEmpty()) {
			mostrarAlerta("⚠ Ingrese el número de tarjeta.");
			return;
		}

		if (datepk1.getValue() == null) {
			mostrarAlerta("⚠ Seleccione la fecha de expedición.");
			return;
		}
		// La columna fecha_exp es VARCHAR(7): formato MM/yyyy
		String fechaExp = datepk1.getValue().format(DateTimeFormatter.ofPattern("MM/yyyy"));

		Titular titular = cmbTitular.getValue();
		if (titular == null) {
			mostrarAlerta("⚠ Seleccione un titular.");
			return;
		}

		// HU-01: no se permite guardar una tarjeta sin un estado válido
		EstadoTarjeta estado = cmbEstado.getValue();
		if (estado == null) {
			mostrarAlerta("⚠ Seleccione el estado de la tarjeta.");
			return;
		}

		Tarjeta nueva;

		if (radio1.isSelected()) {
			nueva = new Debito(numero, fechaExp, estado, titular, 0.0);
		} else {
			nueva = new Credito(numero, fechaExp, estado, titular, 1000000);
		}

		try {
			String resultado = daoTarjeta.create(nueva);
			mostrarAlerta(resultado);
			if (resultado.startsWith("✔")) {
				limpiarFormCrear();
			}
		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}

	}

	/**
	 * Despliega una ventana emergente de alerta informativa para el usuario.
	 * 
	 * @param mensaje Texto que se mostrará dentro de la alerta.
	 */
	private void mostrarAlerta(String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/**
	 * Limpia y restablece los campos del formulario de creación a sus valores iniciales.
	 */
	private void limpiarFormCrear() {
		txtTarjeta2.clear();
		datepk1.setValue(null);
		cmbTitular.setValue(null);
		cmbEstado.setValue(null);
		radio1.setSelected(true);
	}

	/**
	 * Valida que el texto ingresado en un campo numérico contenga exclusivamente dígitos.
	 * Si se detecta un carácter no válido, marca el borde de rojo y muestra una advertencia.
	 * 
	 * @param txt Campo de texto (TextField) a validar.
	 */
	private void validarSoloNumeros(TextField txt) {
		String texto = txt.getText().trim();
		if (!texto.isBlank()) {
			if (!texto.matches("\\d+")) {
				txtAreaResultado.setText("");
				txt.setStyle("-fx-border-color: red;");
				mostrarAlerta("Solo números son permitidos!");
				txt.setText("");
				Platform.runLater(() -> txt.requestFocus());
			} else {
				txt.setStyle("");
			}
		} else
			txt.setStyle("");
	}

}
