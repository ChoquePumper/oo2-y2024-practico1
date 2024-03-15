package oo2.practico1.ejercicio2;

public class Porcentaje {

	private float porcentaje;

	public Porcentaje(float porcentaje) {
		if (porcentaje < 0)
			throw new RuntimeException("Porcentaje inválido.");
		this.porcentaje = porcentaje;
	}

	public float calcular(float monto) {
		return monto * this.porcentaje / 100;
	}

	public String toString() {
		return String.format("%.1f%%", porcentaje);
	}
}
