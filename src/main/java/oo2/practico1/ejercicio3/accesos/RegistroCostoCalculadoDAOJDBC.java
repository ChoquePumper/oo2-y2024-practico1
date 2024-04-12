package oo2.practico1.ejercicio3.accesos;

import oo2.practico1.ejercicio2.RegistroCostoCalculado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroCostoCalculadoDAOJDBC extends ObjetoJDBC implements ObjetoDAO<RegistroCostoCalculado> {

	public RegistroCostoCalculadoDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
	}

	public RegistroCostoCalculadoDAOJDBC(String subprotocolo, String subnombre, String user, String password) {
		super(subprotocolo, subnombre, user, password);
	}


	@Override
	public void create(RegistroCostoCalculado registro) {
		try (Connection conn = getConnection();
			 PreparedStatement sent = conn.prepareStatement(
					 "INSERT INTO CalculoCostoDePedido (fecha_hora, monto) values (?,?);")) {
			sent.setObject(1, registro.fechaHora());
			sent.setFloat(2, registro.monto());
			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(RegistroCostoCalculado registro) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(RegistroCostoCalculado registro) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public RegistroCostoCalculado find(String id) {
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT fecha_hora, monto FROM CalculoCostoDePedido WHERE idRegistro = ? ;");
			 ResultSet res = setearDatos(st, Integer.parseInt(id)).executeQuery()) {
			if (res.next())
				return deResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RegistroCostoCalculado> findAll() {
		List<RegistroCostoCalculado> lista = new ArrayList<>();
		try (Connection conn = getConnection();
			 PreparedStatement st = conn.prepareStatement("SELECT fecha_hora, monto FROM CalculoCostoDePedido;");
			 ResultSet res = st.executeQuery()) {
			while (res.next())
				lista.add(deResultSet(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Auxiliares
	private RegistroCostoCalculado deResultSet(ResultSet res) throws SQLException {
		return new RegistroCostoCalculado(
				res.getObject("fecha_hora", LocalDateTime.class),
				res.getFloat("monto")
		);
	}
}
