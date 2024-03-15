package oo2.practico1.ejercicio2;

import java.util.List;

public class PlatoPrincipal extends Comida {

	public PlatoPrincipal(String nombre, float costo) {
		super(nombre, costo);
	}

	@Override
	void soloPlatosPrincipales(List<Comida> lista) {
		lista.add(this);
	}

}
