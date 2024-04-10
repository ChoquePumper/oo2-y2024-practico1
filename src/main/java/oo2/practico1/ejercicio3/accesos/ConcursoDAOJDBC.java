package oo2.practico1.ejercicio3.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import oo2.practico1.ejercicio1.Concurso;
import oo2.practico1.ejercicio1.Persistencia;

public class ConcursoDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Concurso> {

	private Persistencia persistencia = (registro) -> {
	}; // Nada

	public ConcursoDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	public ConcursoDAOJDBC(String subprotocolo, String subnombre, String user, String password) {
		super(subprotocolo, subnombre, user, password);
	}

	/**
	 * @param subprotocolo
	 * @param subnombre
	 * @param persistencia Setea un objeto de Persistencia para los objetos Concurso obtenidos.
	 */
	public ConcursoDAOJDBC(String subprotocolo, String subnombre, Persistencia persistencia) {
		this(subprotocolo, subnombre);
		this.persistencia = Objects.requireNonNull(persistencia);
	}

	public ConcursoDAOJDBC(String subprotocolo, String subnombre, Persistencia persistencia, String user, String password) {
		super(subprotocolo, subnombre, user, password);
		this.persistencia = Objects.requireNonNull(persistencia);
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
		List<Concurso> lista = new ArrayList<>();
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT idConcurso, fechaDeInicioInscripcion, fechaDeFinInscripcion FROM Concursos;");
			 ResultSet res = st.executeQuery()) {
			while (res.next())
				lista.add(deResultSet(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
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
				this.persistencia,
				res.getObject("fechaDeInicioInscripcion", LocalDateTime.class),
				res.getObject("fechaDeFinInscripcion", LocalDateTime.class),
				LocalDateTime::now
		);
	}
}
