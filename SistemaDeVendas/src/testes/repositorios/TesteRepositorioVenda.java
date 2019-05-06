package testes.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioVenda;

class TesteRepositorioVenda {

	@Test
	void TesteRetornoDoGetInstance() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();

		assertNotNull(repositorioVenda);
	}

	@Test
	void TesteAdicionarVendaNoRepositorio() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");

		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);
		double precoTotal = 25;

		Venda venda = new Venda(data, cliente.getId(), precoTotal, itemVenda);

		int quantidadeEsperada = repositorioVenda.getListVenda().size();
		repositorioVenda.adicionar(venda);
		int quantidadeAtual = repositorioVenda.getListVenda().size();

		assertEquals(quantidadeEsperada + 1, quantidadeAtual);
	}

	@Test
	void TesteGetVendaNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();

		Date data = new Date();
		Cliente cliente = new Cliente("Diana");

		List<ItemVenda> itemVenda = new ArrayList<>();
		Produto produto = new Produto("Caderno", 25);
		ItemVenda item = new ItemVenda(produto.getId(), 1);
		itemVenda.add(item);
		double precoTotal = 25;

		Venda vendaEsperada = new Venda(data, cliente.getId(), precoTotal, itemVenda);
		repositorioVenda.adicionar(vendaEsperada);
		Venda vendaAtual = repositorioVenda.get(vendaEsperada.getId());

		assertEquals(vendaEsperada, vendaAtual);
	}

}
