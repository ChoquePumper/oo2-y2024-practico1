package oo2.practico1.ejercicio1;

import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenciaTest {

	@Test
	void lineaDeSalida() throws FueraDeTerminoException {
		var probarPersistencia = new Persistencia() {

			private String ultimaLinea;

			@Override
			public void registrarInscripcion(RegistroAConcurso registro) {
				this.ultimaLinea = generarLinea(registro);
			}

			public String getLinea() {
				return this.ultimaLinea;
			}

			private String generarLinea(RegistroAConcurso registro) {
				var formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
				return String.format("%s, %s, %s", registro.fechaHora().format(formatoFecha), registro.idParticipante(), registro.idConcurso());
			}
		};

		final String esperado = "12/12/2012 12:12:56, 99, TestLineaDeSalida";
		Concurso concurso = new Concurso("TestLineaDeSalida", probarPersistencia,
				LocalDateTime.of(2012, 12, 03, 0, 0, 0), LocalDateTime.of(2012, 12, 24, 1, 0, 0), new FechaTest(LocalDateTime.of(2012, 12, 12, 12, 12, 56)));
		concurso.inscribirParticipante(new Participante(99, "ParticipanteDePrueba"));

		assertEquals(esperado, probarPersistencia.getLinea().trim());
	}
}
