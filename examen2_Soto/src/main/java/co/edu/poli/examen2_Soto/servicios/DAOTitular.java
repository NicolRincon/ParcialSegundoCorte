package co.edu.poli.examen2_Soto.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.examen2_Soto.modelo.Titular;

/**
 * Clase de acceso a datos (DAO) encargada de gestionar las operaciones CRUD 
 * para la entidad {@link Titular} en la base de datos MySQL.
 */
public class DAOTitular implements CRUD<Titular> {

	/**
	 * Método para registrar un nuevo titular en la base de datos.
	 * (Método no requerido o pendiente de implementación para este módulo).
	 * 
	 * @param t Objeto Titular a registrar.
	 * @return null en esta versión.
	 */
	@Override
	public String create(Titular t) {
		return null;
	}
	
	/**
	 * Busca y retorna un titular específico según su identificador único.
	 * (Método no requerido o pendiente de implementación para este módulo).
	 * 
	 * @param <K> Tipo de dato de la clave de búsqueda.
	 * @param id Identificador del titular a consultar.
	 * @return null en esta versión.
	 * @throws Exception si ocurre un error de consulta.
	 */
	@Override
	public <K> Titular readone(K id) throws Exception {
		return null;
	}

	/**
	 * Consulta y retorna una lista con todos los titulares registrados en la base de datos.
	 * Se conecta a la BD mediante el servicio Singleton y ejecuta la sentencia SQL para mapear los resultados.
	 * 
	 * @return Una lista de objetos {@link Titular} con la información de cada propietario.
	 * @throws Exception si ocurre un error al ejecutar la consulta SQL o al establecer la conexión.
	 */
	@Override
	public List<Titular> readall() throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();
		List<Titular> lista = new ArrayList<>();

		String SQL_SELECT_TITULAR = "SELECT ti.id AS titular_id, ti.nombre AS titular_nombre " 
		+ "FROM titular ti;";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_TITULAR);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Titular t = new Titular(rs.getString("titular_id"), rs.getString("titular_nombre"));
			lista.add(t);
		}
		return lista;
	}

}
