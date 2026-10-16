package co.edu.poli.examen2_Soto.servicios;

import co.edu.poli.examen2_Soto.modelo.EstadoTarjeta;
import co.edu.poli.examen2_Soto.modelo.Credito;
import co.edu.poli.examen2_Soto.modelo.Debito;
import co.edu.poli.examen2_Soto.modelo.Tarjeta;
import co.edu.poli.examen2_Soto.modelo.Titular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOTarjeta implements CRUD<Tarjeta> {


@Override
public String create(Tarjeta t) throws Exception {

    if (t == null) {
        throw new IllegalArgumentException(
            "La tarjeta no puede ser nula."
        );
    }

    if (t.getEstado() == null) {
        throw new IllegalArgumentException(
            "El estado de la tarjeta es obligatorio."
        );
    }

    if (!(t instanceof Debito) && !(t instanceof Credito)) {
        throw new IllegalArgumentException(
            "El tipo de tarjeta no es válido."
        );
    }

    Connection con = ConexionBD.getInstancia().getConexion();
    boolean autoCommitAnterior = con.getAutoCommit();

    try {
        con.setAutoCommit(false);

        String sqlTarjeta =
            "INSERT INTO tarjeta " +
            "(numero, fecha_exp, estado, titular_id) " +
            "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                con.prepareStatement(sqlTarjeta)) {

            ps.setString(1, t.getNumero());
            ps.setString(2, t.getFechaExp());
            ps.setString(3, t.getEstado().name());
            ps.setString(4, t.getTitular().getId());

            ps.executeUpdate();
        }

        if (t instanceof Debito) {

            String sqlDebito =
                "INSERT INTO tarjeta_debito (numero, saldo) " +
                "VALUES (?, ?)";

            try (PreparedStatement ps =
                    con.prepareStatement(sqlDebito)) {

                ps.setString(1, t.getNumero());
                ps.setDouble(2, ((Debito) t).getSaldo());

                ps.executeUpdate();
            }

        } else {

            String sqlCredito =
                "INSERT INTO tarjeta_credito (numero, limite) " +
                "VALUES (?, ?)";

            try (PreparedStatement ps =
                    con.prepareStatement(sqlCredito)) {

                ps.setString(1, t.getNumero());
                ps.setDouble(2, ((Credito) t).getLimite());

                ps.executeUpdate();
            }
        }

        con.commit();

        return "Tarjeta " + t.getNumero()
            + " guardada correctamente con estado "
            + t.getEstado().name() + ".";

    } catch (Exception e) {

        try {
            con.rollback();
        } catch (Exception errorRollback) {
            e.addSuppressed(errorRollback);
        }

        throw e;

    } finally {
        con.setAutoCommit(autoCommitAnterior);
    }
}

	@Override
	public <K> Tarjeta readone(K num) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();

		String SQL_SELECT_DEBITO = "SELECT  t.numero, t.fecha_exp, t.estado, "
				+ "        ti.id AS titular_id, ti.nombre AS titular_nombre, " + "        d.saldo "
				+ "FROM    tarjeta_debito d " + "INNER JOIN tarjeta  t  ON d.numero     = t.numero "
				+ "INNER JOIN titular  ti ON t.titular_id = ti.id " + "WHERE   d.numero = ?";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_DEBITO);
		ps.setString(1, (String) num);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Debito(rs.getString("numero"), rs.getString("fecha_exp"), EstadoTarjeta.desdeBD(rs.getString("estado")),
					new Titular(rs.getString("titular_id"), rs.getString("titular_nombre")), rs.getDouble("saldo"));
		}

		String SQL_SELECT_CREDITO = "SELECT  t.numero, t.fecha_exp, t.estado, "
				+ "        ti.id AS titular_id, ti.nombre AS titular_nombre, " + "        c.limite "
				+ "FROM    tarjeta_credito c " + "INNER JOIN tarjeta  t  ON c.numero     = t.numero "
				+ "INNER JOIN titular  ti ON t.titular_id = ti.id " + "WHERE   c.numero = ?";

		ps = con.prepareStatement(SQL_SELECT_CREDITO);
		ps.setString(1, (String) num);
		rs = ps.executeQuery();
		if (rs.next()) {
			return new Credito(rs.getString("numero"), rs.getString("fecha_exp"), EstadoTarjeta.desdeBD(rs.getString("estado")),
					new Titular(rs.getString("titular_id"), rs.getString("titular_nombre")), rs.getDouble("limite"));
		}

		return null;
	}

	@Override
	public List<Tarjeta> readall() {
		return null;
	}
}
