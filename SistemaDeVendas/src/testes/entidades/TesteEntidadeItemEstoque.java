package testes.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entidades.ItemEstoque;
import entidades.Produto;

class TesteEntidadeItemEstoque {

	@Test
	void testeGetId() {
		ItemEstoque itemEstoque1 = new ItemEstoque(new Produto("Arroz", 2), 50);
		ItemEstoque itemEstoque2 = new ItemEstoque(new Produto("Feijão", 3), 25);

		assertEquals(itemEstoque1.getId() + 1, itemEstoque2.getId());
	}

	@Test
	void testeGetProduto() {
		Produto produto = new Produto("Arroz", 2);
		int quantidade = 50;
		ItemEstoque itemEstoque = new ItemEstoque(produto, quantidade);

		assertEquals(produto, itemEstoque.getProduto());
	}

	@Test
	void testeSetProduto() {
		Produto produtoAntigo = new Produto("Arroz", 2);
		Produto produtoNovo = new Produto("Feijão", 3);
		int quantidade = 50;
		ItemEstoque itemEstoque = new ItemEstoque(produtoAntigo, quantidade);
		itemEstoque.setProduto(produtoNovo);

		assertEquals(produtoNovo, itemEstoque.getProduto());
	}

	@Test
	void testeGetQuantidade() {
		Produto produto = new Produto("Arroz", 2);
		int quantidade = 50;
		ItemEstoque itemEstoque = new ItemEstoque(produto, quantidade);

		assertEquals(quantidade, itemEstoque.getQuantidade());
	}

	@Test
	void testeSetQuantidade() {
		Produto produto = new Produto("Arroz", 2);
		int quantidadeAntiga = 50;
		int quantidadeNova = 100;
		ItemEstoque itemEstoque = new ItemEstoque(produto, quantidadeAntiga);
		itemEstoque.setQuantidade(quantidadeNova);

		assertEquals(quantidadeNova, itemEstoque.getQuantidade());
	}

}
