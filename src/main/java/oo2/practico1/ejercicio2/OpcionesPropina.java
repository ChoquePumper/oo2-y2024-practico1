package oo2.practico1.ejercicio2;

import java.util.HashMap;

public class OpcionesPropina {
	public static enum opciones_posibles {
		PROPINA_2PC, PROPINA_5PC, PROPINA_10PC;
	};

	private HashMap<String, Propina> mapa_valores;

	public OpcionesPropina() {
		this.mapa_valores = new HashMap<String, Propina>();
		this.mapa_valores.put(opciones_posibles.PROPINA_2PC.toString(), new Propina(2));
		this.mapa_valores.put(opciones_posibles.PROPINA_5PC.toString(), new Propina(5));
		this.mapa_valores.put(opciones_posibles.PROPINA_10PC.toString(), new Propina(10));
	}

	// Este método puede estar en una interfaz.
	public Propina get(String clave) {
		if (!this.mapa_valores.containsKey(clave))
			throw new RuntimeException("No existe propina para la clave " + clave);
		return this.mapa_valores.get(clave);
	}

}
