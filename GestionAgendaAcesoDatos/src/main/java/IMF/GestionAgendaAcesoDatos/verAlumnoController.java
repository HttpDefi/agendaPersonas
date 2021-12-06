package IMF.GestionAgendaAcesoDatos;

import Modelos.Alumno;
import Modelos.FicheroAleatorio;
import Modelos.FicheroBinario;
import Modelos.FicheroSecuencial;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TablePosition;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class verAlumnoController {

	private static String eleccion;

	// MIS VARIABLES //

	FicheroSecuencial miFicheroSecuencial = new FicheroSecuencial();
	FicheroBinario miFicheroBinario = new FicheroBinario();
	FicheroAleatorio mificheroAleatorio = new FicheroAleatorio();


	///////////////////

	@FXML
	private Button btnEditar;

	@FXML
	private TableView<Alumno> tblTabla;

	@FXML
	private TableColumn<Alumno, String> columnID;

	@FXML
	private TableColumn<Alumno, String> columnNombre;

	@FXML
	private TableColumn<Alumno, String> columnApellidos;

	@FXML
	private TableColumn<Alumno, String> columnTelefono;

	@FXML
	private TableColumn<Alumno, String> columnDireccion;

	@FXML
	private TextField txtNombre, txtApellidos, txtTelefono, txtDireccion, txtBuscar;

	// PARA LA TABLA
	ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

	/// MÉTODO PARA ELEGIR SI QUIERES VER BINARIO, SECUENCIAL O ALEATORIO ///

	public static void setEleccion(String eleccionBoton) {
		eleccion = eleccionBoton;
	}

	//////////////////////////////////////

	@FXML
	void initialize() {

		try {

			actualizarTabla();
		} catch (IOException e) {

			System.out.println("Error en el método initialize de la clase verAlumnoController");
			e.printStackTrace();
		}

	}

	@FXML
	void getDatos(MouseEvent event) {

		try {

			// CON LA CLASE TABLEPOSITION SACAMOS LA POSICION EN LA QUE SE HA CLICKADO
			TablePosition pos = (TablePosition) tblTabla.getSelectionModel().getSelectedCells().get(0);
			int index = pos.getRow();

			// CREAMOS UN NUEVO ALUMNO CON EL INDEX DEL ARRAYLIST
			Alumno alumno = tblTabla.getItems().get(index);

			// CAMBIO LOS VALORES
			txtNombre.setText(alumno.getNombre());
			txtApellidos.setText(alumno.getApellidos());
			txtTelefono.setText(alumno.getTelefono());
			txtDireccion.setText(alumno.getDireccion());

		} catch (Exception e) {

			System.out.println("Posición sin ningun alumno");

		}

	}

	void actualizarTabla() throws IOException {

		try {

			listaAlumnos.clear();

			// ASIGNAMOS EL VALOR DE LOS ATRIBUTOS DE LA CLASE RED A CADA COLUMNA DEL TABLE
			// VIEW //
			columnID.setCellValueFactory(new PropertyValueFactory<Alumno, String>("id"));
			columnNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
			columnApellidos.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellidos"));
			columnTelefono.setCellValueFactory(new PropertyValueFactory<Alumno, String>("telefono"));
			columnDireccion.setCellValueFactory(new PropertyValueFactory<Alumno, String>("direccion"));

			//// DISTINCION PARA OBTENER LOS DATOS DEL FICHERO SECUENCIAL, BINARIO O ALEATORIO ////
			if(eleccion.equals("secuencial")) {
				
				listaAlumnos = miFicheroSecuencial.getListaAlumnos();
			}else if(eleccion.equals("binario")) {
				
				listaAlumnos = miFicheroBinario.getListaAlumnos();
				
			}else if(eleccion.equals("aleatorio")) {
				
				listaAlumnos = mificheroAleatorio.getListaAlumnos();
				
			}
				

			// Wrap the ObservableList in a FilteredList (initially display all data).
			FilteredList<Alumno> filteredData = new FilteredList<>(listaAlumnos, b -> true);

			// 2. Set the filter Predicate whenever the filter changes.
			txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(alumno -> {
					// If filter text is empty, display all persons.

					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();

					if (alumno.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches first name.
					} else if (alumno.getApellidos().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else if (alumno.getDireccion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else if (String.valueOf(alumno.getApellidos()).indexOf(lowerCaseFilter) != -1)
						return true;
					else {
						return false; // NO HAY RESULTADOS
					}
				});
			});

			// 3. Wrap the FilteredList in a SortedList.
			SortedList<Alumno> sortedData = new SortedList<>(filteredData);

			// 4. Bind the SortedList comparator to the TableView comparator.
			// Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(tblTabla.comparatorProperty());

			// 5. Add sorted (and filtered) data to the table.
			tblTabla.setItems(sortedData);

		} catch (Exception e) {

			System.out.println("Error en el método actualizar tabla de la clase verAlumnoController");
			e.printStackTrace();

		}

	}

	@FXML
	void editar(ActionEvent event) throws IOException {

		try {

			// CON LA CLASE TABLEPOSITION SACAMOS LA POSICION EN LA QUE SE HA CLICKADO
			TablePosition pos = (TablePosition) tblTabla.getSelectionModel().getSelectedCells().get(0);
			int index = pos.getRow();

			// CASTEAMOS INDEX PARA PODER COMPARARLO CON UN NULL
			if ((Integer) index != null) {

				
				if(eleccion.equals("secuencial")) {
					
					miFicheroSecuencial.editarAlumno(index, txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(),
							txtTelefono.getText());
					
				}else if(eleccion.equals("binario")) {
					
					miFicheroBinario.editarAlumno(index, txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(),
							txtTelefono.getText());
					
				}else if(eleccion.equals("aleatorio")) {
					
					mificheroAleatorio.editarAlumno(index, txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(),
							txtTelefono.getText());
					
				}
				
				actualizarTabla();

			} else {

				System.out.println("No se ha seleccionado ningún alumno");

			}

		} catch (Exception e) {

			System.out.println("Error en método editar de la clase verAlumnoController");
			e.printStackTrace();
		}

	}

	@FXML
	void borrar(ActionEvent event) throws IOException {

		try {

			// CON LA CLASE TABLEPOSITION SACAMOS LA POSICION EN LA QUE SE HA CLICKADO
			TablePosition pos = (TablePosition) tblTabla.getSelectionModel().getSelectedCells().get(0);
			int index = pos.getRow();

			// CASTEAMOS INDEX PARA PODER COMPARARLO CON UN NULL
			if ((Integer) index != null) {

				if(eleccion.equals("secuencial")) {
					
					miFicheroSecuencial.borrar(index);
				}else if(eleccion.equals("binario")) {
					
					miFicheroBinario.borrar(index);
				}else if(eleccion.equals("aleatorio")) {
					
					mificheroAleatorio.borrar(index);
					
				}
				
				actualizarTabla();

			} else {

				System.out.println("No se ha seleccionado ningún alumno");

			}

		} catch (Exception e) {

			System.out.println("Error en método editar de la clase verAlumnoController");
			e.printStackTrace();
		}

	}

}
