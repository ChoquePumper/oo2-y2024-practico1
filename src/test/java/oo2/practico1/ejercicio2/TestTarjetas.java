package oo2.practico1.ejercicio2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestTarjetas {

	Pedido crearPedido() {
		Pedido pedido = new Pedido();

		pedido.agregarAlPedido(new Bebida("Bebida AAA", 30));
		pedido.agregarAlPedido(new PlatoPrincipal("Plato AAA", 160));
		pedido.agregarAlPedido(new Bebida("Bebida BBB", 20));

		// Bebidas: 30 + 20 = 50
		// Platos principales: 160

		// Total = 210
		assertEquals(210f, pedido.calcularCostoSinDescuento());

		return pedido;
	}

	@Test
	void tarjetaVisa() {
		Pedido pedido = crearPedido();

		pedido.registrarTarjeta(new TarjetaVisa());

		// bebidas * 3% = 1.5
		assertEquals(1.5f, pedido.calcularDescuento());

		// 210 - 1.5 = 208.5
		pedido.seleccionarPropina(OpcionesPropina.opciones_posibles.PROPINA_2PC.toString());
		// 208.5 * 2% = 4.17
		assertEquals(4.17f, pedido.calcularPropina());
		// 208.5 + 4.17 = 212.67
		assertEquals(212.67f, pedido.calcularCosto());

	}

	@Test
	void tarjetaMastercard() {
		Pedido pedido = crearPedido();

		pedido.registrarTarjeta(new TarjetaMastercard());

		// platos principales * 2% = 160 / 50 = 16 / 5 = 3.2
		assertEquals(3.2f, pedido.calcularDescuento());

		// 210 - 3.2 = 206.8
		pedido.seleccionarPropina(OpcionesPropina.opciones_posibles.PROPINA_5PC.toString());
		// 206.8 * 5% = 2.068 * 5 = 10.34
		assertEquals(10.34f, pedido.calcularPropina());
		// 206.8 + 10.34 = 217.14
		assertEquals(217.14f, pedido.calcularCosto());

	}

	@Test
	void tarjetaComarcaPlus() {
		Pedido pedido = crearPedido();

		pedido.registrarTarjeta(new TarjetaComarcaPlus());

		// total * 2% = 2.10 * 2 = 4.20
		assertEquals(4.2f, pedido.calcularDescuento());

		// 210 - 4.2 = 205.8
		pedido.seleccionarPropina(OpcionesPropina.opciones_posibles.PROPINA_10PC.toString());
		// 205.8 * 10% = 205.8 / 10 = 20.58
		assertEquals(20.58f, pedido.calcularPropina());
		// 205.8 + 20.58 = 226.38
		assertEquals(226.38f, pedido.calcularCosto());

	}

	@Test
	void tarjetaViedma() {
		Pedido pedido = crearPedido();

		pedido.registrarTarjeta(new TarjetaViedma());

		// No hay descuento
		assertEquals(0f, pedido.calcularDescuento());

		pedido.seleccionarPropina(OpcionesPropina.opciones_posibles.PROPINA_5PC.toString());
		// 210 * 5% = 210 / 10 / 2 = 21 / 2 = 10.5
		assertEquals(10.5f, pedido.calcularPropina());
		// 210 + 10.5 = 220.5
		assertEquals(220.5f, pedido.calcularCosto());

	}

}
