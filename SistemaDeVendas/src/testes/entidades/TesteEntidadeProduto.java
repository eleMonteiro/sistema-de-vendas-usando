package testes.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entidades.Produto;

class TesteEntidadeProduto {

	@Test
	void TesteGetId() {
		Produto produtoEsperado = new Produto("Café", 3.5f);
		Produto produtoAtual = new Produto("Açucar", 4.5f);

		long idEsperado = produtoEsperado.getId();
		long idAtual = produtoAtual.getId();

		assertEquals(idEsperado + 1, idAtual);
	}

	@Test
	void TesteGetPrecoInvalido() {
		float precoEsperado = (float) 3.4028235456E38;
		Produto produto = new Produto(precoEsperado);
		float precoAtual = produto.getPreco();
		assertEquals(precoEsperado, precoAtual);
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