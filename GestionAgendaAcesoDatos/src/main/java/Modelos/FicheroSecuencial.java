package Modelos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FicheroSecuencial {

	private File fileAlumno = new File("D:\\Aplicaciones\\GestionAgendaAcesoDatos\\secuencial.txt");
	private BufferedReader bf;

	// VARIABLES AUXILIARES

	String datosAlumno;
	char[] charCaracteres;

	String line;
	int id_Alumno;
	ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

	
	ArrayList<String> auxEditar = new ArrayList<String>();
	
	//////////////////////

	public FicheroSecuencial() {
		super();
	}

	public void addAlumno(String nombre, String apellidos, String direccion, String telefono) {

		try {

			FileWriter fw = new FileWriter(fileAlumno, true);

			datosAlumno = nombre + ";" + apellidos + ";" + direccion + ";" + telefono + "\r\n";

			charCaracteres = datosAlumno.toCharArray();

			// LLENAMOS EL TEXTO
			for (int i = 0; i < charCaracteres.length; i++) {

				fw.write(charCaracteres[i]);

			}

			System.out.println("OK, AÑADIDO");

			// METER EN EL BIN

			fw.close();

		} catch (IOException e) {

			System.out.println("ERROR EN EL METODO ADD - CLASE ADDALUMNOCONTROLLER");
			e.printStackTrace();

		}

	}

	@SuppressWarnings("finally")
	public ObservableList<Alumno> getListaAlumnos() throws IOException {

		try {
			
			bf = new BufferedReader(new FileReader(fileAlumno));

			// ACTUALIZAMOS EL ID DEL ALUMNO A 1 
			id_Alumno = 1;
			while ((line = bf.readLine()) != null) {

				String[] parts = line.split(";");

				listaAlumnos.add(new Alumno(Integer.toString(id_Alumno), parts[0], parts[1], parts[2], parts[3]));

				id_Alumno++;
			}

			
		}catch (Exception e) {

			System.out.println("Error en la clase getListaAlumnos");
			System.out.println(e.getMessage());
			
		} finally {

			bf.close();
			

		}
		return listaAlumnos;

	}

	@SuppressWarnings("resource")
	public void editarAlumno(int idAlumno, String nombreNuevo, String apellidosNuevos, String direccionNueva, String telefonoNuevo) {
		
		try {
			
			// VACIAMOS EL ARRAY
			auxEditar.clear();
			
			FileWriter fw = new FileWriter(fileAlumno, true);
			bf = new BufferedReader(new FileReader(fileAlumno));
			
			// LINEA POR LA QUE QUEREMOS MODIFICAR
			datosAlumno = nombreNuevo + ";" + apellidosNuevos + ";" + direccionNueva + ";" + telefonoNuevo + "\r\n";
			charCaracteres = datosAlumno.toCharArray();
			
			// LLENAMOS EL ARRAY CON EL FICHERO DE TEXTO
			while((line = bf.readLine()) != null) {
				
				auxEditar.add(line + "\r\n");
				
			}
						
			// EDITAMOS LA LÍNEA
			auxEditar.set(idAlumno, datosAlumno);
	
			bf.close();
			
			// VACIAMOS EL FICHERO DE TEXTO
			fw = new FileWriter(fileAlumno);
			
			//LO LLENAMOS CON EL ARRAYLIST
			for (int i = 0; i < auxEditar.size(); i++) {

				charCaracteres = auxEditar.get(i).toCharArray();
				
				fw.write(charCaracteres);

			}
			
			fw.close();
			
		} catch (IOException e) {
			
			
			System.out.println("Error en el método editarAlumno en la clase - Fichero");
			e.printStackTrace();
			
		}
	}
	
	@SuppressWarnings("resource")
	public void borrar(int index) {
		
		try {
			
			// VACIAMOS EL ARRAY
			auxEditar.clear();
			
			FileWriter fw = new FileWriter(fileAlumno, true);
			
			bf = new BufferedReader(new FileReader(fileAlumno));
			
			int aux = 0;
			// LLENAMOS EL ARRAY CON EL FICHERO DE TEXTO
			while((line = bf.readLine()) != null) {
				
				if(aux != index) {
					
					auxEditar.add(line + "\r\n");
					
				}else {
					
					System.out.println("Se ha eliminado la persona " + aux);
				}
				
				aux++;
			}
	
			bf.close();
			
			// VACIAMOS EL FICHERO DE TEXTO
			fw = new FileWriter(fileAlumno);
			
			
			for (int i = 0; i < auxEditar.size(); i++) {

				charCaracteres = auxEditar.get(i).toCharArray();
				
				fw.write(charCaracteres);

			}
			
			fw.close();
			
		}catch (Exception e) {

			System.out.println("Error en el método borrar de la clase Fichero");
			e.printStackTrace();
			
		}
		
	}
	
}
