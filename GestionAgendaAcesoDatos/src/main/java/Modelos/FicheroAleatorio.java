package Modelos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FicheroAleatorio {

	private File ficheroAleatorio = new File("D:\\Aplicaciones\\GestionAgendaAcesoDatos\\aleatorio.dat");
	private RandomAccessFile accesFile;
	private BufferedReader bf;

	// VARIABLES AUXILIARES

	String datosAlumno;
	char[] charCaracteres;

	String line;
	int id_Alumno;
	ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
	
	ArrayList<String> auxEditar = new ArrayList<String>();
	
	//////////////////////

	public FicheroAleatorio() {
		super();
	}
	
	public void addAlumno(String nombre, String apellidos, String direccion, String telefono) {
		
		try {
			
			accesFile = new RandomAccessFile(ficheroAleatorio, "rw");
			
			datosAlumno = nombre + ";" + apellidos + ";" + direccion + ";" + telefono + "\r\n";

			// LLENAMOS EL TEXTO

			accesFile.writeBytes(datosAlumno);

			System.out.println("OK, AÑADIDO");

			// METER EN EL BIN

			accesFile.close();

		} catch (IOException e) {

			System.out.println("ERROR EN EL METODO ADD - CLASE ADDALUMNOCONTROLLER");
			e.printStackTrace();

		}

	}

	@SuppressWarnings("finally")
	public ObservableList<Alumno> getListaAlumnos() throws IOException {

		try {

			accesFile = new RandomAccessFile(ficheroAleatorio, "rw");
			accesFile.seek(0); // Nos situamos al principio
			
			// ACTUALIZAMOS EL ID DEL ALUMNO A 1 
			id_Alumno = 1;
			while ((line = accesFile.readLine()) != null) {

				String[] parts = line.split(";");

				listaAlumnos.add(new Alumno(Integer.toString(id_Alumno), parts[0], parts[1], parts[2], parts[3]));

				id_Alumno++;
			}

			
		}catch (Exception e) {

			System.out.println("Error en la clase getListaAlumnos");
			System.out.println(e.getMessage());
			
		} finally {

			if(accesFile!=null) {
				
				accesFile.close();
				
			}
			

		}
		return listaAlumnos;

	}

	@SuppressWarnings("resource")
	public void editarAlumno(int idAlumno, String nombreNuevo, String apellidosNuevos, String direccionNueva, String telefonoNuevo) {
		
		try {
			
			// VACIAMOS EL ARRAY
			auxEditar.clear();
			
			FileWriter fw = new FileWriter(ficheroAleatorio, true);
			bf = new BufferedReader(new FileReader(ficheroAleatorio));
			
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
			fw = new FileWriter(ficheroAleatorio);
			
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
			
			FileWriter fw = new FileWriter(ficheroAleatorio, true);
			
			bf = new BufferedReader(new FileReader(ficheroAleatorio));
			
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
			fw = new FileWriter(ficheroAleatorio);
			
			
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
