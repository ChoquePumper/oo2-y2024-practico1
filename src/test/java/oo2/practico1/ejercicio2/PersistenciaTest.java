package oo2.practico1.ejercicio2;

import oo2.practico1.ejercicio1.FechaTest;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenciaTest {
	private static class EnMemoria implements Persistencia {
		final StringWriter writer = new StringWriter();

		@Override
		public void registrarCalculoDeCosto(RegistroCostoCalculado registro) {
			writer.append(generarLinea(registro)).write("\n");
		}

		String getTexto() {
			return writer.toString();
		}

		private String generarLinea(RegistroCostoCalculado registro) {
			var formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
			return String.join(" || ", registro.fechaHora().format(formatoFecha), Float.toString(registro.monto()));
		}
	}

	@Test
	void lineaDeSalida() {
		FechaTest proveedorFecha = new FechaTest();
		EnMemoria memoria = new EnMemoria();
		Pedido pedido = TestTarjetas.crearPedido(proveedorFecha, memoria);

		proveedorFecha.setFecha(LocalDateTime.of(2024, 03, 12, 15, 44, 32));
		pedido.seleccionarPropina("PROPINA_2PC");
		pedido.calcularCosto();

		proveedorFecha.setFecha(LocalDateTime.of(2024, 03, 12, 15, 50, 17));
		pedido.seleccionarPropina("PROPINA_5PC");
		pedido.calcularCosto();

		proveedorFecha.setFecha(LocalDateTime.of(2024, 03, 18, 20, 19, 58));
		pedido.seleccionarPropina("PROPINA_10PC");
		pedido.calcularCosto();

		Object[] lineas = memoria.getTexto().lines().toArray();

		assertEquals("12/03/2024 15:44:32 || 214.2", lineas[0]);
		assertEquals("12/03/2024 15:50:17 || 220.5", lineas[1]);
		assertEquals("18/03/2024 20:19:58 || 231.0", lineas[2]);

	}
}
