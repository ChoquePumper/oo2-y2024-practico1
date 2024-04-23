package oo2.practico1.ejercicio1;

import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concurso {

	private final String id;
	private final Persistencia persistencia;
	private final List<Participante> participantes;
	private final LocalDateTime fechaDeInicioInscripcion;
	private final LocalDateTime fechaDeFinInscripcion;
	private final ProveedorFecha proveedor_fecha;
	private final Notificador notificador;
	// Cache
	private final LocalDateTime cacheFechaLimitePrimerDia;

	public Concurso(String id, Persistencia persistencia, LocalDateTime fechaDeInicio, LocalDateTime fechaDeFin, ProveedorFecha proveedor_fecha, Notificador notificador) {
		this.id = procesarString(id);
		this.persistencia = Objects.requireNonNull(persistencia);
		this.fechaDeInicioInscripcion = fechaDeInicio;
		this.fechaDeFinInscripcion = fechaDeFin;
		this.participantes = new ArrayList<Participante>();
		this.proveedor_fecha = proveedor_fecha;
		this.notificador = notificador;

		this.cacheFechaLimitePrimerDia = fechaDeInicio.plusDays(1);
	}

	public Concurso(String id, Persistencia persistencia, LocalDateTime fechaDeInicio, LocalDateTime fechaDeFin, ProveedorFecha proveedor_fecha) {
		this(id, persistencia, fechaDeInicio, fechaDeFin, proveedor_fecha, (r, d1) -> { /*Nada*/ });
	}

	private String procesarString(String s) {
		Objects.requireNonNull(s);
		return s.trim();
	}

	private void agregarParticipante(Participante participante) {
		// Objects.requireNonNull(participante);
		participantes.add(participante);
	}

	public void inscribirParticipante(Participante participante) throws FueraDeTerminoException {
		Objects.requireNonNull(participante);
		LocalDateTime fecha = proveedor_fecha.hoy();
		if (!enElPeriodoDeInscripcion(fecha))
			throw new FueraDeTerminoException();

		agregarParticipante(participante);

		boolean fueEnPrimerDia = enPrimerDia(fecha);
		if (fueEnPrimerDia)
			participante.agregarPuntos(+10);

		RegistroAConcurso registro = new RegistroAConcurso(fecha, Integer.toString(participante.getId()), this.id);
		this.persistencia.registrarInscripcion(registro);
		this.notificador.notificar(registro, fueEnPrimerDia);
	}

	boolean enPrimerDia(LocalDateTime fecha) {
		// fechaDeInicio <= fecha < cacheFechaLimitePrimerDia
		return (!fecha.isBefore(fechaDeInicioInscripcion)) && fecha.isBefore(cacheFechaLimitePrimerDia);
	}

	boolean enElPeriodoDeInscripcion(LocalDateTime fecha) {
		// fechaDeInicio <= fecha < fechaDeFin
		return (!fecha.isBefore(fechaDeInicioInscripcion)) && fecha.isBefore(fechaDeFinInscripcion);
	}

	@Override
	public String toString() {
		return String.format("Concurso '%s', inscripción [%s, %s]", this.id, fechaDeInicioInscripcion, fechaDeFinInscripcion);
	}
}
