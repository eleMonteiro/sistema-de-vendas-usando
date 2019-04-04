package testes.controladores;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import entidades.Venda;
import excecoes.CampoComValorInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.ItemNaoEstaNoRepositorioException;

class TesteControladorVenda {

	@Test
	void TesteQuantidadeDeElementosListVendas() {
		ControladorVenda controladorVenda = new ControladorVenda();
		int quantidadeEsperada = 2;
		int quantidadeAtual = controladorVenda.getListVendas().size();
		
		assertEquals(quantidadeEsperada, quantidadeAtual);
	}
	
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
		}, () -> "A data da venda a ser adicionada não pode ser nula");
	}

	@Test
	void TesteCriarVendaComDataInvalida() throws CampoComValorInvalidoException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 3);
		Date dataAtual = calendar.getTime();
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
		}, () -> "O cliente da venda a ser adicionada não pode ser nulo");
	}

	@Test
	void TesteCriarVendaComVatorTotalNegativo() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = -3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		}, () -> "O valor da venda a ser adicionada não pode ser negativo");
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
		}, () -> "A lista de itens da venda a ser adicionada não pode ser nula");
	}

	@Test
	void TesteCriarVendaCorreta() throws DataInvalidaException, CampoComValorInvalidoException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);

		Date data =  new Date();
		
		int quantidadeEsperada = controladorVenda.getListVendas().size();	
		
		controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		
		int quantidadeAtual = controladorVenda.getListVendas().size();
		assertEquals(quantidadeEsperada+1, quantidadeAtual);
	}
	
	@Test
	void TesteGetVendaQueNaoExiste() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		Date data =  new Date();		
		
		Venda venda = new Venda(data, cliente, precoTotal, itemVenda);
		long idVenda = venda.getId();
		
		assertThrows(ItemNaoEstaNoRepositorioException.class, ()-> {
			controladorVenda.getVenda(idVenda);
		}, () -> "A venda com o identificador fornecido n�o existe!");

	}
	
	@Test
	void TesteGetVendaQueExiste() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		Date data =  new Date();		
				
		long vendaEsperada = controladorVenda.criarVenda(data, cliente, precoTotal, itemVenda);
		Venda vendaAtual = controladorVenda.getVenda(vendaEsperada);
		
		assertEquals(vendaEsperada, vendaAtual.getId());	
	}
}