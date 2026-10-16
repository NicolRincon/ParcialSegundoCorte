
package co.edu.poli.examen2_Soto.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Soto.modelo.Debito;
import co.edu.poli.examen2_Soto.modelo.EstadoTarjeta;
import co.edu.poli.examen2_Soto.modelo.Tarjeta;
import co.edu.poli.examen2_Soto.modelo.Titular;

public class TestTarjeta {

    private Tarjeta nueva(EstadoTarjeta estado) {
        return new Debito(
            "123",
            "12/2027",
            estado,
            new Titular("1", "Test"),
            1000
        );
    }

    private static boolean lanza(Runnable accion) {
        try {
            accion.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    @Test
    void constructor_conEstadoValido_loAsigna() {
        assertEquals(
            EstadoTarjeta.ACTIVA,
            nueva(EstadoTarjeta.ACTIVA).getEstado()
        );

        assertEquals(
            EstadoTarjeta.BLOQUEADA,
            nueva(EstadoTarjeta.BLOQUEADA).getEstado()
        );

        assertEquals(
            EstadoTarjeta.CANCELADA,
            nueva(EstadoTarjeta.CANCELADA).getEstado()
        );
    }

    @Test
    void constructor_sinEstado_lanzaExcepcion() {
        assertTrue(lanza(() -> nueva(null)));
    }

    @Test
    void setEstado_nulo_lanzaExcepcionYConservaElEstado() {
        Tarjeta t = nueva(EstadoTarjeta.ACTIVA);

        assertTrue(lanza(() -> t.setEstado(null)));

        assertEquals(
            EstadoTarjeta.ACTIVA,
            t.getEstado()
        );
    }

    @Test
    void bloquear_cambiaEstadoABloqueada() {
        Tarjeta t = nueva(EstadoTarjeta.ACTIVA);

        String mensaje = t.bloquear();

        assertEquals(
            EstadoTarjeta.BLOQUEADA,
            t.getEstado()
        );

        assertTrue(mensaje.contains("BLOQUEADA"));
    }

    @Test
    void activar_cambiaEstadoAActiva() {
        Tarjeta t = nueva(EstadoTarjeta.BLOQUEADA);

        String mensaje = t.activar();

        assertEquals(
            EstadoTarjeta.ACTIVA,
            t.getEstado()
        );

        assertTrue(mensaje.contains("ACTIVADA"));
    }

    @Test
    void cancelar_cambiaEstadoACancelada() {
        Tarjeta t = nueva(EstadoTarjeta.ACTIVA);

        String mensaje = t.cancelar();

        assertEquals(
            EstadoTarjeta.CANCELADA,
            t.getEstado()
        );

        assertTrue(mensaje.contains("CANCELADA"));
    }

    @Test
    void puedeUsarse_soloCuandoEstaActiva() {
        assertTrue(nueva(EstadoTarjeta.ACTIVA).puedeUsarse());

        assertFalse(nueva(EstadoTarjeta.BLOQUEADA).puedeUsarse());

        assertFalse(nueva(EstadoTarjeta.CANCELADA).puedeUsarse());
    }

    @Test
    void getters_retornaValoresCorrectos() {
        Titular titular = new Titular("1", "Test");

        Tarjeta t = new Debito(
            "123",
            "12/2027",
            EstadoTarjeta.ACTIVA,
            titular,
            1000
        );

        assertEquals("123", t.getNumero());
        assertEquals("12/2027", t.getFechaExp());
        assertEquals(EstadoTarjeta.ACTIVA, t.getEstado());
        assertEquals(titular, t.getTitular());
    }

    @Test
    void setters_modificanValores() {
        Titular nuevo = new Titular("2", "Nuevo");

        Tarjeta t = nueva(EstadoTarjeta.ACTIVA);

        t.setNumero("999");
        t.setFechaExp("01/2030");
        t.setEstado(EstadoTarjeta.CANCELADA);
        t.setTitular(nuevo);

        assertEquals("999", t.getNumero());
        assertEquals("01/2030", t.getFechaExp());
        assertEquals(EstadoTarjeta.CANCELADA, t.getEstado());
        assertEquals(nuevo, t.getTitular());
    }

    @Test
    void toString_muestraElEstado() {
        String texto = nueva(EstadoTarjeta.BLOQUEADA).toString();

        assertTrue(texto.contains("123"));
        assertTrue(texto.contains("Bloqueada"));
    }
}
