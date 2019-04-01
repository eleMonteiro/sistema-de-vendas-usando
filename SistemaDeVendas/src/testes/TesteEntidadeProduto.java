package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entidades.Produto;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
class TesteEntidadeProduto {

	@Test
	void TesteRetornoConstrutorProduto() {
		Produto produtoAtual = new Produto();
		Assert.assertNotNull(produtoAtual);
	}
	
	@Test
	void TesteGetPrecoInvalido() {
		float preco = (float) 3.4028235456E38;
		Produto produto = new Produto(preco);
		
		assertEquals(preco, produto.getPreco());
	}
	
	@Test
	void TesteGetPrecoProduto() {
		float preco = (float) 3.19;
		
		Produto produto = new Produto(preco);
		assertEquals(preco, produto.getPreco());
	}
	
	@Test
	void TesteSetNomeProduto() {
		Produto produto = new Produto("Calculadora");
		String novoNome = "Calc";
		produto.setNome(novoNome);
	
		assertEquals(novoNome, produto.getNome());
	}
	
	@Test
	void TesteSetPrecoProduto() {
		Produto produto = new Produto(50);
		float novopreco = 60;
		produto.setPreco(novopreco);
	
		assertEquals(novopreco, produto.getPreco());
	}
}