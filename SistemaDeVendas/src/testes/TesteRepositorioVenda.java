package testes;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void TesteInicializacaoDaEstrruturaDeDados() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();

		assertNotNull(repositorioVenda.getListVenda());
	}
	
	@Test
	void TesteAdicionarVendaVaziaNoRepositorio() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		Venda venda = null;
		
		assertThrows(NullPointerException.class, () ->{
			repositorioVenda.adicionar(venda);
		}, () -> "A venda a ser adicionado não pode ser nulo!");
		
	}
	
	@Test
	void TesteAdicionarVendaNoRepositorio() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		double precoTotal = 25;
		
		Venda venda = new Venda( data, cliente, precoTotal, itemVenda);
		
		assertTrue( repositorioVenda.adicionar(venda));
	}
	
	@Test
	void TesteGetVendaQueNaoEstaNoRepositorio() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		double precoTotal = 25;
		
		Venda venda = new Venda( data, cliente, precoTotal, itemVenda);
		
		assertThrows(ItemNaoEstaNoRepositorioException.class, () ->{
			repositorioVenda.get(venda.getId());
		});
	
	}
	
	@Test
	void TesteGetVendaNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		double precoTotal = 25;
		
		Venda venda = new Venda( data, cliente, precoTotal, itemVenda);
		repositorioVenda.adicionar(venda);
		assertNotNull(repositorioVenda.get(venda.getId()));
	
	}
	
	@Test
	void TesteRemoverVendaQueNaoEstaNoRepositorio() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		double precoTotal = 25;
		
		Venda venda = new Venda( data, cliente, precoTotal, itemVenda);
		
		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			repositorioVenda.remove(venda.getId());
		});
	}
	
	@Test
	void TesteRemoverVendaNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		
		Date data = new Date();
		Cliente cliente = new Cliente("Diana");
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item = new ItemVenda(new Produto("Caderno", 25), 1);
		itemVenda.add(item);
		double precoTotal = 25;
		
		Venda venda = new Venda( data, cliente, precoTotal, itemVenda);
		repositorioVenda.adicionar(venda);
		assertTrue(repositorioVenda.remove(venda.getId()));
	}
	
	
}
