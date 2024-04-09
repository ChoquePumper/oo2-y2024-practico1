package oo2.practico1.ejercicio3.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import oo2.practico1.ejercicio1.Concurso;

public class ConcursoDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Concurso> {

	public ConcursoDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void create(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public void update(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public Concurso find(String id) {
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT idConcurso, fechaDeInicioInscripcion, fechaDeFinInscripcion FROM Concursos WHERE idConcurso = ? ;");
			 ResultSet res = setearDatos(st, id).executeQuery()) {
			if (res.next())
				return deResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Concurso> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}

	// Auxiliares

	/**
	 * Crea el objeto Concurso con los datos del ResultSet
	 *
	 * @param res ResultSet
	 * @return Concurso
	 * @throws SQLException
	 * @implNote Aún no sé que objetos poner para persistencia y proveedor_fecha.
	 */
	private Concurso deResultSet(ResultSet res) throws SQLException {
		return new Concurso(
				res.getString("idConcurso"),
				registro -> {
				},
				res.getObject("fechaDeInicioInscripcion", LocalDateTime.class),
				res.getObject("fechaDeFinInscripcion", LocalDateTime.class),
				LocalDateTime::now
		);
	}
}
