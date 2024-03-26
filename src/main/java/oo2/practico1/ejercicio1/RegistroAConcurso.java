package oo2.practico1.ejercicio1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

record RegistroAConcurso(LocalDateTime fechaHora, String idParticipante, String idConcurso) {
	String generarLinea() {
		var formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
		return String.format("%s, %s, %s", fechaHora.format(formatoFecha), idParticipante(), idConcurso());
	}
}
