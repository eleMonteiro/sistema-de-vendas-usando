package testes;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
		Produto produto = RepositorioProdutos.getInstance().get(1);
		Assert.assertNotNull(RepositorioProdutos.getInstance().remove(produto));
	}
	
	@Test
	void TesteEditarProduto() {
		Produto produto = new Produto(9, "Cabo USB", 30);
		RepositorioProdutos.getInstance().adicionar(produto);
		boolean retorno = RepositorioProdutos.getInstance().editar(9, "Cabo", 30);
		assertTrue(retorno);
	}
	
}
