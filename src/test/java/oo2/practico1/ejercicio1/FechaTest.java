package oo2.practico1.ejercicio1;

import java.time.LocalDateTime;
import java.util.Objects;

public class FechaTest implements ProveedorFecha {

	private LocalDateTime actual;

	FechaTest() {
		setFecha(LocalDateTime.now());
	}

	FechaTest(LocalDateTime fecha) {
		setFecha(fecha);
	}

	@Override
	public LocalDateTime hoy() {
		return actual;
	}

	void setFecha(LocalDateTime fecha) {
		Objects.requireNonNull(fecha);
		this.actual = fecha;
	}

}
