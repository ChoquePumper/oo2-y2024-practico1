package oo2.practico1.ejercicio2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

record RegistroCostoCalculado(LocalDateTime fechaHora, float monto) {
	String generarLinea() {
		var formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
		return String.join(" || ", fechaHora.format(formatoFecha), Float.toString(monto));
	}
}
