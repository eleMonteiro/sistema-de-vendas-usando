package testes;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entidades.Produto;
import repositorios.RepositorioProdutos;

class TesteRepositorioProduto {

	@Test
	void TesteRetornoDoConstrutor() {
		Assert.assertNotNull(RepositorioProdutos.getInstance());
	}
	
	@Test
	void TesteEstruturaDeDadosInicializada() {
		Assert.assertNotNull(RepositorioProdutos.getInstance().getListProdutos());
	}
	
	@Test
	void TesteAdicionarClienteAoRepositorio() {
		Assert.assertTrue(RepositorioProdutos.getInstance().adicionar(new Produto()));
	}
	
	@Test
	void TesteEncontrarProdutoNoRepositorio() {
		RepositorioProdutos.getInstance().adicionar(new Produto("Café", 20));
		Assert.assertNotNull(RepositorioProdutos.getInstance().get(1));
	}
	
	@Test
	void TesteRemoverProdutoNoRepositorio() {
		Assert.assertNotNull(RepositorioProdutos.getInstance().remove(1));
	}
	
}
