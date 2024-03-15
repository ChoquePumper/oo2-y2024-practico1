package oo2.practico1.ejercicio2;

import java.util.List;

public class Bebida extends Comida {

	public Bebida(String nombre, float costo) {
		super(nombre, costo);
	}

	void soloBebidas(List<Comida> lista) {
		lista.add(this);
	}

}
