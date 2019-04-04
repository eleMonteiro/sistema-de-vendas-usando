package testes.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import entidades.Cliente;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioClientes;

class TesteRepositorioClientes {

	@Test
	void testeGetInstance() {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertNotNull(repositorioClientes);
	}

	@Test
	void testeGetClienteList() {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		assertNotNull(repositorioClientes.getClienteList());
	}

//	@Test
//	void testeAdicionarClienteNulo() {
//		Cliente cliente = null;
//		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
//
//		assertThrows(NullPointerException.class, () -> {
//			repositorioClientes.adicionar(cliente);
//		}, () -> "O cliente a ser adicionado não pode ser nulo");
//	}

	@Test
	void testeAdicionarClienteCorretamente() {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		int qtdClientesAntes = repositorioClientes.getClienteList().size();
		repositorioClientes.adicionar(cliente);
		int qtdClientesDepois = repositorioClientes.getClienteList().size();

		assertEquals(qtdClientesAntes + 1, qtdClientesDepois);
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

//	@Test
//	void testeRemoverClienteQueNaoEstaNoRepositorio() {
//		Cliente cliente = new Cliente("John Doe");
//		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
//
//		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
//			repositorioClientes.remover(cliente.getId());
//		}, () -> "O cliente a ser removido não existe");
//	}

	@Test
	void testeRemoverClienteCorretamente() throws ItemNaoEstaNoRepositorioException {
		Cliente cliente = new Cliente("John Doe");
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);
		int qtdClientesAntes = repositorioClientes.getClienteList().size();
		repositorioClientes.remover(cliente.getId());
		int qtdClientesDepois = repositorioClientes.getClienteList().size();

		assertEquals(qtdClientesAntes - 1, qtdClientesDepois);
	}

}
