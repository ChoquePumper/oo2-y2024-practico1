package oo2.practico1.ejercicio1;

import java.time.LocalDateTime;
import java.util.Objects;

public class FechaTest implements ProveedorFecha {

	private LocalDateTime actual;

	public FechaTest() {
		setFecha(LocalDateTime.now());
	}

	public FechaTest(LocalDateTime fecha) {
		setFecha(fecha);
	}

	@Override
	public LocalDateTime hoy() {
		return actual;
	}

	public void setFecha(LocalDateTime fecha) {
		Objects.requireNonNull(fecha);
		this.actual = fecha;
	}

}
