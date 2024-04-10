package oo2.practico1.ejercicio3.accesos;

import oo2.practico1.ejercicio1.RegistroAConcurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroAConcursoJDBC extends ObjetoJDBC implements ObjetoDAO<RegistroAConcurso> {
	
	public RegistroAConcursoJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
	}

	@Override
	public void create(RegistroAConcurso registro) {
		try (Connection conn = getConnection();
			 PreparedStatement sent = conn.prepareStatement(
					 "INSERT INTO RegistrosAConcursos (fechaRegistro, idParticipante, idConcurso) values (?,?,?);")) {
			sent.setObject(1, registro.fechaHora());
			sent.setString(2, registro.idParticipante());
			sent.setString(3, registro.idConcurso());
			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(RegistroAConcurso registro) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(RegistroAConcurso registro) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public RegistroAConcurso find(String id) {
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT fechaRegistro, idParticipante, idConcurso FROM RegistrosAConcursos WHERE idRegistro = ? ;");
			 ResultSet res = setearDatos(st, id).executeQuery()) {
			if (res.next())
				return deResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RegistroAConcurso> findAll() {
		List<RegistroAConcurso> lista = new ArrayList<>();
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT fechaRegistro, idParticipante, idConcurso FROM RegistrosAConcursos;");
			 ResultSet res = st.executeQuery()) {
			while (res.next())
				lista.add(deResultSet(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private RegistroAConcurso deResultSet(ResultSet res) throws SQLException {
		return new RegistroAConcurso(
				res.getObject("fechaRegistro", LocalDateTime.class),
				res.getObject("idParticipante", Integer.class).toString(),
				res.getString("idConcurso")
		);
	}
}
