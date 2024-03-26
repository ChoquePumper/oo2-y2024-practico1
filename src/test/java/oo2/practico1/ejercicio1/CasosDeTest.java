package oo2.practico1.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;

class CasosDeTest {

	// Un participante se inscribe en un concurso
	@Test
	void inscribirEnConcurso() {
		FechaTest proveedor_fecha = new FechaTest();
		LocalDateTime fecha_inicio = LocalDateTime.of(2023, 01, 18, 0, 0, 0);
		LocalDateTime fecha_fin = LocalDateTime.of(2023, 01, 26, 0, 0, 0);
		Concurso concurso = new Concurso("ConcursoTestInscripción", new SinPersistencia(), fecha_inicio, fecha_fin, proveedor_fecha);

		record RecordInscribir(Participante participante, LocalDateTime fecha, boolean resultadoEsperado) {

		}
		;

		RecordInscribir pruebas[] = {
				new RecordInscribir(new Participante("Aaaaaa"), LocalDateTime.of(2023, 01, 23, 0, 0, 0), true),
				new RecordInscribir(new Participante("JustoAFuera"), LocalDateTime.of(2023, 01, 26, 0, 0, 0), false),
				new RecordInscribir(new Participante("AntesDeFin"), LocalDateTime.of(2023, 01, 25, 23, 59, 59), true),
				new RecordInscribir(new Participante("AlPrincipio"), LocalDateTime.of(2023, 01, 18, 0, 0, 0), true),
				new RecordInscribir(new Participante("MuyAntes"), LocalDateTime.of(2023, 01, 17, 23, 59, 59), false),};

		for (RecordInscribir inscripcion : pruebas) {
			proveedor_fecha.setFecha(inscripcion.fecha());
			assertEquals(inscripcion.resultadoEsperado(), inscribir(concurso, inscripcion.participante()));
		}
	}

	boolean inscribir(Concurso concurso, Participante participante) {
		try {
			concurso.inscribirParticipante(participante);
			return true;
		} catch (FueraDeTerminoException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	// Un participante se inscribe en un concurso el primer día de abierta la
	// inscripción.
	@Test
	void primerDia() {
		FechaTest proveedor_fecha = new FechaTest();
		LocalDateTime fecha_inicio = LocalDateTime.of(2023, 01, 18, 0, 0, 0);
		LocalDateTime fecha_fin = LocalDateTime.of(2023, 01, 26, 0, 0, 0);
		Concurso concurso = new Concurso("ConcursoTestPrimerDia", new SinPersistencia(), fecha_inicio, fecha_fin, proveedor_fecha);

		Participante participante_en_1er_dia = new Participante("En Primer Dia");
		Participante otro_participante = new Participante("Otro");

		try {
			// Fecha y hora dentro del primer dia.
			proveedor_fecha.setFecha(LocalDateTime.of(2023, 01, 18, 11, 00, 00));
			concurso.inscribirParticipante(participante_en_1er_dia);
			// Otra fecha.
			proveedor_fecha.setFecha(LocalDateTime.of(2023, 01, 19, 18, 00, 00));
			concurso.inscribirParticipante(otro_participante);
		} catch (FueraDeTerminoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e);
		}

		// En el primer dia de inscripción, el participante suma 10 puntos.
		assertEquals(10, participante_en_1er_dia.mostrarPuntos());
		// No suma puntos en cualquier otro día.
		assertEquals(0, otro_participante.mostrarPuntos());

	}

	// Un participante intenta inscribirse fuera del rango de inscripción.
	@Test
	void fueraDeRango() {
		FechaTest proveedor_fecha = new FechaTest();
		LocalDateTime fecha_inicio = LocalDateTime.of(2023, 01, 18, 0, 0, 0);
		LocalDateTime fecha_fin = LocalDateTime.of(2023, 01, 26, 0, 0, 0);
		Concurso concurso = new Concurso("ConcursoTestFuera", new SinPersistencia(), fecha_inicio, fecha_fin, proveedor_fecha);

		record RecordInscribir(Participante participante, LocalDateTime fecha, boolean resultadoEsperado) {

		}
		;

		RecordInscribir pruebas[] = {
				new RecordInscribir(new Participante("JustoAFuera"), LocalDateTime.of(2023, 01, 26, 0, 0, 0), false),
				new RecordInscribir(new Participante("MuyAntes"), LocalDateTime.of(2023, 01, 17, 23, 59, 59), false),};

		for (RecordInscribir inscripcion : pruebas) {
			proveedor_fecha.setFecha(inscripcion.fecha());
			assertEquals(inscripcion.resultadoEsperado(), inscribir(concurso, inscripcion.participante()));
		}
	}
}
