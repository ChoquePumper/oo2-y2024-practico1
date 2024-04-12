package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio2.Persistencia;
import oo2.practico1.ejercicio2.RegistroCostoCalculado;
import oo2.practico1.ejercicio3.accesos.RegistroCostoCalculadoDAOJDBC;

public class PersistenciaCalculoDeCosto implements Persistencia {

	private final RegistroCostoCalculadoDAOJDBC daojdbc;

	public PersistenciaCalculoDeCosto(String subprotocolo, String subnombre, String user, String password) {
		this.daojdbc = new RegistroCostoCalculadoDAOJDBC(subprotocolo,subnombre,user,password);
	}
	@Override
	public void registrarCalculoDeCosto(RegistroCostoCalculado registro) {
		daojdbc.create(registro);
	}
}
