package oo2.practico1.ejercicio3.accesos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import oo2.practico1.ejercicio1.Participante;

public class ParticipanteDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Participante> {

	public ParticipanteDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	public ParticipanteDAOJDBC(String subprotocolo, String subnombre, String user, String password) {
		super(subprotocolo, subnombre, user, password);
	}

	@Override
	public void create(Participante obj) {
		String sql = "INSERT INTO Participantes (nombre, puntos) values (?,?);";
		// Incluir el id si no es igual a 0.
		var valores = new ArrayList<>();
		if (obj.getId() != 0) {
			valores.add(obj.getId());
			sql = "INSERT INTO Participantes (idParticipante, nombre, puntos) values (?,?,?);";
		}
		valores.addAll(List.of(obj.getNombre(), obj.mostrarPuntos()));
		try (Connection conn = getConnection();
		     PreparedStatement sent = conn.prepareStatement(sql)) {
			setearDatos(sent, valores.toArray());
			sent.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
