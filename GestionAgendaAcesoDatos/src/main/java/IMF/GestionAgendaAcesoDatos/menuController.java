package IMF.GestionAgendaAcesoDatos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Modelos.FicheroXML;
import Modelos.XMLtoHTML;
import javafx.event.ActionEvent;

public class menuController {
	
	// ATRIBUTOS PROPIOS //
	private Stage stage = new Stage();
	private static Scene scene;
	///////////////////////

	@FXML
	private HBox topPanel;

	@FXML
	private Button btnVerALumnos;

	@FXML
	private Button btnRefrescar;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnModificar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnExportarXML;
	
	@FXML
	private Button btnConvertirHTML;

	////////////////////////////////////////////////////////////////
	
	@FXML
	void addAlumnos(ActionEvent event) {

		addAlumnoController.setEleccion("secuencial");
		pantallaAdd();
		
	}
	
	@FXML
	void verAlumnos(ActionEvent event) {

		verAlumnoController.setEleccion("secuencial");
		pantallaVer();
		
	}

	@FXML
	void addBinario(ActionEvent event) {

		addAlumnoController.setEleccion("binario");
		pantallaAdd();
		
	}

	@FXML
	void verBinario(ActionEvent event) {

		verAlumnoController.setEleccion("binario");
		pantallaVer();
		
	}

	@FXML
	void addAleatorio(ActionEvent event) {

		addAlumnoController.setEleccion("aleatorio");
		pantallaAdd();
		
	}

	@FXML
	void verAleatorio(ActionEvent event) {

		verAlumnoController.setEleccion("aleatorio");
		pantallaVer();
		
	}

	@FXML
	void exportarXML(ActionEvent event) throws IOException {
		
		FicheroXML.crearXML();
		
	}
	
	@FXML
	void ConvertirHTML(ActionEvent event) throws IOException, URISyntaxException {
		
		XMLtoHTML.exportarHTML();
		Desktop.getDesktop().browse(new URI("D:/Aplicaciones/GestionAgendaAcesoDatos/personas.html"));
		
	}
	
	// MÉTODOS PARA MOSTRAR LA PANTALLA AÑADIR Y LA DE VER ALUMNOS //

	public void pantallaVer() {

		try {

			// TITULO DE LA PANTALLA
			stage.setTitle("BBDD de alumnos");
			// ICONO
			stage.getIcons().add(
					new Image("https://v2assets.zopim.io/229IBlgwCCyLy9436kK4wA0RHMxsXUNU-concierge?1621445964924"));
			cambiarVentana(stage, "verAlumno", "styleverAlumno.css");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void pantallaAdd() {

		try {

			// TITULO DE LA PANTALLA
			stage.setTitle("Agregar alumnos");
			// ICONO
			stage.getIcons().add(
					new Image("https://v2assets.zopim.io/229IBlgwCCyLy9436kK4wA0RHMxsXUNU-concierge?1621445964924"));
			cambiarVentana(stage, "addAlumno", "styleAddAlumno.css");

		} catch (Exception e) {

			System.out.println("ERROR EN LA RUTA DEL ARCHIVO FXML O DEL CSS");
			e.printStackTrace();

		}

	}

	////// MÉTODOS PARA CAMBIAR DE PANTALLA ///////

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void cambiarVentana(Stage stage, String fxml, String css) throws IOException {

		try {

			scene = new Scene(loadFXML(fxml));
			stage.setScene(scene);
			stage.show();

			// BLOQUEO LA VENTANA PARA QUE NO PUEDA SER RESIZABLE
			stage.setResizable(false);

			// LE AÑADO ESTILOS AL CSS
			scene.getStylesheets().add(getClass().getResource(css).toExternalForm());

		} catch (Exception e) {

			System.out.println("ERROR EN LA RUTA DEL ARCHIVO FXML O DEL CSS");
			e.printStackTrace();

		}

	}

}
