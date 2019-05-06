package testes.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.ItemVenda;
import entidades.Produto;

class TesteEntidadeItemVenda {

	@Test
	void TesteGetProdutoItemVenda() {
		Produto produto = new Produto("Calculadora");
		ItemVenda itemVenda = new ItemVenda(produto.getId());

		assertEquals(produto.getId(), itemVenda.getIdProduto());
	}

	@Test
	void TesteSetProdutoItemVenda() {
		Produto produto = new Produto("Calculadora");
		ItemVenda itemVenda = new ItemVenda(produto.getId());
		Produto novoProduto = new Produto("Celular");
		itemVenda.setIdProduto(novoProduto.getId());

		assertEquals(novoProduto, itemVenda.getIdProduto());
	}

	@Test
	void TesteGetQuantidadeItemVenda() {
		int quantidade = 9;
		ItemVenda itemVenda = new ItemVenda(quantidade);

		assertEquals(quantidade, itemVenda.getQuantidade());
	}

	@Test
	void TesteSetQuantidadeItemVenda() {
		ItemVenda itemVenda = new ItemVenda(9);
		int novaQuantidade = 30;
		itemVenda.setQuantidade(novaQuantidade);

		assertEquals(novaQuantidade, itemVenda.getQuantidade());
	}
}
