package oo2.practico1.ejercicio3;

import oo2.practico1.ejercicio2.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;

public class MainCalcularCostoPedido {
	public static void main(String[] args) throws IOException {
		// Carga propiedades desde un archivo
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		Pedido pedido = new Pedido(
				() -> LocalDateTime.now(),
				new PersistenciaCalculoDeCosto(
						prop.getProperty("jdbc.subprotocol"),
						prop.getProperty("jdbc.subname.tp1e2"),
						prop.getProperty("jdbc.user"),
						prop.getProperty("jdbc.password")
				)
		);
		Comida[] comidas = {
				new PlatoPrincipal("MiPlatoPrincipal", 2000),
				new Bebida("Agua", 580.99f),
				new Bebida("Energizante",1050)
		};
		Arrays.asList(comidas).forEach( pedido::agregarAlPedido );

		pedido.seleccionarPropina(OpcionesPropina.opciones_posibles.PROPINA_10PC.toString());

		pedido.registrarTarjeta(new TarjetaVisa());
		float monto = pedido.calcularCosto();
		System.out.println("El costo es: "+monto);

	}
}
