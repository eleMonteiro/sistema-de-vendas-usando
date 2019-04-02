package testes.entidades;

import static org.junit.jupiter.api.Assertions.*;

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
		long id = 1;
		Venda venda = new Venda(id);
		
		assertEquals(id, venda.getId());
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
		Venda venda = new Venda(cliente);
		
		assertEquals(cliente, venda.getCliente());
	}
	
	@Test
	void TesteSetClienteVenda() {
		Cliente cliente = new Cliente("Rafa");
		Venda venda = new Venda(cliente);
		Cliente novoCliente = new Cliente("Tiago");
		venda.setCliente(novoCliente);
		
		assertEquals(novoCliente, venda.getCliente());
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
		itemVenda.add(new ItemVenda(new Produto("Celular"), 9));
		itemVenda.add(new ItemVenda(new Produto("Calculadora"), 5));
		itemVenda.add(new ItemVenda(new Produto("Caderno"), 3));
		
		Venda venda = new Venda(itemVenda);
		assertEquals(itemVenda, venda.getListItemVenda());
	}
	
	@Test
	void TesteSetListItensVenda() {
		List<ItemVenda> itemVenda = new ArrayList<>();
		itemVenda.add(new ItemVenda(new Produto("Celular"), 9));
		itemVenda.add(new ItemVenda(new Produto("Calculadora"), 5));
		itemVenda.add(new ItemVenda(new Produto("Caderno"), 3));
		
		Venda venda = new Venda(itemVenda);
		
		List<ItemVenda> novoItemVenda = new ArrayList<>();
		novoItemVenda.add(new ItemVenda(new Produto("Tablet"), 9));
		novoItemVenda.add(new ItemVenda(new Produto("Iphone"), 5));
		novoItemVenda.add(new ItemVenda(new Produto("Smartphone"), 3));
		
		venda.setListItemVenda(novoItemVenda);
		assertEquals(novoItemVenda, venda.getListItemVenda());
	}
}
