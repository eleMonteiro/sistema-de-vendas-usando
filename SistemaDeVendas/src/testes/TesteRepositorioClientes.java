package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import entidades.Cliente;
import excecoes.ClienteNaoExisteException;
import repositorios.RepositorioClientes;

class TesteRepositorioClientes {

	@Test
	void testeAdicionarCliente() {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		int qtdClientesAntes = repositorioClientes.getClienteList().size();
		repositorioClientes.adicionar(cliente);
		int qtdClientesDepois = repositorioClientes.getClienteList().size();

		assertEquals(qtdClientesAntes + 1, qtdClientesDepois);
	}

	@Test
	void testeGetClienteList() {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertNotNull(repositorioClientes.getClienteList());
	}

	@Test
	void testeGetParaClienteQueNaoEstaNoRepositorio() {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertNull(repositorioClientes.get(cliente.getId()));
	}

	@Test
	void testeGetParaClienteQueEstaNoRepositorio() {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);

		assertNotNull(repositorioClientes.get(cliente.getId()));
	}

	@Test
	void testeRemoverClienteCorretamente() throws ClienteNaoExisteException {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);

		int qtdClientesAntes = repositorioClientes.getClienteList().size();
		repositorioClientes.remover(cliente.getId());
		int qtdClientesDepois = repositorioClientes.getClienteList().size();

		assertEquals(qtdClientesAntes - 1, qtdClientesDepois);

	}

	@Test
	void testeRemoverClienteQueNaoEstaNoRepositorio() {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertThrows(ClienteNaoExisteException.class, () -> {
			repositorioClientes.remover(cliente.getId());
		});
	}

	@Test
	void testeGetInstance() {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertNotNull(repositorioClientes);
	}
}
