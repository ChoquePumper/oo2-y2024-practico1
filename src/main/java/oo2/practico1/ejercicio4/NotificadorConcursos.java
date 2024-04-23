package oo2.practico1.ejercicio4;

import oo2.practico1.ejercicio1.Notificador;
import oo2.practico1.ejercicio1.RegistroAConcurso;

import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class NotificadorConcursos extends NotificacionPorMail implements Notificador {
	public NotificadorConcursos(ServicioEmail servicio) {
		super(servicio);
	}

	@Override
	public void notificar(RegistroAConcurso registro, boolean fueEnPrimerDia) {
		notificar(armarMensaje(registro, fueEnPrimerDia));
	}

	// El armado del mensaje podría ir en otra clase.
	private Mensaje armarMensaje(RegistroAConcurso registro, boolean fueEnPrimerDia) {
		return new MensajeSimple(
				"notificador.concursos@example.com",
				"suscriptor@example.com",
				"Inscripción a concurso",
				armarCuerpo(registro, fueEnPrimerDia));
	}

	private String armarCuerpo(RegistroAConcurso registro, boolean fueEnPrimerDia) {
		var formatoFecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' uuuu");
		var formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String[] lineas = {
				"Fecha: " + registro.fechaHora().format(formatoFecha),
				"Hora: " + registro.fechaHora().format(formatoHora),
				String.format("Se ha realizado la inscripción del participante con identificador '%s' al concurso con identificador '%s'.",
						registro.idParticipante(), registro.idConcurso()),
		};
		Stream<String> cuerpo = Stream.of(lineas);

		// Si el participante se inscribió en el primer día del concurso, agregar esa info al mensaje.
		if (fueEnPrimerDia) {
			String[] lineasPrimerDia = {
					"El participante se inscribió en el primer día y recibe 10 puntos de bonificación.",
			};
			cuerpo = Stream.concat(cuerpo, Stream.of(lineasPrimerDia));
		}

		return String.join("\n", cuerpo.toList());
	}
}
