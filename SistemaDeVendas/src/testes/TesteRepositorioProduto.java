package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entidades.Produto;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioProdutos;

class TesteRepositorioProduto {

	@Test
	void TesteRetornoDoGetInstance() {
		Assert.assertNotNull(RepositorioProdutos.getInstance());
	}
	
	@Test
	void TesteEstruturaDeDadosInicializada() {
		Assert.assertNotNull(RepositorioProdutos.getInstance().getListProdutos());
	}
	
	@Test
	void TesteAdicionarProdutoNuloAoRepositorio() {
		Produto produto = null;
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		assertThrows(NullPointerException.class, () -> {
			repositorioProdutos.adicionar(produto);
		}, () -> "O produto a ser adicionado não pode ser nulo!");
	}
	
	@Test
	void TesteAdicionarClienteAoRepositorio() {
		Produto produto = new Produto("Celular", 500);
		Assert.assertTrue(RepositorioProdutos.getInstance().adicionar( produto));
	}
	

	@Test
	void testeGetParaClienteQueNaoEstaNoRepositorio() {
		Produto produto = new Produto("Tablet", 500);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		assertNull(repositorioProdutos.get(produto.getCodigo()));
	}

	@Test
	void TesteGetProdutoNoRepositorio() {
		RepositorioProdutos.getInstance().adicionar(new Produto("Café", 20));
		Assert.assertNull(RepositorioProdutos.getInstance().get(1));
	}

	@Test
	void testeRemoverProdutoQueNaoEstaNoRepositorio() {
		Produto produto = new Produto("Tablet", 500);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			repositorioProdutos.remove(produto.getCodigo());
		});
	}
	
	@Test
	void TesteRemoverProdutoNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto("Tablet", 500);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		int qtdProdutosAntes = repositorioProdutos.getListProdutos().size();
		repositorioProdutos.remove(produto.getCodigo());
		int qtdprodutosDepois = repositorioProdutos.getListProdutos().size();

		assertEquals(qtdProdutosAntes - 1, qtdprodutosDepois);
	}
	
	@Test
	void TesteEditarProdutoQueNaoExisteNoRepositorio() {
		assertThrows(ItemNaoEstaNoRepositorioException.class, () ->{
			RepositorioProdutos.getInstance().editar(30, "Cabo", 30);		
		});
	}
	
	@Test
	void TesteEditarProduto() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto(9, "Cabo USB", 30);
		RepositorioProdutos.getInstance().adicionar(produto);
		boolean retorno = RepositorioProdutos.getInstance().editar(9, "Cabo", 30);
		assertTrue(retorno);
	}
	
}