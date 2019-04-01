package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controladores.ControladorProduto;
import entidades.Produto;
import excecoes.FloatNegativoException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.StringVaziaException;
import repositorios.RepositorioProdutos;

class TesteControladorProduto {

	private final ControladorProduto controladorProduto = new ControladorProduto();
	
	@Test
	void TesteCriarProdutoComNomeVazio() {
		assertThrows(StringVaziaException.class, () -> {
			controladorProduto.criarProduto("", 19);
		}, () -> "nome do produto a ser cadastrado não pode ser nulo");
	}
	
	@Test
	void TesteCriarProdutoComPrecoNegativo() {
		assertThrows(FloatNegativoException.class, () -> {
			controladorProduto.criarProduto("Café", -19);
		}, () -> "preço do produto a ser cadastrado não pode ser negativo" );
	}
	
	@Test
	void TesteCriarProdutoComNomeContendoCaracteresEspeciais() {
		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorProduto.criarProduto("@Café", 19);
		}, () -> "nome produto não pode conter caracteres especiais ou números");
	}
	
	@Test
	void TesteCriarProdutoComNomeContendoNumeros() {
		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			controladorProduto.criarProduto("12Café", 19);
		}, () -> "nome produto não pode conter caracteres especiais ou números");
	}
	
	@Test 
	void TesteCriarProdutoCorretamente() throws StringVaziaException, FloatNegativoException, FormatoDeStringInvalidoException, ItemNaoEstaNoRepositorioException{
		long idProduto = controladorProduto.criarProduto("Celular", 500);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		assertNotNull(repositorioProdutos.get(idProduto));
	}
	
	@Test
	void TesteRemoverProduto() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto(5,"Calculadora", 10);
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
		
		assertThrows(StringVaziaException.class, () ->{
			controladorProduto.editarProduto(produto.getId(), "", 10);
		}, () -> "nome do produto a ser editado não pode ser nulo");
	}
	
	@Test
	void TesteEditarProdutoComNomeInvalido() throws ItemNaoEstaNoRepositorioException {
		Produto produto = new Produto("Calculadora", 10);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		repositorioProdutos.get(produto.getId());
		
		assertThrows(FormatoDeStringInvalidoException.class, () ->{
			controladorProduto.editarProduto(produto.getId(), "Calcu$ladora", 10);
		}, () -> "nome produto não pode conter caracteres especiais");
	}
	
	@Test
	void TesteEditarProdutoComPrecoNegativo() {
		assertThrows(FloatNegativoException.class, () ->{
			Produto produto = new Produto(5,"Calculadora", 10);
			RepositorioProdutos.getInstance().get(produto.getId());
			controladorProduto.editarProduto(produto.getId(), "Calculadora", -10);
		}, () -> "preço do produto a ser editado não pode ser negativo");
	}
	
	@Test
	void TesteEditarProdutoQueNaoExiste() {
		assertThrows(ItemNaoEstaNoRepositorioException.class, () ->{
			Produto produto = new Produto("Calculadora", 10);
			controladorProduto.editarProduto(produto.getId(), "Calculadora", 10);
		}, () -> "O produto a ser editado não existe");
	}
	
	@Test
	void TesteEditarProdutoCorretamente() throws StringVaziaException, FloatNegativoException, FormatoDeStringInvalidoException, ItemNaoEstaNoRepositorioException {
		long idProduto = controladorProduto.criarProduto("Celular", 20);
		Produto produto = RepositorioProdutos.getInstance().get(idProduto);
		boolean returnProduto = controladorProduto.editarProduto(produto.getId(), produto.getNome(), produto.getPreco());
		assertTrue(returnProduto);
	}
	
	@Test
	void TesteSeListaRetornaElementos() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		assertNotNull(repositorioProdutos.getListProdutos());
	}

}