package testes.controladores;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import controladores.ControladorItemEstoque;
import controladores.ControladorProduto;
import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;

class TesteControladorItemEstoque {

	@Test
	void testeCriarItemEstoqueComIDDeprodutoInvalida() {
		long idProduto = -1;
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		}, () -> "A id do produto precisa ser >= 1");
	}

	@Test
	void testeCriarItemEstoqueComProdutoQueNaoEstaNoRepositorio() {
		long idProduto = new Produto("Arroz", 2).getId();
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		}, () -> "O produto a compor o item de estoque não existe");
	}

	@Test
	void testeCriarItemEstoqueComQuantidadeInvalida()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidade = -1;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		}, () -> "A quantidade de produtos a compor o item de estoque precisa ser >= 0");
	}

	@Test
	void testeCriarItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException,
			NullPointerException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		int quantidadeItensAntes = controladorItemEstoque.getItemEstoqueList().size();
		controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		int quantidaeItensDepois = controladorItemEstoque.getItemEstoqueList().size();

		assertEquals(quantidadeItensAntes + 1, quantidaeItensDepois);
	}

	@Test
	void testeCriarItemEstoqueQueJaExiste() throws CampoComValorInvalidoException, NullPointerException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		controladorItemEstoque.criarItemEstoque(idProduto, 50);

		assertThrows(ItemJaEstaNoRepositorio.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, 100);
		}, () -> "O item de estoque a ser criado já existe");
	}

	@Test
	void testeEditarItemEstoqueComIDInvalida() {
		long idItemEstoque = -1;
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidade);
		}, () -> "A id do item de estoque precisa ser >= 1");
	}

	@Test
	void testeEditarItemEstoqueQueNaoEstaNoRepositorio() {
		Produto produto = new Produto("Arroz", 2);
		int quantidadeAntiga = 50;
		int quantidadeNova = 100;
		long idItemEstoque = new ItemEstoque(produto, quantidadeAntiga).getId();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidadeNova);
		}, () -> "O item de estoque a ser editado não existe");
	}

	@Test
	void testeEditarItemEstoqueComQuantidadeInvalida() throws CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, NullPointerException, ItemJaEstaNoRepositorio {
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Arroz", 2);
		int quantidadeAntiga = 50;
		int quantidadeNova = -1;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidadeAntiga);

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidadeNova);
		}, () -> "A quantidade de produtos do item de estoque a ser editado precisa ser >= 0");
	}

	@Test
	void testeEditarItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException,
			NullPointerException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidadeAntiga = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidadeAntiga);
		int quantidadeNova = 100;
		controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidadeNova);
		ItemEstoque itemEstoque = controladorItemEstoque.getItemEstoque(idItemEstoque);

		assertEquals(quantidadeNova, itemEstoque.getQuantidade());
	}

	@Test
	void testeRemoverItemEstoqueComIdInvalida() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.removerItemEstoque(0);
		}, () -> "A id do item de estoque precisa ser >= 1");
	}

	@Test
	void testeRemoverItemEstoqueQueNaoEstaNoRepositorio() {
		Produto produto = new Produto("Arroz", 2);
		int quantidade = 50;
		long idItemEstoque = new ItemEstoque(produto, quantidade).getId();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			controladorItemEstoque.removerItemEstoque(idItemEstoque);
		}, () -> "O item de estoque a ser removido não existe");
	}

	@Test
	void testeRemoverItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException,
			NullPointerException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		int quantidadeItensAntes = controladorItemEstoque.getItemEstoqueList().size();
		controladorItemEstoque.removerItemEstoque(idItemEstoque);
		int quantidadeItensDepois = controladorItemEstoque.getItemEstoqueList().size();

		assertEquals(quantidadeItensAntes - 1, quantidadeItensDepois);
	}

	@Test
	void testeGetItemEstoqueQueNaoEstaNoRepositorio() {
		Produto produto = new Produto("Arroz", 2);
		int quantidade = 50;
		long idItemEstoque = new ItemEstoque(produto, quantidade).getId();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertNull(controladorItemEstoque.getItemEstoque(idItemEstoque));
	}

	@Test
	void testeGetItemEstoqueQueEstaNoRepositorio() throws CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, NullPointerException, ItemJaEstaNoRepositorio {
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Arroz", 2);
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidade);

		assertNotNull(controladorItemEstoque.getItemEstoque(idItemEstoque));
	}

	@Test
	void testeGetItemEstoquePorProdutoComIDInvalida() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.getItemEstoquePorProduto(0);
		}, () -> "A id do produto precisa ser >= 1");
	}

	@Test
	void testeGetItemEstoqueList() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertNotNull(controladorItemEstoque.getItemEstoqueList());
	}

	@Test
	void testeProcurarItensEstoquePorProdutoComFiltroVazio() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.procurarItensEstoquePorProduto("");
		}, () -> "O filtro da pesquisa não pode ser vazio");
	}

	@Test
	void testeProcurarItensEstoquePorProdutoComFiltroContendoNumeros() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.procurarItensEstoquePorProduto("Café 12");
		}, () -> "O filtro da pesquisa não pode conter números ou caracteres especiais");
	}

	@Test
	void testeProcurarItensEstoquePorProdutoComFiltroContendoCaracteresEspeciais() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.procurarItensEstoquePorProduto("@Café");
		}, () -> "O filtro da pesquisa não pode conter números ou caracteres especiais");
	}

	@Test
	void testeProcurarItensEstoquePorProdutoNaoRetornandoItemEstoque() throws CampoComValorInvalidoException,
			NullPointerException, ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, 50);
		ItemEstoque itemEstoque = controladorItemEstoque.getItemEstoque(idItemEstoque);
		String filtro = "Arraz";
		List<ItemEstoque> itensEstoque = controladorItemEstoque.procurarItensEstoquePorProduto(filtro);

		assertNotNull(itensEstoque);
		assertFalse(itensEstoque.contains(itemEstoque));
	}

	@Test
	void testeProcurarItensEstoquePorProdutoRetornandoItemEstoque() throws CampoComValorInvalidoException,
			NullPointerException, ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, 50);
		ItemEstoque itemEstoque = controladorItemEstoque.getItemEstoque(idItemEstoque);
		String filtro = "Arroz";
		List<ItemEstoque> itensEstoque = controladorItemEstoque.procurarItensEstoquePorProduto(filtro);

		assertNotNull(itensEstoque);
		assertTrue(itensEstoque.contains(itemEstoque));
	}

}
