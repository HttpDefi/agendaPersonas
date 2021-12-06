package IMF.GestionAgendaAcesoDatos;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class openGoogleMapsController {
	
	private static String direccion;
	
    @FXML
    private WebView webView;

    @FXML
    void initialize() {
    	
    	direccion = direccion.replace(" ", "+");
    	
	    webView.getEngine().load("https://www.google.com/maps/place/"+direccion);
	    
    }
    
    static void setDireccion(String direccionAlumno) {
    	
    	direccion = direccionAlumno;
    	
    }
    
}
