package oo2.practico1.ejercicio4;

import oo2.practico1.ejercicio1.*;
import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class MainInscripcionAConcurso {
	public static void main(String[] args) throws FueraDeTerminoException {
		ServicioEmail servicioFake = mensaje -> {
			System.out.println(mensaje.remitente());
			System.out.println(mensaje.destinatario());
			System.out.println(mensaje.asunto());
			System.out.println(mensaje.cuerpo());
		};

		var proveedorFecha = new ProveedorFecha() {
			LocalDateTime fecha = LocalDateTime.now();

			@Override
			public LocalDateTime hoy() {
				return fecha;
			}

			public void setFecha(LocalDateTime fecha) {
				this.fecha = Objects.requireNonNull(fecha);
			}
		};

		LocalDateTime fechaInscripcion = LocalDateTime.of(2024, 1, 31, 0, 0);
		Notificador notificador = new NotificadorConcursos(new Mailtrap("username", "password"));
		//Notificador notificador = new NotificadorConcursos(servicioFake);
		Concurso concurso = new Concurso("TEST0002", (r) -> {/* Sin persistencia */},
				fechaInscripcion,
				fechaInscripcion.plusDays(14),
				proveedorFecha,
				notificador
		);

		record PruebaConFecha(Participante participante, LocalDateTime fechaHora) {
		}
		;

		PruebaConFecha[] lista = {
				new PruebaConFecha(new Participante(007, "Agente", 4), LocalDate.of(2024, 1, 31).atTime(LocalTime.now())),
				new PruebaConFecha(new Participante(88, "Otro agente", 0), LocalDate.of(2024, 2, 2).atTime(LocalTime.now().plusHours(7))),
		};

		for (PruebaConFecha p : lista) {
			proveedorFecha.setFecha(p.fechaHora);
			concurso.inscribirParticipante(p.participante);
		}
	}

}
