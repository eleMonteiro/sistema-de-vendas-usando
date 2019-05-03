package testes.fronteira;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import controladores.ControladorItemEstoque;
import controladores.ControladorProduto;
import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;
import fronteira.MenuEstoque;
import repositorios.RepositorioItensEstoque;

public class TesteFronteiraMenuEstoque {

	private final String stringMenuEstoque = "# MENU DE ESTOQUE #\n" + "[0] Voltar\n" + "[1] Criar\n" + "[2] Editar\n"
			+ "[3] Remover\n" + "[4] Procurar\n" + "[5] Listar\n" + "$ Digite a sua opção: \n";
	private final String stringOpcaoNaoEInteiro = "ERR: A opção precisa ser um inteiro\n";
	private final String stringOpcaoInvalida = "ERR: Opção inválida\n";

	@Test
	void testeOpcaoSendoUmaLetra() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("a\n0".getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + stringOpcaoNaoEInteiro + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoSendoUmCaractereEspecial() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("@\n0".getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + stringOpcaoNaoEInteiro + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoSendoUmDouble() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("1.0\n0".getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + stringOpcaoNaoEInteiro + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoInvalidaMenorQue0() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("-1\n0".getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + stringOpcaoInvalida + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoInvalidaMaiorQue5() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("6\n0".getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + stringOpcaoInvalida + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueComIDProdutoNaoInteira() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\nA\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "ERR: A id do produto e a quantidade de produtos a compor o novo item de estoque precisam ser inteiros\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueComIDProdutoInvalida() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n0\n50\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n" + "ERR: A id do produto precisa ser >= 1\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueComProdutoQueNaoExiste() {
		// Garante que o produto não existe
		long idProduto = new Produto("Arroz", 2).getId();

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n" + idProduto + "\n50\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n" + "ERR: O produto a compor o item de estoque não existe\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	// Quando já existe um item de estoque com o mesmo produto
	@Test
	void testeCriarItemEstoqueQueJaExiste() throws NullPointerException, CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		// Garante que já existe um item de estoque com este produto
		new ControladorItemEstoque().criarItemEstoque(idProduto, 25);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n" + idProduto + "\n50\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n" + "ERR: O item de estoque a ser criado já existe\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueComQuantidadeNaoInteira()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n" + idProduto + "\nA\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n"
				+ "ERR: A id do produto e a quantidade de produtos a compor o novo item de estoque precisam ser inteiros\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueComQuantidadeInvalida()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n" + idProduto + "\n-1\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n"
				+ "ERR: A quantidade de produtos a compor o item de estoque precisa ser >= 0\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeCriarItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\n" + idProduto + "\n50\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do produto: \n"
				+ "$ Digite a quantidade de produtos: \n" + "MSG: O item de estoque foi criado\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueComIDNaoInteira() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\nA\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "ERR: A id do item de estoque e a quantidade de produtos do item de estoque precisam ser inteiros\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueComIDInvalida() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\n0\n50\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "$ Digite a quantidade de produtos: \n" + "ERR: A id do item de estoque precisa ser >= 1\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueQueNaoExiste() {
		// Garante que o item de estoque não existe
		long idItemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50).getId();

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\n" + idItemEstoque + "\n100\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "$ Digite a quantidade de produtos: \n" + "ERR: O item de estoque a ser editado não existe\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueComQuantidadeNaoInteira() throws CampoComValorInvalidoException, NullPointerException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		// Garante que o item de estoque existe
		long idItemEstoque = new ControladorItemEstoque().criarItemEstoque(idProduto, 50);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\n" + idItemEstoque + "\nA\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "$ Digite a quantidade de produtos: \n"
				+ "ERR: A id do item de estoque e a quantidade de produtos do item de estoque precisam ser inteiros\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueComQuantidadeInvalida() throws CampoComValorInvalidoException, NullPointerException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		// Garante que o item de estoque existe
		long idItemEstoque = new ControladorItemEstoque().criarItemEstoque(idProduto, 50);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\n" + idItemEstoque + "\n-1\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "$ Digite a quantidade de produtos: \n"
				+ "ERR: A quantidade de produtos do item de estoque a ser editado precisa ser >= 0\n"
				+ stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarItemEstoqueCorretamente() throws CampoComValorInvalidoException, NullPointerException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		// Garante que o item de estoque existe
		long idItemEstoque = new ControladorItemEstoque().criarItemEstoque(idProduto, 50);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\n" + idItemEstoque + "\n100\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "$ Digite a quantidade de produtos: \n" + "MSG: O item de estoque foi editado\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeRemoverItemEstoqueComIDNaoInteira() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "3\nA\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "ERR: A id do item de estoque precisa ser um inteiro\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeRemoverItemEstoqueComIDInvalida() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "3\n0\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "ERR: A id do item de estoque precisa ser >= 1\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeRemoverItemEstoqueQueNaoExiste() {
		// Garante que o item de estoque não existe
		long idItemEstoque = new ItemEstoque(new Produto("Arroz", 2), 50).getId();

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "3\n" + idItemEstoque + "\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "ERR: O item de estoque a ser removido não existe\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeRemoverItemEstoqueCorretamente() throws CampoComValorInvalidoException, NullPointerException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		// Garante que o item de estoque existe
		long idItemEstoque = new ControladorItemEstoque().criarItemEstoque(idProduto, 50);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "3\n" + idItemEstoque + "\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite a id do item de estoque: \n"
				+ "MSG: O item de estoque foi removido\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeProcurarItensEstoque() throws NullPointerException, CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que só existem os seguintes itens de estoque
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();
		repositorioItensEstoque.getItemEstoqueList().clear();
		ControladorProduto controladorProduto = new ControladorProduto();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idProduto1 = controladorProduto.criarProduto("Arroz", 2);
		controladorItemEstoque.criarItemEstoque(idProduto1, 50);
		long idProduto2 = controladorProduto.criarProduto("Feijão", 3);
		long idItemEstoque2 = controladorItemEstoque.criarItemEstoque(idProduto2, 25);
		ItemEstoque itemEstoque2 = controladorItemEstoque.getItemEstoque(idItemEstoque2);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "4\njão\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "$ Digite o filtro da pesquisa: \n" + "(" + itemEstoque2.getId()
				+ ") Produto: (" + itemEstoque2.getProduto() + ") " + itemEstoque2.getProduto().getNome()
				+ "; Quantidade: " + itemEstoque2.getQuantidade() + "\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeListarItensEstoque() throws NullPointerException, CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio {
		// Garante que só existem os seguintes itens de estoque
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();
		repositorioItensEstoque.getItemEstoqueList().clear();
		ControladorProduto controladorProduto = new ControladorProduto();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idProduto1 = controladorProduto.criarProduto("Arroz", 2);
		long idItemEstoque1 = controladorItemEstoque.criarItemEstoque(idProduto1, 50);
		ItemEstoque itemEstoque1 = controladorItemEstoque.getItemEstoque(idItemEstoque1);
		long idProduto2 = controladorProduto.criarProduto("Feijão", 3);
		long idItemEstoque2 = controladorItemEstoque.criarItemEstoque(idProduto2, 25);
		ItemEstoque itemEstoque2 = controladorItemEstoque.getItemEstoque(idItemEstoque2);

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "5\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuEstoque().iniciar();
		String resultadoEsperado = stringMenuEstoque + "(" + itemEstoque1.getId() + ") Produto: ("
				+ itemEstoque1.getProduto() + ") " + itemEstoque1.getProduto().getNome() + "; Quantidade: "
				+ itemEstoque1.getQuantidade() + "\n" + "(" + itemEstoque2.getId() + ") Produto: ("
				+ itemEstoque2.getProduto() + ") " + itemEstoque2.getProduto().getNome() + "; Quantidade: "
				+ itemEstoque2.getQuantidade() + "\n" + stringMenuEstoque;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

}