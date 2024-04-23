package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio1.Concurso;
import oo2.practico1.ejercicio1.Participante;
import oo2.practico1.ejercicio1.Persistencia;
import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;
import oo2.practico1.ejercicio3.accesos.ConcursoDAOJDBC;
import oo2.practico1.ejercicio3.accesos.ParticipanteDAOJDBC;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MainRegistroAConcurso {
	public static void main(String[] args) throws IOException, FueraDeTerminoException {
		// Carga propiedades desde un archivo
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		String subprotocol = prop.getProperty("jdbc.subprotocol");
		String subname = prop.getProperty("jdbc.subname.tp1e1");
		String user = prop.getProperty("jdbc.user");
		String password = prop.getProperty("jdbc.password");

		// Objeto de persistencia para concurso
		Persistencia persistencia = new RegistroABaseDeDatos(subprotocol, subname, user, password);

		var concursoDAO = new ConcursoDAOJDBC(subprotocol, subname, persistencia, user, password);
		var participanteDAO = new ParticipanteDAOJDBC(subprotocol, subname, user, password);

		// Recuperar un concurso.
		Concurso concurso = concursoDAO.find("IDTEST");

		// Crear un nuevo participante y registrarlo.
		Random random = new Random();
		String nombre = String.format("Participante Prueba %04X", random.nextInt(0, 0xFFFF));
		Participante participante = new Participante(556, nombre);
		participanteDAO.create(participante);

		// Inscribir a ese participante al concurso.
		concurso.inscribirParticipante(participante);
	}
}
