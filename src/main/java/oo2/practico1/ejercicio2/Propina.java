package oo2.practico1.ejercicio2;

public class Propina {

	private Porcentaje porcentaje;

	public Propina(float porcentaje) {
		this.porcentaje = new Porcentaje(porcentaje);
	}

	public float calcular(float monto) {
		return porcentaje.calcular(monto);
	}

	public String toString() {
		return String.format("Propina(%s)", porcentaje);
	}

}