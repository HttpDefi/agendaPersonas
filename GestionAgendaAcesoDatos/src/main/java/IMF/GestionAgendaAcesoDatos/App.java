package IMF.GestionAgendaAcesoDatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws IOException {

		scene = new Scene(loadFXML("menu"));
		stage.setScene(scene);
		stage.show();

		// BLOQUEO LA VENTANA PARA QUE NO PUEDA SER RESIZABLE
		stage.setResizable(false);

		// TITULO DE LA PANTALLA
		stage.setTitle("Menú de gestión de alumnos");
		
		// LE PONEMOS UN ICONITO GUAPO
		stage.getIcons().add(new Image("https://v2assets.zopim.io/229IBlgwCCyLy9436kK4wA0RHMxsXUNU-concierge?1621445964924"));
		
		// LE AÑADO ESTILOS AL CSS
		scene.getStylesheets().add(getClass().getResource("styleMenu.css").toExternalForm());

	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}



}