package testes.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.Cliente;

class TesteEntidadeCliente {

	@Test
	void testeGetId() {
		Cliente cliente1 = new Cliente("John Doe");
		Cliente cliente2 = new Cliente("Jane Doe");

		assertEquals(cliente1.getId() + 1, cliente2.getId());
	}

	@Test
	void testeGetNomeCliente() {
		String nome = "John Doe";
		Cliente cliente = new Cliente(nome);

		assertEquals(nome, cliente.getNome());
	}

	@Test
	void testeSetNomeCliente() {
		Cliente cliente = new Cliente("John Doe");
		String novoNomeCliente = "Jane Doe";
		cliente.setNome(novoNomeCliente);

		assertEquals(novoNomeCliente, cliente.getNome());
	}

}
