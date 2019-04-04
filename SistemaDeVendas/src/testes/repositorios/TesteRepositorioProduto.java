package testes.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entidades.Produto;
import repositorios.RepositorioProdutos;

class TesteRepositorioProduto {

	@Test
	void TesteRetornoDoGetInstance() {
		Assert.assertNotNull(RepositorioProdutos.getInstance());
	}
	
	@Test
	void TesteAdicionarProdutoAoRepositorio() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		Produto produto = new Produto("Café", 2.5f);
		
		int quantidadeEsperada = repositorioProdutos.getListProdutos().size();
		repositorioProdutos.adicionar(produto);
		
		int quantidadeAtual = repositorioProdutos.getListProdutos().size();
		assertEquals(quantidadeEsperada+1, quantidadeAtual);
	}
	
	@Test
	void TesteGetProdutoNoRepositorio() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		Produto produtoEsperado = new Produto("Café", 2.5f);
		repositorioProdutos.adicionar(produtoEsperado);
		Produto produtoAtual = repositorioProdutos.get(produtoEsperado.getId());
		assertEquals(produtoEsperado, produtoAtual );
	}
	
	@Test
	void TesteRemoveProdutoDoRepositorio() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		Produto produto = new Produto("Café", 2.5f);
		repositorioProdutos.adicionar(produto);
		
		int quantidadeEsperada = repositorioProdutos.getListProdutos().size();
		repositorioProdutos.remove(produto.getId());
		int quantidadeAtual = repositorioProdutos.getListProdutos().size();
		assertEquals(quantidadeEsperada-1, quantidadeAtual);
	}
	
	@Test
	void TesteEditarProdutoAoRepositorio() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		Produto produto = new Produto("Café", 2.5f);
		repositorioProdutos.adicionar(produto);
		boolean valorEsperado = repositorioProdutos.editar(produto.getId(), "Açucar", 4.5f);
		
		assertTrue(valorEsperado);
	}
}