package testes.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioItensEstoque;

class TesteRepositorioItensEstoque {

	@Test
	void testeGetInstance() {
		assertNotNull(RepositorioItensEstoque.getInstance());
	}

	@Test
	void testeGetItemEstoqueList() {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		assertNotNull(repositorioItensEstoque.getItemEstoqueList());
	}

	@Test
	void testeAdicionarItemEstoqueNulo() {
		ItemEstoque itemEstoque = null;
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		assertThrows(NullPointerException.class, () -> {
			repositorioItensEstoque.adicionar(itemEstoque);
		}, () -> "O item de estoque a ser adicionado não pode ser nulo");
	}

	@Test
	void testeAdicionarItemCorretamente() throws ItemNaoEstaNoRepositorioException {
		ItemEstoque itemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		assertTrue(repositorioItensEstoque.adicionar(itemEstoque));
	}

	@Test
	void testeGetItemEstoqueQueNaoEstaNoRepositorio() {
		ItemEstoque itemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		assertNull(repositorioItensEstoque.getItemEstoque(itemEstoque.getId()));
	}

	@Test
	void testeGetItemEstoqueQueEstaNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		ItemEstoque itemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();
		repositorioItensEstoque.adicionar(itemEstoque);

		assertNotNull(repositorioItensEstoque.getItemEstoque(itemEstoque.getId()));
	}

	@Test
	void testeRemoverClienteQueNaoEstaNoRepositorio() {
		ItemEstoque itemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			repositorioItensEstoque.remover(itemEstoque.getId());
		}, () -> "O item de estoque a ser removido não está no repositório");
	}

	@Test
	void testeRemoverClienteCorretamente() throws ItemNaoEstaNoRepositorioException {
		ItemEstoque itemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();
		repositorioItensEstoque.adicionar(itemEstoque);

		assertTrue(repositorioItensEstoque.remover(itemEstoque.getId()));
	}

}
