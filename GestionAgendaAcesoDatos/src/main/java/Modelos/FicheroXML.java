package Modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FicheroXML {
	
	private static DocumentBuilderFactory docFactory;
	private static DocumentBuilder docBuilder;
	private static Document doc;
	
	private static File miArchivo = new File("D:\\Aplicaciones\\GestionAgendaAcesoDatos\\secuencial.txt");
	private static BufferedReader bf;
	
	private static String line;
	private static ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
	
	
	public static void crearXML() throws IOException {
		
		try {
			
			// COJO LOS DATOS DEL FICHERO SECUENCIAL
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("listaPersonas");
			
			
			bf = new BufferedReader(new FileReader(miArchivo));

			// ACTUALIZAMOS EL ID DEL ALUMNO A 1 
			int id_Alumno = 1;
			while ((line = bf.readLine()) != null) {

				String[] parts = line.split(";");

				listaAlumnos.add(new Alumno(Integer.toString(id_Alumno), parts[0], parts[1], parts[2], parts[3]));

				id_Alumno++;
			}
			
			for (int i = 0; i < listaAlumnos.size(); i++) {
				
				Element persona = doc.createElement("Persona");
				
				// NOMBRE 
				Element nombre = doc.createElement("nombre");
				nombre.setTextContent(listaAlumnos.get(i).getNombre());
				persona.appendChild(nombre);
				
				// APELLIDOS 
				Element apellidos = doc.createElement("apellidos");
				apellidos.setTextContent(listaAlumnos.get(i).getApellidos());
				persona.appendChild(apellidos);
				
				// TELEFONO 
				Element telefono = doc.createElement("telefono");
				telefono.setTextContent(listaAlumnos.get(i).getTelefono());
				persona.appendChild(telefono);
				
				// DIRECCION 
				Element direccion = doc.createElement("direccion");
				direccion.setTextContent(listaAlumnos.get(i).getDireccion());
				persona.appendChild(direccion);
				
				// Se agrega un atributo al nodo elemento y su valor
				Attr attr = doc.createAttribute("id");
				Attr attr2 = doc.createAttribute("id");
				Attr attr3 = doc.createAttribute("id");
				Attr attr4 = doc.createAttribute("id");
				attr.setValue("id_nombre");
				nombre.setAttributeNode(attr);
				attr2.setValue("id_apellidos");
				apellidos.setAttributeNode(attr2);
				attr3.setValue("id_telefono");
				telefono.setAttributeNode(attr3);
				attr4.setValue("id_direccion");
				direccion.setAttributeNode(attr4);
				
				rootElement.appendChild(persona);
				
			}
			
			doc.appendChild(rootElement);
			
			// Se escribe el contenido del XML en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("personas.xml"));
			
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			
			pce.printStackTrace();
			
		} catch (TransformerException tfe) {
			
			tfe.printStackTrace();
		}
	}
}
