package testes.controladores;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import controladores.ControladorItemEstoque;
import controladores.ControladorProduto;
import controladores.ControladorVenda;
import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;
import excecoes.CampoComValorInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.QuantidadeDoElementoInvalidaException;

class TesteControladorVenda {

	@Test
	void TesteQuantidadeDeElementosListVendas() {
		ControladorVenda controladorVenda = new ControladorVenda();
		int quantidadeEsperada = controladorVenda.getListVendas().size();
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
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);

		assertThrows(NullPointerException.class, () -> {
			controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
		}, () -> "A data da venda a ser adicionada não pode ser nula");
	}

	@Test
	void TesteCriarVendaComDataInvalida() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException,
			QuantidadeDoElementoInvalidaException {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 3);
		Date dataAtual = calendar.getTime();
		Date dataEsperada = new Date();

		controladorVenda.criarVenda(dataEsperada, cliente.getId(), precoTotal, itemVenda);

		assertNotEquals(dataEsperada, dataAtual);
	}

	@Test
	void TesteCriarVendaComClienteVazio() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = null;
		double precoTotal = 3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);

		assertThrows(NullPointerException.class, () -> {
			controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
		}, () -> "O cliente da venda a ser adicionada não pode ser nulo");
	}

	@Test
	void TesteCriarVendaComVatorTotalNegativo() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Date data = new Date();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = -3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
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
			controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
		}, () -> "A lista de itens da venda a ser adicionada não pode ser nula");
	}

	@Test
	void TesteCriarVendaComQuantidadeDosItensInvalida()
			throws CampoComValorInvalidoException, QuantidadeDoElementoInvalidaException,
			ItemNaoEstaNoRepositorioException, NullPointerException, ItemJaEstaNoRepositorio {
		// Garantir que item estará no estoque
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Caderno", 25);
		Produto produto = controladorProduto.getProduto(idProduto);
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		controladorItemEstoque.criarItemEstoque(idProduto, 20);

		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(produto.getId(), 11);
		itemVenda.add(item);

		Date data = new Date();

		assertThrows(QuantidadeDoElementoInvalidaException.class, () -> {
			controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
		}, () -> "quantidade do produto é insuficiente");

	}

	@Test
	void TesteCriarVendaCorreta()
			throws DataInvalidaException, CampoComValorInvalidoException, QuantidadeDoElementoInvalidaException,
			ItemNaoEstaNoRepositorioException, NullPointerException, ItemJaEstaNoRepositorio {
		// Garantir que item estará no estoque
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Caderno", 25);
		Produto produto = controladorProduto.getProduto(idProduto);
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		controladorItemEstoque.criarItemEstoque(idProduto, 5);

		ControladorVenda controladorVenda = new ControladorVenda();
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(produto.getId(), 7);
		itemVenda.add(item);

		int quantidadeEsperada = controladorVenda.getListVendas().size();
		int quantidadeAtual = controladorVenda.getListVendas().size();

		assertEquals(quantidadeEsperada + 1, quantidadeAtual);
	}

	@Test
	void TesteGetVendaQueNaoExiste() {
		ControladorVenda controladorVenda = new ControladorVenda();
		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;

		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);
		Date data = new Date();

		Venda venda = new Venda(data, cliente.getId(), precoTotal, itemVenda);
		long idVenda = venda.getId();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorVenda.getVenda(idVenda);
		}, () -> "A venda com o identificador fornecido n�o existe!");

	}

	@Test
	void TesteGetVendaQueExiste() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException,
			QuantidadeDoElementoInvalidaException, NullPointerException, ItemJaEstaNoRepositorio {
		// Garantir que item estará no estoque
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Lápis", 1.5f);

		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		controladorItemEstoque.criarItemEstoque(idProduto, 5);

		ControladorVenda controladorVenda = new ControladorVenda();

		Cliente cliente = new Cliente("Rafael");
		double precoTotal = 3.5;

		Produto produto = controladorProduto.getProduto(idProduto);

		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);
		Date data = new Date();

		long vendaEsperada = controladorVenda.criarVenda(data, cliente.getId(), precoTotal, itemVenda);
		Venda vendaAtual = controladorVenda.getVenda(vendaEsperada);

		assertEquals(vendaEsperada, vendaAtual.getId());
	}
}