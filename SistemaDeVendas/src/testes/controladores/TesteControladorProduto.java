package testes.controladores;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controladores.ControladorProduto;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioProdutos;

class TesteControladorProduto {

	private final ControladorProduto controladorProduto = new ControladorProduto();

	@Test
	void TesteCriarProdutoComNomeVazio() {
		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.criarProduto("", 19);
		}, () -> "Nome do produto a ser cadastrado não pode ser nulo");
	}

	@Test
	void TesteCriarProdutoComPrecoNegativo() {
		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.criarProduto("Café", -19);
		}, () -> "Preço do produto a ser cadastrado não pode ser nulo");
	}

	@Test
	void TesteCriarProdutoComNomeContendoCaracteresEspeciais() {
		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.criarProduto("@Café", 19);
		}, () -> "Nome do produto não pode conter caracteres especiais ou números");
	}

	@Test
	void TesteCriarProdutoComNomeContendoNumeros() {
		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.criarProduto("12Café", 19);
		}, () -> "Nome do produto não pode conter caracteres especiais ou números");
	}

	@Test
	void TesteCriarProdutoCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		long idProduto = controladorProduto.criarProduto("Celular", 500);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		assertNotNull(repositorioProdutos.get(idProduto));
	}

	@Test
	void TesteRemoverProduto() throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		Produto produto = new Produto(5, "Calculadora", 10);
		RepositorioProdutos.getInstance().adicionar(produto);
		boolean saida = controladorProduto.remover(5);

		assertTrue(saida);
	}

	@Test
	void TesteEditarProdutoComNomeVazio() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto("Calculadora", 10);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		repositorioProdutos.get(produto.getId());

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.editarProduto(produto.getId(), "", 10);
		}, () -> "Nome do produto a ser editado não pode ser nulo");
	}

	@Test
	void TesteEditarProdutoComNomeInvalido() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto("Calculadora", 10);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		repositorioProdutos.get(produto.getId());

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorProduto.editarProduto(produto.getId(), "Calcu$ladora", 10);
		}, () -> "Nome do produto não pode conter caracteres especiais");
	}

	@Test
	void TesteEditarProdutoComPrecoNegativo() {
		assertThrows(CampoComValorInvalidoException.class, () -> {
			Produto produto = new Produto(5, "Calculadora", 10);
			RepositorioProdutos.getInstance().get(produto.getId());
			controladorProduto.editarProduto(produto.getId(), "Calculadora", -10);
		}, () -> "Preço do produto a ser editado não pode ser negativo");
	}

	@Test
	void TesteEditarProdutoQueNaoExiste() {
		assertThrows(ItemNaoEstaNoRepositorioException.class, () -> {
			Produto produto = new Produto("Calculadora", 10);
			controladorProduto.editarProduto(produto.getId(), "Calculadora", 10);
		}, () -> "O produto a ser editado não existe");
	}

	@Test
	void TesteEditarProdutoCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		long idProduto = controladorProduto.criarProduto("Celular", 20);
		Produto produto = RepositorioProdutos.getInstance().get(idProduto);
		boolean returnProduto = controladorProduto.editarProduto(produto.getId(), produto.getNome(),
				produto.getPreco());

		assertTrue(returnProduto);
	}

	@Test
	void TesteSeListaRetornaElementos() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		assertNotNull(repositorioProdutos.getListProdutos());
	}

	@Test
	void testeGetProdutoQueNaoEstaNoRepositorio() throws ItemNaoEstaNoRepositorioException {
		long idProduto = new Produto("Arroz", 2).getId();
		ControladorProduto controladorProduto = new ControladorProduto();

		assertNull(controladorProduto.getProduto(idProduto));
	}

	@Test
	void testeGetProdutoQueEstaNoRepositorio()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Arroz", 2);

		assertNotNull(controladorProduto.getProduto(idProduto));
	}

}