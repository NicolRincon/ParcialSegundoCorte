package co.edu.poli.examen2_Soto.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Soto.modelo.EstadoTarjeta;

/** Pruebas unitarias de {@link EstadoTarjeta} (HU-01). */
public class TestEstadoTarjeta {

	private static boolean lanza(Runnable accion) {
		try {
			accion.run();
			return false;
		} catch (IllegalArgumentException e) {
			return true;
		}
	}

	@Test
	void existenExactamenteLosTresEstados() {
		assertEquals(3, EstadoTarjeta.values().length);
		assertNotNull(EstadoTarjeta.valueOf("ACTIVA"));
		assertNotNull(EstadoTarjeta.valueOf("BLOQUEADA"));
		assertNotNull(EstadoTarjeta.valueOf("CANCELADA"));
	}

	@Test
	void desdeBD_valorValido_devuelveLaConstante() {
		assertEquals(EstadoTarjeta.ACTIVA, EstadoTarjeta.desdeBD("ACTIVA"));
		assertEquals(EstadoTarjeta.BLOQUEADA, EstadoTarjeta.desdeBD("bloqueada"));
		assertEquals(EstadoTarjeta.CANCELADA, EstadoTarjeta.desdeBD(" CANCELADA "));
	}

	@Test
	void desdeBD_nuloOVacio_lanzaExcepcion() {
		assertTrue(lanza(() -> EstadoTarjeta.desdeBD(null)));
		assertTrue(lanza(() -> EstadoTarjeta.desdeBD("  ")));
	}

	@Test
	void desdeBD_valorInvalido_lanzaExcepcion() {
		assertTrue(lanza(() -> EstadoTarjeta.desdeBD("SUSPENDIDA")));
	}

	@Test
	void toString_devuelveLaEtiquetaLegible() {
		assertEquals("Activa", EstadoTarjeta.ACTIVA.toString());
		assertEquals("Bloqueada", EstadoTarjeta.BLOQUEADA.toString());
		assertEquals("Cancelada", EstadoTarjeta.CANCELADA.toString());
	}
}