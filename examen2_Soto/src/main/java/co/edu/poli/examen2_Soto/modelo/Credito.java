package co.edu.poli.examen2_Soto.modelo;

/**
 * Clase que representa una tarjeta de crédito dentro del sistema,
 * heredando los atributos y comportamientos generales de la clase {@link Tarjeta}.
 */
public class Credito extends Tarjeta {

	/** Límite de crédito asignado a la tarjeta. */
	private double limite;

	/**
	 * Constructor para inicializar una nueva tarjeta de crédito con sus datos principales y su límite.
	 * 
	 * @param numero      Número único identificador de la tarjeta.
	 * @param fechaExp    Fecha de expedición o expiración de la tarjeta (formato MM/yyyy).
	 * @param estado      Estado actual de la tarjeta (ej. ACTIVA, BLOQUEADA).
	 * @param titular     Objeto Titular asociado a la tarjeta.
	 * @param limite      Límite máximo de crédito de la tarjeta.
	 */
	public Credito(String numero, String fechaExp, EstadoTarjeta estado, Titular titular, double limite) {
		super(numero, fechaExp, estado, titular);
		this.limite = limite;
	}

	/**
	 * Obtiene el límite de crédito de la tarjeta.
	 * 
	 * @return El valor del límite de crédito.
	 */
	public double getLimite() {
		return limite;
	}

	/**
	 * Establece o modifica el límite de crédito de la tarjeta.
	 * 
	 * @param limite El nuevo límite de crédito a asignar.
	 */
	public void setLimite(double limite) {
		this.limite = limite;
	}

	/**
	 * Retorna una representación en cadena de texto de la tarjeta de crédito,
	 * incluyendo los datos heredados y su límite.
	 * 
	 * @return Cadena con los detalles de la tarjeta de crédito.
	 */
	@Override
	public String toString() {
		return "Credito [" + super.toString() + ", limite=" + limite + "]";
	}
}
