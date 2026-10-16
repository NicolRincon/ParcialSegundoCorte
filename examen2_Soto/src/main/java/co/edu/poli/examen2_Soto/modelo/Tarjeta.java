package co.edu.poli.examen2_Soto.modelo;

/**
 * Clase abstracta que define los atributos, constructores y comportamientos generales
 * de una tarjeta dentro del sistema bancario.
 */
public abstract class Tarjeta {

	/** Número identificador único de la tarjeta. */
	private String numero;

	/** Fecha de expedición o expiración de la tarjeta (formato MM/yyyy). */
	private String fechaExp;

	/** Estado actual en el que se encuentra la tarjeta (ACTIVA, BLOQUEADA, CANCELADA). */
	private EstadoTarjeta estado;

	/** Titular o propietario asociado a la tarjeta. */
	private Titular titular;

	/**
	 * Constructor para inicializar los datos principales de la tarjeta.
	 * 
	 * @param numero    Número único identificador de la tarjeta.
	 * @param fechaExp  Fecha de expedición/expiración.
	 * @param estado    Estado inicial de la tarjeta (obligatorio).
	 * @param titular   Titular propietario de la tarjeta.
	 */
	public Tarjeta(String numero, String fechaExp, EstadoTarjeta estado, Titular titular) {
		this.numero = numero;
		this.fechaExp = fechaExp;
		this.titular = titular;
		setEstado(estado);
	}

	/**
	 * Obtiene el número identificador de la tarjeta.
	 * 
	 * @return El número de la tarjeta.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Establece o modifica el número identificador de la tarjeta.
	 * 
	 * @param numero El nuevo número de la tarjeta.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Obtiene la fecha de expedición o expiración de la tarjeta.
	 * 
	 * @return La fecha en formato de texto.
	 */
	public String getFechaExp() {
		return fechaExp;
	}

	/**
	 * Establece o modifica la fecha de expedición o expiración de la tarjeta.
	 * 
	 * @param fechaExp La nueva fecha.
	 */
	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	/**
	 * Obtiene el estado actual de la tarjeta.
	 * 
	 * @return Objeto {@link EstadoTarjeta} con el estado actual.
	 */
	public EstadoTarjeta getEstado() {
		return estado;
	}

	/**
	 * Establece o modifica el estado de la tarjeta. 
	 * Valida que el estado no sea nulo.
	 * 
	 * @param estado El nuevo estado a asignar.
	 * @throws IllegalArgumentException si el estado proporcionado es nulo.
	 */
	public void setEstado(EstadoTarjeta estado) {
		if (estado == null) {
			throw new IllegalArgumentException("El estado de la tarjeta es obligatorio.");
		}
		this.estado = estado;
	}

	/**
	 * Obtiene el titular asociado a la tarjeta.
	 * 
	 * @return Objeto {@link Titular} propietario.
	 */
	public Titular getTitular() {
		return titular;
	}

	/**
	 * Establece o modifica el titular de la tarjeta.
	 * 
	 * @param titular El nuevo objeto Titular.
	 */
	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	/**
	 * Verifica si la tarjeta se encuentra activa y puede ser utilizada para transacciones.
	 * 
	 * @return true si el estado es ACTIVA, false en caso contrario.
	 */
	public boolean puedeUsarse() {
		return estado == EstadoTarjeta.ACTIVA;
	}

	/**
	 * Cambia el estado de la tarjeta a BLOQUEADA.
	 * 
	 * @return Mensaje de confirmación del bloqueo.
	 */
	public String bloquear() {
		setEstado(EstadoTarjeta.BLOQUEADA);
		return "Tarjeta " + numero + " BLOQUEADA.";
	}

	/**
	 * Cambia el estado de la tarjeta a ACTIVA.
	 * 
	 * @return Mensaje de confirmación de la activación.
	 */
	public String activar() {
		setEstado(EstadoTarjeta.ACTIVA);
		return "Tarjeta " + numero + " ACTIVADA.";
	}

	/**
	 * Cambia el estado de la tarjeta a CANCELADA.
	 * 
	 * @return Mensaje de confirmación de la cancelación.
	 */
	public String cancelar() {
		setEstado(EstadoTarjeta.CANCELADA);
		return "Tarjeta " + numero + " CANCELADA.";
	}

	/**
	 * Retorna una representación en cadena de texto con los atributos principales de la tarjeta.
	 * 
	 * @return Cadena formateada con los datos de la tarjeta.
	 */
	@Override
	public String toString() {
		return "numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", titular=" + titular;
	}
}
