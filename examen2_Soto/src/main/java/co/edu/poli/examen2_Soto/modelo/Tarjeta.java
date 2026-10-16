package co.edu.poli.examen2_Soto.modelo;

public abstract class Tarjeta {

	private String numero;

	private String fechaExp;

	private EstadoTarjeta estado;

	private Titular titular;

	public Tarjeta(String numero, String fechaExp, EstadoTarjeta estado, Titular titular) {
		this.numero = numero;
		this.fechaExp = fechaExp;
		this.titular = titular;
		setEstado(estado);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public EstadoTarjeta getEstado() {
		return estado;
	}

	public void setEstado(EstadoTarjeta estado) {
		if (estado == null) {
			throw new IllegalArgumentException("El estado de la tarjeta es obligatorio.");
		}
		this.estado = estado;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public boolean puedeUsarse() {
		return estado == EstadoTarjeta.ACTIVA;
	}

	public String bloquear() {
		setEstado(EstadoTarjeta.BLOQUEADA);
		return "Tarjeta " + numero + " BLOQUEADA.";
	}

	public String activar() {
		setEstado(EstadoTarjeta.ACTIVA);
		return "Tarjeta " + numero + " ACTIVADA.";
	}

	public String cancelar() {
		setEstado(EstadoTarjeta.CANCELADA);
		return "Tarjeta " + numero + " CANCELADA.";
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", titular=" + titular;
	}
}