package oo2.practico1.ejercicio2;

// Los pagos con tarjeta de crédito Comarca Plus tienen un descuento del 2%
// sobre el costo total (bebidas + platos principales).

public class TarjetaComarcaPlus implements TarjetaDeCredito {
	static final Porcentaje PC_DESCUENTO_DE_COSTO_TOTAL = new Porcentaje(2);

	@Override
	public float calcularDescuento(ListaComidas comidas) {
		return PC_DESCUENTO_DE_COSTO_TOTAL.calcular(
				comidas.stream().map(Comida::getCosto).reduce(Float::sum).orElse(0f));
	}

	// TODO: 2% del costo total (bebidas + platos principales)
}
