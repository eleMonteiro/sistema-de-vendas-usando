package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.ItemVenda;
import entidades.Produto;

class TesteEntidadeItemVenda {

	@Test
	void TesteGetProdutoItemVenda() {
		Produto produto = new Produto("Calculadora");
		ItemVenda itemVenda = new ItemVenda(produto);
		
		assertEquals(produto, itemVenda.getProduto());
	}
	
	@Test
	void TesteSetProdutoItemVenda() {
		Produto produto = new Produto("Calculadora");
		ItemVenda itemVenda = new ItemVenda(produto);
		Produto novoProduto = new Produto("Celular");
		itemVenda.setProduto(novoProduto);
		
		assertEquals(novoProduto, itemVenda.getProduto());
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
