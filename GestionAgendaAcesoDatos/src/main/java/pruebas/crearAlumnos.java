package pruebas;

public class crearAlumnos {

	/**
	 * Programa para sacar alumnos con datos aleatorios y pegarlos en el .txt :)
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) {

			calcularAlumnoRandom();
			
		}
		
	}
	
	public static void calcularAlumnoRandom() {

		String nombres = "Andrés,Sofía,Pol,Aitor,Diana,Iria,Mara,Iris,Ona,Aroa,Elias,Eloy,Cloe,Luna,Jana,Berta,Naia,Paola,Saitama,Shin chan,Helena,Martina,María,Paula,Unai,Jon,Teo,Luka,Alan,Omar,Oscar,Cristian,Cristina,Ángel,Axel,Matías,Noah,Biel,Fernando,Jan,Julia,Emma,Valeria,Daniela,Alba,Hugo,Lucas,Martín,Daniel,Mateo,Álvaro,Leticia,Antonio,Javier,Diego,Alejandra,Cristiano,Gerard,Ana,Eva,Eric,Hugo,Iván,Leo,Lara,Nora,Raúl,Sara,Héctor,Helena,Alba,Emma,Lucas,Lucía,Manuel,Martín,Bernardo,Esther,Gabriel,Isabel,Jorge,Raquel,Samuel,Rubén,Alberto,Enrique,Felipe,Isabel,Leticia,Margarita";

		String apellidos = "Daenerys,Rubio,Ortiz,Castro,Delgado,Ortega,Suarez,Morales,Cruz,Méndez,Prieto,Cano,Guerrero,Lozano,Santos,Castillo,Cortes,Garrido,Medina,Iglesias,Núñez,Sanz,Marín,Reyes,Caballero,Diez,Carrasco,Fuentes,Vega,Campos,Cabrera,Flores,Peña,Herrera,Márquez,León,Vidal,Gallego,Calvo,Mora,Benítez,Santiago,Duran,Ferrer,Ibáñez,Giménez,Hidalgo,Montero,Lorenzo,Herrero,Santana,Pascual,Aguilar,Nieto,Rojas,Gallardo,Bravo,Esteban,Parra,Soler,Moya,Velasco,Sáez,Soto,Pastor,Román,Crespo,Carmona,Arias,Vargas,Vicente,García,González,Rodriguez,Fernandez,Sánchez,Pérez,Gómez,Ruiz,Jiménez,Diaz,Moreno,Muñoz,Álvarez,Romero,Alonso,Navarro,Torres,Gil,Ramírez,Santos,Cruz,Calvo,Gallego,Vidal,León,Márquez,Peña,Herrera,Flores,Diez,Carrasco,Nieto,Aguilar,Montero,Hidalgo";

		String telefono = "";

		String direccion;
		String direccionPrimario = "Calle,Avenida,Paseo";
		String direccionSecundario = "de Goya,de Marcos,de la Reina Cristina,de Romeo,de las Fuerzas Armadas,de los Tercios,de Emilio,de las amapolas,del Roble,del Olmo,del Pinar,de los Guerreros,de Paquito Salas,del Barroco,de América";
		String numeroCalle = "";

		String datosCompletos;

		///

		String[] arrayNombres = nombres.split(",");
		int randomNombres = (int) (Math.random() * (1 - arrayNombres.length) + arrayNombres.length);

		///

		String[] arrayApellidos = apellidos.split(",");
		int randomApellidos = (int) (Math.random() * (1 - arrayApellidos.length) + arrayApellidos.length);
		int randomApellidos2 = (int) (Math.random() * (1 - arrayApellidos.length) + arrayApellidos.length);

		///

		for (int i = 0; i < 9; i++) {
			int randomTelefono = (int) (Math.random() * (0 - 9) + 9);

			telefono = telefono + Integer.toString(randomTelefono);

		}

		telefono = telefono.replace(telefono.charAt(0), '6');

		//////

		String[] arrayDireccionPrimario = direccionPrimario.split(",");
		int randomCallePrimario = (int) (Math.random() * (0 - arrayDireccionPrimario.length)
				+ arrayDireccionPrimario.length);

		// CALLE... AVENIDA...
		direccion = arrayDireccionPrimario[randomCallePrimario] + " ";

		String[] arrayDireccionSecundario = direccionSecundario.split(",");
		int randomCalleSecundario = (int) (Math.random() * (0 - arrayDireccionSecundario.length)
				+ arrayDireccionSecundario.length);

		direccion = direccion + arrayDireccionSecundario[randomCalleSecundario] + " ";

		for (int i = 0; i < 2; i++) {
			int randomNumeroCalle = (int) (Math.random() * (1 - 9) + 9);

			numeroCalle = numeroCalle + Integer.toString(randomNumeroCalle);

		}

		direccion = direccion + numeroCalle;

		datosCompletos = arrayNombres[randomNombres] + ";" + arrayApellidos[randomApellidos] + " "
				+ arrayApellidos[randomApellidos2] + ";" + direccion + ";" + telefono;

		System.out.println(datosCompletos);
	}

}
