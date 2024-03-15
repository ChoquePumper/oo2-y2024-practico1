package oo2.practico1.ejercicio2;

import java.util.List;
import java.util.Objects;

public abstract class Comida {

	private String nombre;
	private float costo;

	public Comida(String nombre, float costo) {
		Objects.requireNonNull(nombre);
		assert !nombre.isBlank() : "El nombre de la comida está en blanco.";

		this.nombre = nombre;
		this.costo = costo;
	}

	public float getCosto() {
		return costo;
	}

	void soloBebidas(List<Comida> lista) {

	}

	void soloPlatosPrincipales(List<Comida> lista) {

	}

	public String toString() {
		String s = this.getClass().getSimpleName();
		return String.format("%s('%s',$%.2f)", s, nombre, costo);
	}
}
