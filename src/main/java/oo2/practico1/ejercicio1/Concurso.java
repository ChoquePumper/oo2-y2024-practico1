package oo2.practico1.ejercicio1;

import oo2.practico1.ejercicio1.exceptions.FueraDeTerminoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concurso {

	private String id;
	private List<Participante> participantes;
	private LocalDateTime fechaDeInicioInscripcion;
	private LocalDateTime fechaDeFinInscripcion;
	private ProveedorFecha proveedor_fecha;
	// Cache
	private LocalDateTime cacheFechaLimitePrimerDia;

	public Concurso(String id, LocalDateTime fechaDeInicio, LocalDateTime fechaDeFin, ProveedorFecha proveedor_fecha) {
		this.id = procesarString(id);
		this.fechaDeInicioInscripcion = fechaDeInicio;
		this.fechaDeFinInscripcion = fechaDeFin;
		this.participantes = new ArrayList<Participante>();
		this.proveedor_fecha = proveedor_fecha;

		this.cacheFechaLimitePrimerDia = fechaDeInicio.plusDays(1);
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

		if (enPrimerDia(fecha))
			participante.agregarPuntos(+10);

		//TODO: Enviar mail aquí? Sí. (si puede grabar)
	}

	boolean enPrimerDia(LocalDateTime fecha) {
		// fechaDeInicio <= fecha < cacheFechaLimitePrimerDia
		return (!fecha.isBefore(fechaDeInicioInscripcion)) && fecha.isBefore(cacheFechaLimitePrimerDia);
	}

	boolean enElPeriodoDeInscripcion(LocalDateTime fecha) {
		// fechaDeInicio <= fecha < fechaDeFin
		return (!fecha.isBefore(fechaDeInicioInscripcion)) && fecha.isBefore(fechaDeFinInscripcion);
	}
}
