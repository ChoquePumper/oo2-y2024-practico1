package oo2.practico1.ejercicio1;

// Este archivo solo fue creado para hacer algunos tests antes de prueba sobre los métodos de Concurso.

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TestFechas {

	@Test
	void enElPeriodoDeInscripcion() {
		FechaTest proveedor_fecha = new FechaTest(); // Solo para el constructor de Concurso
		LocalDateTime fecha_inicio = LocalDateTime.of(2023, 01, 23, 0, 0, 0);
		LocalDateTime fecha_fin = LocalDateTime.of(2023, 01, 30, 0, 0, 0);

		Concurso concurso = new Concurso("ConcursoTestInscripción", new SinPersistencia(), fecha_inicio, fecha_fin, proveedor_fecha);
		for (int dia = 23; dia <= 29; dia++)
			assertEquals(concurso.enElPeriodoDeInscripcion(LocalDateTime.of(2023, 01, dia, 0, 0, 0)), true);
		assertEquals(concurso.enElPeriodoDeInscripcion(LocalDateTime.of(2023, 01, 30, 0, 0, 0)), false);
		assertEquals(concurso.enElPeriodoDeInscripcion(LocalDateTime.of(2023, 01, 22, 23, 59, 59)), false);
	}

	@Test
	void primerDia() {
		FechaTest proveedor_fecha = new FechaTest(); // Solo para el constructor de Concurso
		LocalDateTime fecha_inicio = LocalDateTime.of(2023, 01, 23, 0, 0, 0);
		LocalDateTime fecha_fin = LocalDateTime.of(2023, 01, 30, 0, 0, 0);

		LocalDateTime fecha1erDia = LocalDateTime.of(2023, 01, 23, 0, 0, 0);
		LocalDateTime otraFecha = LocalDateTime.of(2023, 01, 27, 0, 0, 0);
		LocalDateTime fechaAntes = LocalDateTime.of(2023, 01, 21, 0, 0, 0);

		Concurso concurso = new Concurso("ConcursoTestPrimerDia", new SinPersistencia(), fecha_inicio, fecha_fin, proveedor_fecha);
		assertEquals(concurso.enPrimerDia(fecha1erDia), true);
		assertEquals(concurso.enPrimerDia(otraFecha), false);
		assertEquals(concurso.enPrimerDia(fechaAntes), false);
	}

}
