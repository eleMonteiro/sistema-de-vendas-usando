package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controladores.ControladorCliente;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.StringVaziaException;
import repositorios.RepositorioClientes;

class TesteControladorCliente {

	@Test
	void testeCriarClienteComNomeVazio() {
		assertThrows(StringVaziaException.class, () -> {
			ControladorCliente controladorCliente = new ControladorCliente();
			controladorCliente.criarCliente("");
		});
	}

	@Test
	void testeCriarClienteComNomeContendoNumeros() {
		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			ControladorCliente controladorCliente = new ControladorCliente();
			controladorCliente.criarCliente("John Doe 10");
		});
	}

	@Test
	void testeCriarClienteComNomeContendoCaracteresEspeciais() {
		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			ControladorCliente controladorCliente = new ControladorCliente();
			controladorCliente.criarCliente("@John_Doe");
		});
	}

	@Test
	void testCriarClienteCorretamente() throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("Luís D'Eça");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		assertNotNull(repositorioClientes.get(idCliente));
	}

}
