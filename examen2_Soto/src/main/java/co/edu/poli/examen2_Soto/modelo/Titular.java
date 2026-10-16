package co.edu.poli.examen2_Soto.modelo;

/**
 * Clase que representa al titular o propietario de las tarjetas dentro del sistema bancario.
 */
public class Titular {

	/** Identificador único o documento de identidad del titular. */
	private String id;

	/** Nombre completo del titular. */
	private String nombre;

	/**
	 * Constructor para inicializar un nuevo titular con su respectivo identificador y nombre.
	 * 
	 * @param id     Identificador o número de documento del titular.
	 * @param nombre Nombre completo del titular.
	 */
	public Titular(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Obtiene el identificador o documento del titular.
	 * 
	 * @return El identificador del titular.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece o modifica el identificador del titular.
	 * 
	 * @param id El nuevo identificador a asignar.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre completo del titular.
	 * 
	 * @return El nombre del titular.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece o modifica el nombre del titular.
	 * 
	 * @param nombre El nuevo nombre a asignar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna una representación en cadena de texto del titular,
     * combinando su identificador y su nombre (utilizado habitualmente en componentes visuales como ComboBox).
	 * 
	 * @return Cadena formateada con el id y el nombre del titular.
	 */
	@Override
	public String toString() {
		return id + ", " + nombre;
	}
}
