package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio1.Concurso;
import oo2.practico1.ejercicio1.Participante;
import oo2.practico1.ejercicio2.RegistroCostoCalculado;
import oo2.practico1.ejercicio3.accesos.ConcursoDAOJDBC;
import oo2.practico1.ejercicio3.accesos.ObjetoDAO;
import oo2.practico1.ejercicio3.accesos.ParticipanteDAOJDBC;
import oo2.practico1.ejercicio3.accesos.RegistroCostoCalculadoDAOJDBC;

import java.io.IOException;
import java.util.Properties;

public class MainTestJDBC {
	public static void main(String[] args) throws IOException {
		// Carga propiedades desde un archivo
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		// Prepara los DAOs.
		String subprotocolo = prop.getProperty("jdbc.subprotocol"), subnombre = prop.getProperty("jdbc.subname.tp1e1");
		String user = prop.getProperty("jdbc.user"), password = prop.getProperty("jdbc.password");
		ObjetoDAO<Participante> daoParticipante =
				new ParticipanteDAOJDBC(subprotocolo, subnombre, user, password);
		ObjetoDAO<Concurso> daoConcurso =
				new ConcursoDAOJDBC(subprotocolo, subnombre, user, password);

		daoConcurso.findAll().forEach(MainTestJDBC::mostrar);
		daoParticipante.findAll().forEach(MainTestJDBC::mostrar);
		//mostrar(daoParticipante.find("1"));
		//mostrar(daoConcurso.find("IDTEST"));


		subnombre = prop.getProperty("jdbc.subname.tp1e2");
		ObjetoDAO<RegistroCostoCalculado> daoRegistroCostoCalculado =
				new RegistroCostoCalculadoDAOJDBC(subprotocolo, subnombre, user, password);

		daoRegistroCostoCalculado.findAll().forEach(MainTestJDBC::mostrar);
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

	private static void mostrar(RegistroCostoCalculado rcc) {
		mostrar((Object) rcc);
	}
}
