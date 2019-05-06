package testes.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;

class TesteEntidadeVenda {

	@Test
	void TesteGetIdVenda() {
		Venda vendaEsperada = new Venda();
		Venda vendaAtual = new Venda();

		long idEsperado = vendaEsperada.getId();
		long idAtual = vendaAtual.getId();

		assertEquals(idEsperado + 1, idAtual);
	}

	@Test
	void TesteSetIdVenda() {
		Venda venda = new Venda(1);
		long novoId = 2;
		venda.setId(novoId);

		assertEquals(novoId, venda.getId());
	}

	@Test
	void TesteGetDataVenda() {
		Date data = new Date();
		Venda venda = new Venda(data);

		assertEquals(data, venda.getData());
	}

	@Test
	void TesteSetDataVenda() {
		Venda venda = new Venda(new Date());
		Date novaData = new Date();
		venda.setData(novaData);

		assertEquals(novaData, venda.getData());
	}

	@Test
	void TesteGetClienteVenda() {
		Cliente cliente = new Cliente("Rafa");
		Venda venda = new Venda(cliente.getId());

		assertEquals(cliente, venda.getIdCliente());
	}

	@Test
	void TesteSetClienteVenda() {
		Cliente cliente = new Cliente("Rafa");
		Venda venda = new Venda(cliente.getId());
		Cliente novoCliente = new Cliente("Tiago");
		venda.setCliente(novoCliente.getId());

		assertEquals(novoCliente, venda.getIdCliente());
	}

	@Test
	void TesteGetPrecoTotalVenda() {
		double precoTotal = 3.5;
		Venda venda = new Venda(precoTotal);

		assertEquals(precoTotal, venda.getPrecoTotal());
	}

	@Test
	void TesteGetListItensVenda() {
		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto1 = new Produto("Celular");
		Produto produto2 = new Produto("Calculadora");
		Produto produto3 = new Produto("Caderno");
		itemVenda.add(new ItemVenda(produto1.getId(), 9));
		itemVenda.add(new ItemVenda(produto2.getId(), 5));
		itemVenda.add(new ItemVenda(produto3.getId(), 3));

		Venda venda = new Venda(itemVenda);
		assertEquals(itemVenda, venda.getListItemVenda());
	}

	@Test
	void TesteSetListItensVenda() {
		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto1 = new Produto("Celular");
		Produto produto2 = new Produto("Calculadora");
		Produto produto3 = new Produto("Caderno");
		itemVenda.add(new ItemVenda(produto1.getId(), 9));
		itemVenda.add(new ItemVenda(produto2.getId(), 5));
		itemVenda.add(new ItemVenda(produto3.getId(), 3));

		Venda venda = new Venda(itemVenda);

		List<ItemVenda> novoItemVenda = new ArrayList<>();
		Produto produto1Novo = new Produto("Tablet");
		Produto produto2Novo = new Produto("Iphone");
		Produto produto3Novo = new Produto("Smartphone");
		itemVenda.add(new ItemVenda(produto1Novo.getId(), 9));
		itemVenda.add(new ItemVenda(produto2Novo.getId(), 5));
		itemVenda.add(new ItemVenda(produto3Novo.getId(), 3));
		

		venda.setListItemVenda(novoItemVenda);
		assertEquals(novoItemVenda, venda.getListItemVenda());
	}
}
