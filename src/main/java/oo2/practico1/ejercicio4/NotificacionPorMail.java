package oo2.practico1.ejercicio4;

import java.util.Objects;

public abstract class NotificacionPorMail {

	private final ServicioEmail servicio;

	public NotificacionPorMail(ServicioEmail servicio) {
		this.servicio = Objects.requireNonNull(servicio, "No se especificó el servicio.");
	}

	public final void notificar(Mensaje mensaje) {
		servicio.enviarMensaje(mensaje);
		System.out.println("Mensaje enviado.");
	}
}
