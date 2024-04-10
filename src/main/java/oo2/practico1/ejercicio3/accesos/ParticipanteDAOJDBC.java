package oo2.practico1.ejercicio3.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oo2.practico1.ejercicio1.Participante;

public class ParticipanteDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Participante> {

	public ParticipanteDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void create(Participante obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public void update(Participante obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(Participante obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public Participante find(String id) {
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT idParticipante, nombre, puntos FROM Participantes WHERE idParticipante = ? ;");
			 ResultSet res = setearDatos(st, Integer.parseInt(id)).executeQuery()) {
			if (res.next())
				return deResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Participante> findAll() {
		List<Participante> lista = new ArrayList<>();
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT idParticipante, nombre, puntos FROM Participantes;");
			 ResultSet res = st.executeQuery()) {
			while (res.next())
				lista.add(deResultSet(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Auxiliares
	private Participante deResultSet(ResultSet res) throws SQLException {
		return new Participante(
				res.getInt("idParticipante"),
				res.getString("nombre"),
				res.getInt("puntos")
		);
	}
}
