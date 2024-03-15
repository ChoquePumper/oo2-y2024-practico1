package oo2.practico1.ejercicio2;

// Los pagos con tarjeta de crédito Visa tienen un descuento del 3% sobre el
// costo total de las bebidas.

public class TarjetaVisa implements TarjetaDeCredito {
	static final Porcentaje PC_DESCUENTO_POR_BEBIDA = new Porcentaje(3);

	// TODO: 3% de descuento en bebidas
	@Override
	public float calcularDescuento(ListaComidas comidas) {
		float suma_costo = comidas.soloBebidas().stream().map(Comida::getCosto)
				.reduce(Float::sum).orElse(0f);
		return PC_DESCUENTO_POR_BEBIDA.calcular(suma_costo);
	}
}
