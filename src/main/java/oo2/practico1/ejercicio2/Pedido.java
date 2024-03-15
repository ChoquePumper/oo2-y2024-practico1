package oo2.practico1.ejercicio2;

import java.util.ArrayList;

public class Pedido {

	private ListaComidas lista_comidas;
	private TarjetaDeCredito tarjeta;

	private OpcionesPropina opciones_propina;
	private Propina propina;

	public Pedido() {
		this.tarjeta = null;
		this.lista_comidas = new ListaComidas();
		// this.seleccion_propina = crearListaDePropinas();
		this.opciones_propina = new OpcionesPropina();
		this.propina = null;
	}

	public void agregarAlPedido(Comida comida) {
		lista_comidas.agregar(comida);
	}

	public void registrarTarjeta(TarjetaDeCredito tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void seleccionarPropina(String clave_propina) {
		this.propina = opciones_propina.get(clave_propina);
	}

	public float calcularDescuento() {
		// Si no hay tarjeta, no hay descuento.
		if (tarjeta == null)
			return 0;
		// Si hay, calcular el descuento según la tarjeta.
		float descuento_por_tarjeta = tarjeta.calcularDescuento(lista_comidas);
		return descuento_por_tarjeta;
	}

	// Suma el costo total de la lista de comidas
	public float calcularCostoSinDescuento() {
		float suma_costo = 0;
		ArrayList<Comida> lista = new ArrayList<Comida>();
		lista.addAll(lista_comidas.soloBebidas());
		lista.addAll(lista_comidas.soloPlatosPrincipales());
		for (Comida comida : lista)
			suma_costo += comida.getCosto();
		return suma_costo;
	}

	public float calcularCosto() {
		return calcularCostoSinDescuento() - calcularDescuento() + calcularPropina();
	}

	public float calcularPropina() {
		// La propina es obligatoria
		if (propina == null)
			// TODO: Crear una subclase de Exception
			throw new RuntimeException("No se seleccionó propina.");
		return propina.calcular(calcularCostoSinDescuento() - calcularDescuento());
	}

}
