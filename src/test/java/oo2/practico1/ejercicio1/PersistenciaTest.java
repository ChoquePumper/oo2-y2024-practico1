package oo2.practico1.ejercicio1;

import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenciaTest {

	@Test
	void lineaDeSalida() throws FueraDeTerminoException {
		Persistencia probarPersistencia = new Persistencia() {
			final String esperado = "12/12/2012 12:12:56, ParticipanteDePrueba, TestLineaDeSalida";

			@Override
			public void registrarInscripcion(String lineaDeRegistro) {
				assertEquals(esperado, lineaDeRegistro);
			}
		};

		Concurso concurso = new Concurso("TestLineaDeSalida", probarPersistencia,
				LocalDateTime.of(2012, 12, 03, 0, 0, 0), LocalDateTime.of(2012, 12, 24, 1, 0, 0), new FechaTest(LocalDateTime.of(2012, 12, 12, 12, 12, 56)));

		concurso.inscribirParticipante(new Participante("ParticipanteDePrueba"));
	}
}
