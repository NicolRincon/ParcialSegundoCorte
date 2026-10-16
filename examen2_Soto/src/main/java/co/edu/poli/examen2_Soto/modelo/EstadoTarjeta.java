package co.edu.poli.examen2_Soto.modelo;

public enum EstadoTarjeta {

	ACTIVA("Activa"),

	BLOQUEADA("Bloqueada"),

	CANCELADA("Cancelada");

	private final String etiqueta;

	EstadoTarjeta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Convierte el valor guardado en la base de datos a su constante.
	 *
	 * @param valor texto almacenado en la columna {@code estado}
	 * @return la constante correspondiente
	 * @throws IllegalArgumentException si el valor es nulo o no es un estado válido
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
	 * Texto que se muestra en el formulario (combo y resultados).
	 *
	 * @return la etiqueta del estado
	 */
	@Override
	public String toString() {
		return etiqueta;
	}
}