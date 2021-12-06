package Modelos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FicheroBinario {

	private File miArchivo = new File("D:\\Aplicaciones\\GestionAgendaAcesoDatos\\binario.dat");
	private FileWriter fw;
	private DataOutputStream dataOS;
	private DataInputStream dataIS;

	// VARIABLES AUXILIARES

	String datosAlumno;
	char[] charCaracteres;

	String line;
	int id_Alumno;
	ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

	ArrayList<String> auxEditar = new ArrayList<String>();

	//////////////////////

	public FicheroBinario() {
		super();
	}

	public void addAlumno(String nombre, String apellidos, String direccion, String telefono)
			throws FileNotFoundException {

		dataOS = new DataOutputStream(new FileOutputStream(miArchivo, true));
				
		try {

			datosAlumno = nombre + ";" + apellidos + ";" + direccion + ";" + telefono + "\r\n";

			// LLENAMOS EL TEXTO

			dataOS.writeUTF(datosAlumno);

			System.out.println("OK, AÑADIDO");

		} catch (IOException e) {

			System.out.println("ERROR EN EL METODO ADD - CLASE ADDALUMNOCONTROLLER");
			e.printStackTrace();

		} finally {

			
		}

	}

	@SuppressWarnings("finally")
	public ObservableList<Alumno> getListaAlumnos() throws IOException {

		try {

			dataIS = new DataInputStream(new FileInputStream(miArchivo));

			// ACTUALIZAMOS EL ID DEL ALUMNO A 1
			id_Alumno = 1;
			while (dataIS.available() > 0) {

				line = dataIS.readUTF();
				// le quitamos el /r/n para que no haga el salto de linea
				line = line.substring(0, line.length() - 4);

				String[] parts = line.split(";");

				listaAlumnos.add(new Alumno(Integer.toString(id_Alumno), parts[0], parts[1], parts[2], parts[3]));

				id_Alumno++;
			}

		}catch (Exception e) {

			System.out.println("Error en el metodo getlistaAlumnos en la clase FicheroBinario");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {

			if(dataIS!=null) {
				
				dataIS.close();
			}
			
		}
		return listaAlumnos;

	}

	@SuppressWarnings("resource")
	public void editarAlumno(int idAlumno, String nombreNuevo, String apellidosNuevos, String direccionNueva,
			String telefonoNuevo) {

		try {

			// VACIAMOS EL ARRAY
			auxEditar.clear();

			dataIS = new DataInputStream(new FileInputStream(miArchivo));
			dataOS = new DataOutputStream(new FileOutputStream(miArchivo, true));

			datosAlumno = nombreNuevo + ";" + apellidosNuevos + ";" + direccionNueva + ";" + telefonoNuevo + "\r\n";

			charCaracteres = datosAlumno.toCharArray();

			// LLENAMOS EL ARRAYLIST CON LOS DATOS DEL FICHERO
			while (dataIS.available() > 0) {

				line = dataIS.readUTF();
				auxEditar.add(line + "\r\n");
			}
			
			// EDITAMOS LA LÍNEA
			auxEditar.set(idAlumno, datosAlumno);

			for (int i = 0; i < auxEditar.size(); i++) {

				System.out.println(auxEditar.get(i));

			}

			// NO LE PONEMOS TRUE PARA QUE SE VACIE
			// VACIAMOS EL FICHERO DE TEXTO
			fw = new FileWriter(miArchivo);

			// LO LLENAMOS CON EL ARRAYLIST
			for (int i = 0; i < auxEditar.size(); i++) {

				dataOS.writeUTF(auxEditar.get(i));

			}


		}
		catch (EOFException e) {

			System.out.println("Final del fichero");

		} 
		catch (IOException e) {

			System.out.println("Error en el método editarAlumno en la clase - Fichero");
			e.printStackTrace();

		}
	}

	@SuppressWarnings("resource")
	public void borrar(int index) {

		try {

			dataIS = new DataInputStream(new FileInputStream(miArchivo));
			dataOS = new DataOutputStream(new FileOutputStream(miArchivo, true));
			
			// VACIAMOS EL ARRAY
			auxEditar.clear();

			int aux = 0;
			// LLENAMOS EL ARRAY CON EL FICHERO DE TEXTO
			while (dataIS.available() > 0) {
				
				line = dataIS.readUTF();
				if (aux != index) {

					auxEditar.add(line + "\r\n");

				} else {

					System.out.println("Se ha eliminado la persona " + aux);
				}

				aux++;
			}

			System.out.println(auxEditar.size());
			
			for (int i = 0; i < auxEditar.size(); i++) {
				
				System.out.println(auxEditar);
				
			}

			// VACIAMOS EL FICHERO DE TEXTO
			fw = new FileWriter(miArchivo);

			// LO LLENAMOS CON EL ARRAYLIST
			for (int i = 0; i < auxEditar.size(); i++) {

				dataOS.writeUTF(auxEditar.get(i));

			}


		} catch (Exception e) {

			System.out.println("Error en el método borrar de la clase Fichero");
			e.printStackTrace();

		}

	}

}
