package co.edu.poli.examen2_Soto.modelo;

/**
 * Enumeración que representa los diferentes estados posibles que puede tener una tarjeta en el sistema.
 */
public enum EstadoTarjeta {

	/** Estado que indica que la tarjeta se encuentra activa y operativa. */
	ACTIVA("Activa"),

	/** Estado que indica que la tarjeta ha sido bloqueada temporal o permanentemente. */
	BLOQUEADA("Bloqueada"),

	/** Estado que indica que la tarjeta se encuentra cancelada. */
	CANCELADA("Cancelada");

	/** Etiqueta descriptiva asociada al estado. */
	private final String etiqueta;

	/**
	 * Constructor del enum para inicializar la etiqueta descriptiva.
	 * 
	 * @param etiqueta Texto descriptivo del estado.
	 */
	EstadoTarjeta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Obtiene la etiqueta descriptiva del estado.
	 * 
	 * @return La etiqueta en formato de texto.
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Convierte el valor guardado en la base de datos a su respectiva constante del enum.
	 *
	 * @param valor Texto almacenado en la columna de estado de la base de datos.
	 * @return La constante del enum correspondiente.
	 * @throws IllegalArgumentException si el valor es nulo, en blanco o no corresponde a un estado válido.
	 */
	public static EstadoTarjeta desdeBD(String valor) {
		if (valor == null || valor.isBlank()) {
			throw new IllegalArgumentException("El estado de la tarjeta es obligatorio.");
		}
		try {
			return EstadoTarjeta.valueOf(valor.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Estado de tarjeta no válido: " + valor);
		}
	}

	/**
	 * Retorna el texto que se muestra en la interfaz de usuario (como en los ComboBox y resultados).
	 *
	 * @return La etiqueta descriptiva del estado.
	 */
	@Override
	public String toString() {
		return etiqueta;
	}
}
