package testes.controladores;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import controladores.ControladorCliente;
import entidades.Cliente;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;

class TesteControladorCliente {

	@Test
	void testeCriarClienteComNomeVazio() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.criarCliente("");
		}, () -> "O nome do cliente não pode ser vazio");
	}

	@Test
	void testeCriarClienteComNomeContendoNumeros() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.criarCliente("John Doe 10");
		}, () -> "O nome do cliente não pode conter números");
	}

	@Test
	void testeCriarClienteComNomeContendoCaracteresEspeciais() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.criarCliente("@John_Doe");
		}, () -> "O nome do cliente não pode conter caracteres especiais");
	}

	@Test
	void testeCriarClienteCorretamente() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		int qtdClientesAntes = controladorCliente.getClienteList().size();
		controladorCliente.criarCliente("Luís D'Eça");
		int qtdClientesDepois = controladorCliente.getClienteList().size();

		assertEquals(qtdClientesAntes + 1, qtdClientesDepois);
	}

	@Test
	void testeGetClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertNull(controladorCliente.getCliente(cliente.getId()));
	}

	@Test
	void testeGetClienteExistente() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertNotNull(controladorCliente.getCliente(idCliente));
	}

	@Test
	void testeGetClienteList() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertNotNull(controladorCliente.getClienteList());
	}

	@Test
	void testeEditarClienteComIDInvalida() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.editarCliente(0, "Jane Doe");
		}, () -> "A ID do cliente a ser editado tem que ser >= 1");
	}

	@Test
	void testeEditarClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorCliente.editarCliente(cliente.getId(), "Jane Doe");
		}, () -> "O cliente a ser editado não existe");
	}

	@Test
	void testeEditarClienteComNomeVazio() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.editarCliente(idCliente, "");
		}, () -> "O nome do cliente a ser editado não pode ser vazio");
	}

	@Test
	void testeEditarClienteComNomeContendoNumeros() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.editarCliente(idCliente, "Jane Doe 10");
		}, () -> "O nome do cliente a ser editado não pode conter números");
	}

	@Test
	void testeEditarClienteComNomeContendoCaracteresEspeciais() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.editarCliente(idCliente, "@Jane_Doe");
		}, () -> "O nome do cliente a ser editado não pode conter caracteres especiais");
	}

	@Test
	void testeEditarClienteCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		String novoNomeDoCliente = "Jane Doe";
		controladorCliente.editarCliente(idCliente, novoNomeDoCliente);
		Cliente cliente = controladorCliente.getCliente(idCliente);

		assertEquals(novoNomeDoCliente, cliente.getNome());
	}

	@Test
	void testeRemoverClienteComIDInvalida() {
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.removerCliente(0);
		}, () -> "A ID do cliente a ser removido tem que ser >= 1");
	}

	@Test
	void testeRemoverClienteInexistente() {
		Cliente cliente = new Cliente("John Doe");
		ControladorCliente controladorCliente = new ControladorCliente();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorCliente.removerCliente(cliente.getId());
		}, () -> "O cliente a ser removido não existe");
	}

	@Test
	void testeRemoverClienteCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		int qtdClientesAntes = controladorCliente.getClienteList().size();
		controladorCliente.removerCliente(idCliente);
		int qtdClientesDepois = controladorCliente.getClienteList().size();

		assertEquals(qtdClientesAntes - 1, qtdClientesDepois);
	}

	@Test
	void testeProcurarClientesComFiltroVazio() {
		ControladorCliente controladorCliente = new ControladorCliente();
		String filtro = "";

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.procurarClientes(filtro);
		}, () -> "O filtro da pesquisa não pode ser vazio");
	}

	@Test
	void testeProcurarClientesComFiltroContendoNumeros() {
		ControladorCliente controladorCliente = new ControladorCliente();
		String filtro = "John Doe 10";

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.procurarClientes(filtro);
		}, () -> "O filtro da pesquisa não pode conter números ou caracteres especiais");
	}

	@Test
	void testeProcurarClientesComFiltroContendoCaracteresEspeciais() {
		ControladorCliente controladorCliente = new ControladorCliente();
		String filtro = "@John_Doe";

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorCliente.procurarClientes(filtro);
		}, () -> "O filtro da pesquisa não pode conter números ou caracteres especiais");
	}

	@Test
	void testeProcurarClientesNaoRetornandoCliente() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		Cliente cliente = controladorCliente.getCliente(idCliente);
		String filtro = "Dow";
		List<Cliente> clientes = controladorCliente.procurarClientes(filtro);

		assertNotNull(clientes);
		assertFalse(clientes.contains(cliente));
	}

	@Test
	void testeProcurarClientesRetornandoCliente() throws CampoComValorInvalidoException {
		ControladorCliente controladorCliente = new ControladorCliente();
		long idCliente = controladorCliente.criarCliente("John Doe");
		Cliente cliente = controladorCliente.getCliente(idCliente);
		String filtro = "Doe";
		List<Cliente> clientes = controladorCliente.procurarClientes(filtro);

		assertNotNull(clientes);
		assertTrue(clientes.contains(cliente));
	}

}
