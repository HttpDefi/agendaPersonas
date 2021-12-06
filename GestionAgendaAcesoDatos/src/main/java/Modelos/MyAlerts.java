package Modelos;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyAlerts {


	/**
	 * 
	 * CLASE DE ALERTAS
	 * @author RGBARCO
	 *
	 */
	
	public static void basicAlert(AlertType tipoAlerta, String tituloAlerta, String contenidoAlerta) {
		
		
		Alert alert = new Alert(tipoAlerta);
		alert.setTitle(tituloAlerta);
		alert.setHeaderText(null);
		alert.setContentText(contenidoAlerta);
		alert.showAndWait();
		
		
	}
	
	
	
	
	
	
}
