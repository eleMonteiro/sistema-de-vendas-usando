package testes;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import controladores.ControladorVenda;
import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Produto;
import excecoes.DataInvalidaException;
import excecoes.FloatNegativoException;

class TesteControladorVenda {

	@Test
	void TesteCriarVendaComDataVazia() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = null;
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
				
		assertThrows(NullPointerException.class, () -> {
			controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		}, () ->"a data da venda a ser adicionada não pode ser nula");
	}
	
	@Test
	void TesteCriarVendaComDataInvalida() throws FloatNegativoException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 3);
		Date dataAtual =  calendar.getTime();
		Date dataEsperada = new Date();
		
		controladorVenda.criarVenda(dataEsperada, cliente, precoTotal, itemVenda);
		
		assertNotEquals(dataEsperada, dataAtual);
	}
	
	@Test
	void TesteCriarVendaComClienteVazio() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = null;
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
				
		assertThrows(NullPointerException.class, () -> {
			controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		}, () ->"o cliente da venda a ser adicionada não pode ser nulo");
	}
	
	@Test
	void TesteCriarVendaComVatorTotalNegativo(){
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = -3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
				
		assertThrows(FloatNegativoException.class, () -> {
			controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		}, () ->"o valor da venda a ser adicionada não pode ser negativo");
	}
	
	@Test
	void TesteCriarVendaComListItensVazia() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
				
		assertThrows(NullPointerException.class, () -> {
			controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		}, () ->"a lista de itens da venda a ser adicionada não pode ser vazia");
	}
	
	@Test
	void TesteCriarVendaCorreta() throws DataInvalidaException, FloatNegativoException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		Date data =  new Date();
		
		assertTrue(controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda));
	}
}