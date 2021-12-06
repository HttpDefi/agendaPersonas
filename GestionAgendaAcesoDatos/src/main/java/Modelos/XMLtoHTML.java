package Modelos;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XMLtoHTML {

	public static void exportarHTML() throws FileNotFoundException {

		String hojaEstilo = "personas.xsl";
		String datosAlumnos = "personas.xml";
		File pagHTML = new File("personas.html");
		FileOutputStream os = new FileOutputStream(pagHTML); // crear fichero HTML

		Source estilos = new StreamSource(hojaEstilo); // fuente XSL
		Source datos = new StreamSource(datosAlumnos); // fuente XML
		Result result = new StreamResult(os); // resultado de la transformación

		try {

			Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
			transformer.transform(datos, result); // Obtiene el HTML
		} catch (Exception e) {

			System.err.println("Error: " + e);
		}
		
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
