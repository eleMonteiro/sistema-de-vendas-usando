package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import controladores.ControladorCliente;
import entidades.Cliente;
import excecoes.ClienteNaoExisteException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.IDInvalidaException;
import excecoes.StringVaziaException;

class TesteControladorCliente {

//	Criar Cliente

	@Test
	void testeCriarClienteComNomeVazio() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(StringVaziaException.class, () -> {
			controladorCliente.criarCliente("");
		});
	}

	@Test
	void testeCriarClienteComNomeContendoNumeros() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorCliente.criarCliente("John Doe 10");
		});
	}

	@Test
	void testeCriarClienteComNomeContendoCaracteresEspeciais() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorCliente.criarCliente("@John_Doe");
		});
	}

	@Test
	void testeCriarClienteCorretamente() throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		int qtdClientesAntes = controladorCliente.getClienteList().size();
		controladorCliente.criarCliente("Luís D'Eça");
		int qtdClientesDepois = controladorCliente.getClienteList().size();

		assertEquals(qtdClientesAntes + 1, qtdClientesDepois);
	}

//	Get Cliente

	@Test
	void testeGetClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertNull(controladorCliente.getCliente(cliente.getId()));
	}

	@Test
	void testeGetClienteExistente() throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertNotNull(controladorCliente.getCliente(idCliente));
	}

//	Get Cliente List

	@Test
	void testeGetClienteList() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertNotNull(controladorCliente.getClienteList());
	}

//	Editar Cliente

	@Test
	void testeEditarClienteComIDInvalida() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(IDInvalidaException.class, () -> {
			controladorCliente.editarCliente(0, "Jane Doe");
		});
	}

	@Test
	void testeEditarClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(ClienteNaoExisteException.class, () -> {
			controladorCliente.editarCliente(cliente.getId(), "Jane Doe");
		});
	}

	@Test
	void testeEditarClienteComNomeVazio() throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(StringVaziaException.class, () -> {
			controladorCliente.editarCliente(idCliente, "");
		});
	}

	@Test
	void testeEditarClienteComNomeContendoNumeros() throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorCliente.editarCliente(idCliente, "Jane Doe 10");
		});
	}

	@Test
	void testeEditarClienteComNomeContendoCaracteresEspeciais()
			throws StringVaziaException, FormatoDeStringInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorCliente.editarCliente(idCliente, "@Jane_Doe");
		});
	}

	@Test
	void testeEditarClienteCorretamente() throws StringVaziaException, FormatoDeStringInvalidoException,
			IDInvalidaException, ClienteNaoExisteException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		String novoNomeDoCliente = "Jane Doe";
		controladorCliente.editarCliente(idCliente, novoNomeDoCliente);
		Cliente cliente = controladorCliente.getCliente(idCliente);

		assertEquals(novoNomeDoCliente, cliente.getNome());
	}

//	Remover Cliente

	@Test
	void testeRemoverClienteComIDInvalida() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(IDInvalidaException.class, () -> {
			controladorCliente.removerCliente(0);
		});
	}

	@Test
	void testeRemoverClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(ClienteNaoExisteException.class, () -> {
			controladorCliente.removerCliente(cliente.getId());
		});
	}

	@Test
	void testeRemoverClienteCorretamente() throws StringVaziaException, FormatoDeStringInvalidoException,
			IDInvalidaException, ClienteNaoExisteException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		int qtdClientesAntes = controladorCliente.getClienteList().size();
		controladorCliente.removerCliente(idCliente);
		int qtdClientesDepois = controladorCliente.getClienteList().size();

		assertEquals(qtdClientesAntes - 1, qtdClientesDepois);
	}

}
