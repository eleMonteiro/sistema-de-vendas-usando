package testes.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.Cliente;

class TesteEntidadeCliente {

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
