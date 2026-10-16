package co.edu.poli.examen2_Soto.modelo;

/**
 * Clase que representa una tarjeta de débito dentro del sistema,
 * heredando los atributos y comportamientos generales de la clase {@link Tarjeta}.
 */
public class Debito extends Tarjeta {

	/** Saldo disponible asociado a la tarjeta de débito. */
	private double saldo;

	/**
	 * Constructor para inicializar una nueva tarjeta de débito con sus datos principales y su saldo inicial.
	 * 
	 * @param numero      Número único identificador de la tarjeta.
	 * @param fechaExp    Fecha de expedición o expiración de la tarjeta (formato MM/yyyy).
	 * @param estado      Estado actual de la tarjeta (ej. ACTIVA, BLOQUEADA).
	 * @param titular     Objeto Titular asociado a la tarjeta.
	 * @param saldo       Saldo inicial disponible en la tarjeta de débito.
	 */
	public Debito(String numero, String fechaExp, EstadoTarjeta estado, Titular titular, double saldo) {
		super(numero, fechaExp, estado, titular);
		this.saldo = saldo;
	}

	/**
	 * Obtiene el saldo disponible de la tarjeta de débito.
	 * 
	 * @return El valor del saldo actual.
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Establece o modifica el saldo disponible de la tarjeta de débito.
	 * 
	 * @param saldo El nuevo valor de saldo a asignar.
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Retorna una representación en cadena de texto de la tarjeta de débito,
	 * incluyendo los datos heredados y su saldo actual.
	 * 
	 * @return Cadena con los detalles de la tarjeta de débito.
	 */
	@Override
	public String toString() {
		return "Debito [" + super.toString() + ", saldo=" + saldo + "]";
	}
}
