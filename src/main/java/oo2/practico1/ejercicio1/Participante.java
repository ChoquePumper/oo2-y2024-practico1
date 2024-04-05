package oo2.practico1.ejercicio1;

import java.util.Objects;

public class Participante {

	private int id = 0;
	private String nombre;
	private int puntos;

	public Participante(String nombre) {
		Objects.requireNonNull(nombre);
		if (nombre.isBlank())
			throw new IllegalArgumentException();
		this.nombre = nombre;
		this.puntos = 0;
	}

	public Participante(int id, String nombre) {
		this(nombre);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarPuntos(int puntos) {
		this.puntos += puntos;
	}

	public int mostrarPuntos() {
		return this.puntos;
	}

	@Override
	public String toString() {
		return String.format("Participante(%s, %d puntos)", nombre, puntos);
	}
}
