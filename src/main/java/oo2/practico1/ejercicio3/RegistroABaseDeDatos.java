package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio1.Persistencia;
import oo2.practico1.ejercicio1.RegistroAConcurso;
import oo2.practico1.ejercicio3.accesos.RegistroAConcursoJDBC;

public class RegistroABaseDeDatos implements Persistencia {

	private final RegistroAConcursoJDBC registroDAOJDBC;

	public RegistroABaseDeDatos(String subprotocolo, String subnombre) {
		this.registroDAOJDBC = new RegistroAConcursoJDBC(subprotocolo, subnombre);
	}

	@Override
	public void registrarInscripcion(RegistroAConcurso registro) {
		registroDAOJDBC.create(registro);
	}
}
