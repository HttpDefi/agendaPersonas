package IMF.GestionAgendaAcesoDatos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelos.FicheroAleatorio;
import Modelos.FicheroBinario;
import Modelos.FicheroSecuencial;
import Modelos.MyAlerts;

public class addAlumnoController {

	private static String eleccion;
	
	// PARA CAMBIAR DE PANTALLA
	private Stage stage = new Stage();
	private static Scene scene;

	FicheroSecuencial miFicheroSecuencial = new FicheroSecuencial();
	FicheroBinario miFicheroBinario = new FicheroBinario();
	FicheroAleatorio mificheroAleatorio = new FicheroAleatorio();
	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtTelefono;

	@FXML
	private Button btnAdd, btnLimpiar, btnGoogle;

	///////// EXPRESION REGULAR
	
	Pattern pattern;
	Matcher matcher;
	boolean telefonoCorrecto, nombreCorrecto, apellidoCorrecto;
	
	//////////////
	
	/// MÉTODO PARA ELEGIR SI QUIERES USAR BINARIO, SECUENCIAL O ALEATORIO ///
	
	public static void setEleccion(String eleccionBoton) {
		eleccion = eleccionBoton;
	}
	
	//////////////////////////////////////
	
	@FXML
	public void initialize() throws IOException {

		// SI NO HA RELLENADO TODOS LOS CAMPOS NO PUEDE DARLE AL BOTON
		btnAdd.disableProperty().bind(txtApellidos.textProperty().isEmpty().or(txtNombre.textProperty().isEmpty())
				.or(txtDireccion.textProperty().isEmpty()).or(txtTelefono.textProperty().isEmpty()));
		
		btnGoogle.disableProperty().bind(txtDireccion.textProperty().isEmpty());

	}


	@FXML
	void add(ActionEvent event) {
		
		System.out.println("Eleccion: " + eleccion.toString());
			
			try {
				
				// TELÉFONO
				pattern = Pattern.compile("[0-9]{9}");
				matcher = pattern.matcher(txtTelefono.getText());
				telefonoCorrecto = matcher.matches();
				
				// NOMBRE Y APELLIDOS
				
				pattern = Pattern.compile("^([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)*)$");
				matcher = pattern.matcher(txtNombre.getText());
				nombreCorrecto = matcher.matches();
				
				matcher = pattern.matcher(txtApellidos.getText());
				apellidoCorrecto = matcher.matches();
				
				if(nombreCorrecto && apellidoCorrecto && telefonoCorrecto) {
				
					//// DISTINGUIMOS SI ES SECUENCIAL, BINARIO O ALEATORIO ///
					
					if(eleccion.equals("secuencial")) {
					
						miFicheroSecuencial.addAlumno(txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(), txtTelefono.getText());
					
					}
					
					else if(eleccion.equals("binario")) {
						
						miFicheroBinario.addAlumno(txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(), txtTelefono.getText());
						
					}else if(eleccion.equals("aleatorio")) {
						
						mificheroAleatorio.addAlumno(txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(), txtTelefono.getText());
						
					}
					
					MyAlerts.basicAlert(AlertType.INFORMATION, "COMPLETADO", "ALUMNO AÑADIDO CORRECTAMENTE");
					borrar();
					
					
				}else {
					
					MyAlerts.basicAlert(AlertType.ERROR, "DATOS INCORRECTOS", "POR FAVOR INTRODUZCA DATOS CORRECTOS");
					
					if(!nombreCorrecto) {
						
						txtNombre.setStyle("-fx-border-color: red");
					}else {
						
						txtNombre.setStyle("-fx-border-color: transparent");
					}
					if(!apellidoCorrecto) {
						
						txtApellidos.setStyle("-fx-border-color: red");
					}else {
						
						txtApellidos.setStyle("-fx-border-color: transparent");
					}
					if(!telefonoCorrecto) {
						
						txtTelefono.setStyle("-fx-border-color: red");
					}else {
						
						txtTelefono.setStyle("-fx-border-color: transparent");
					}
					
				}
					
			} catch (Exception e) {
				
				System.out.println("Error en el método add de la clase - addAlumnoController");
				e.printStackTrace();
				
			}
			
		}
		
	
	@FXML
	void limpiarCampos(ActionEvent event) {
		
		
		borrar();
		
	}
	
	@FXML
	void verGoogleMaps(ActionEvent event) throws IOException {

		
	}
	
	
    ///// MÉTODOS NO DE JAVAFX //////
    
    public void borrar() {
    	
    	txtNombre.clear();
    	txtApellidos.clear();
    	txtDireccion.clear();
    	txtTelefono.clear();
    	
    	txtNombre.setStyle("-fx-border-color: transparent");
		txtApellidos.setStyle("-fx-border-color: transparent");
		txtDireccion.setStyle("-fx-border-color: transparent");
		txtTelefono.setStyle("-fx-border-color: transparent");
    	
    }
    
    /// MÉTODOS PARA CAMBIAR DE PANTALLA ///
    
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
    
	
	
	
}

