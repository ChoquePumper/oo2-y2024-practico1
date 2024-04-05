package oo2.practico1;

import oo2.practico1.ejercicio1.*;
import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainEjercicio1 {
	public static void main(String[] args) {

		Concurso concurso = new Concurso("enMain", new Archivo("inscripciones.txt"),
				LocalDateTime.of(2024, 3, 22, 0, 0), LocalDateTime.of(2024, 6, 30, 0, 0),
				new FechaHoy());

		Participante participante = new Participante("ParticipanteMain");
		try {
			concurso.inscribirParticipante(participante);
		} catch (FueraDeTerminoException e) {
			throw new RuntimeException(e);
		}
	}
}

class FechaHoy implements ProveedorFecha {

	@Override
	public LocalDateTime hoy() {
		return LocalDateTime.now();
	}
}

class Archivo implements Persistencia {

	File archivo;

	public Archivo(String ruta) {
		this.archivo = new File(ruta);
	}

	@Override
	public void registrarInscripcion(RegistroAConcurso registro) {
		try (FileWriter writer = new FileWriter(this.archivo, true);) {
			writer.write(generarLinea(registro));
			writer.write("\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private String generarLinea(RegistroAConcurso registro) {
		var formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
		return String.format("%s, %s, %s", registro.fechaHora().format(formatoFecha), registro.idParticipante(), registro.idConcurso());
	}
}