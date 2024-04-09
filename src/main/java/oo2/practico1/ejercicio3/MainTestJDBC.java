package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio1.Concurso;
import oo2.practico1.ejercicio1.Participante;
import oo2.practico1.ejercicio3.accesos.ConcursoDAOJDBC;
import oo2.practico1.ejercicio3.accesos.ObjetoDAO;
import oo2.practico1.ejercicio3.accesos.ParticipanteDAOJDBC;

import java.io.IOException;
import java.util.Properties;

public class MainTestJDBC {
	public static void main(String[] args) throws IOException {
		// Carga propiedades desde un archivo
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		// Prepara los DAOs.
		ObjetoDAO<Participante> daoParticipante =
				new ParticipanteDAOJDBC(prop.getProperty("jdbc.subprotocol"), prop.getProperty("jdbc.subname"));
		ObjetoDAO<Concurso> daoConcurso =
				new ConcursoDAOJDBC(prop.getProperty("jdbc.subprotocol"), prop.getProperty("jdbc.subname"));

		mostrar(daoParticipante.find("1"));
		mostrar(daoConcurso.find("IDTEST"));
	}

	private static void mostrar(Object obj) {
		System.out.println(obj);
	}

	private static void mostrar(Concurso c) {
		mostrar((Object) c);
	}

	private static void mostrar(Participante p) {
		mostrar((Object) p);
		mostrar(p.getId());
		mostrar(p.getNombre());
		mostrar(p.mostrarPuntos());
	}
}
