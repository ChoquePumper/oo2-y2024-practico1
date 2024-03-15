package oo2.practico1.ejercicio2;

// Los pagos con tarjeta de crédito Mastercard tienen un 2% de descuento sobre
// el costo total de los platos principales.

public class TarjetaMastercard implements TarjetaDeCredito {
	static final Porcentaje PC_DESCUENTO_POR_PLATO_PRINCIPAL = new Porcentaje(2);

	@Override
	public float calcularDescuento(ListaComidas comidas) {
		float suma_costo = comidas.soloPlatosPrincipales().stream().map(Comida::getCosto)
				.reduce(Float::sum).orElse(0f);
		return PC_DESCUENTO_POR_PLATO_PRINCIPAL.calcular(suma_costo);
	}
	// TODO: 2% de descuento en Platos Principales
}
