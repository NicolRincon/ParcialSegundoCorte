package co.edu.poli.examen2_Soto.servicios;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase encargada de gestionar la conexión a la base de datos MySQL 
 * utilizando el patrón de diseño Singleton y la librería dotenv para la seguridad de credenciales.
 */
public class ConexionBD {

	/** Instancia única de la clase ConexionBD (Patrón Singleton). */
	private static ConexionBD instancia;
	
	/** Objeto de conexión JDBC a la base de datos. */
	private Connection conexion;

	/**
	 * Constructor privado para evitar la instanciación directa desde fuera de la clase.
	 * Carga las variables de entorno desde el archivo "conexion.env" e inicializa la conexión JDBC.
	 * 
	 * @throws Exception si ocurre un error al cargar las credenciales, registrar el driver o conectar con la base de datos.
	 */
	private ConexionBD() throws Exception {

		Dotenv dotenv = Dotenv.configure().filename("conexion.env").load();

		String url = dotenv.get("DB_URL");
		String user = dotenv.get("DB_USER");
		String pass = dotenv.get("DB_PASSWORD");

		if (url == null || user == null || pass == null) {
			throw new RuntimeException("Faltan variables en el .env");
		}

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, user, pass);
	}

	/**
	 * Obtiene la instancia única de la clase ConexionBD.
	 * Si la instancia no ha sido creada previamente, se inicializa.
	 * 
	 * @return La instancia única de {@link ConexionBD}.
	 * @throws Exception si hay fallos en la inicialización de la conexión.
	 */
	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	/**
	 * Obtiene el objeto de conexión JDBC activo. 
	 * Si la conexión es nula o se encuentra cerrada, regenera la instancia automáticamente.
	 * 
	 * @return El objeto {@link Connection} listo para ejecutar consultas.
	 * @throws Exception si ocurre un error al restablecer la conexión.
	 */
	public Connection getConexion() throws Exception {
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}
