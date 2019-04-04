package testes.fronteira;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import controladores.ControladorProduto;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import fronteira.MenuProdutos;

class TesteFronteiraMenuProdutos {
	
	private final String stringMenuProduto = "# MENU DE PRODUTOS #\r\n"+"[0] Voltar\r\n"+"[1] Criar\r\n"+
	"[2] Editar\r\n"+"[3] Remover\r\n"+"[4] Procurar\r\n"+"$ Digite a sua opção:\r\n";
	private final String  stringOpacaoNaoEInteiro = "ERR: A opção precisa ser um inteiro\r\n";
	private final String stringOpcaoInvalida = "ERR: Opção inválida\r\n";
	
	@Test
	void TesteOpcaoSendoUmaLetra() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("a\r\n0".getBytes()));
		
		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + stringOpacaoNaoEInteiro + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoSendoUmCaractereEspecial() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("@\r\n0".getBytes()));
	
		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + stringOpacaoNaoEInteiro + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoSendoUmDouble() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("3.5\r\n0".getBytes()));
	
		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + stringOpacaoNaoEInteiro + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void TesteOpcaoInvalidaMenorQue0() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("-1\r\n0".getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + stringOpcaoInvalida + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoInvalidaMaiorQue4(){
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("5\r\n0".getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + stringOpcaoInvalida + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteCriarProdutoComNomeVazio() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\n" + "\r\n" + 2.5 +"\r\n0";  
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o nome do novo produto: \r\n" + "$ Digite o preço do novo produto: \r\n" 
				+ "ERR: nome do produto a ser cadastrado não pode ser nulo\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void TesteCriarProdutoComPrecoNegativo() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\n" + "Café\r\n" + -2.5 +"\r\n0";  
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o nome do novo produto: \r\n" + "$ Digite o preço do novo produto: \r\n" 
				+ "ERR: preço do produto a ser cadastrado não pode ser negativo\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteCriarProdutoComNomeContendoCaracetersEspeciais() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\n" + "@Café\r\n" + 2.5 +"\r\n0";  
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o nome do novo produto: \r\n" + "$ Digite o preço do novo produto: \r\n" 
				+ "ERR: nome produto não pode conter caracteres especiais ou números\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteCriarProdutoComNomeContendoNumeros() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\n" + "12Café\r\n" + 2.5 +"\r\n0";  
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o nome do novo produto: \r\n" + "$ Digite o preço do novo produto: \r\n" 
				+ "ERR: nome produto não pode conter caracteres especiais ou números\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteCriarProdutoCorretamente() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\n" + "Café\r\n" + 2.5 +"\r\n0";  
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o nome do novo produto: \r\n" + "$ Digite o preço do novo produto: \r\n" 
				+ "MSG: Novo produto criado\r\n" + stringMenuProduto;
		
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteEditarProdutoComIDNaoInteiro() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\nA\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n"
				+ "ERR: A id tem que ser um inteiro\r\n" + stringMenuProduto;
		
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void TesteEditarProdutoComIDInvalida() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\n" + 0 + "\r\nJohn Doe\r\n "+ 2.5 + "\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n" + "$ Digite o novo nome do produto: \r\n"+
				"$ Digite o novo preço do produto: \r\n" + "ERR: A ID tem que ser >= 1\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
		
	@Test
	void testeEditarProdutoComNomeVazio() throws CampoComValorInvalidoException {
		//Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Café", 2.5f);
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\n" + idProduto + "\r\n\r\n "+ 2.5 + "\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n" + "$ Digite o novo nome do produto: \r\n"+
				"$ Digite o novo preço do produto: \r\n" + "ERR: nome do produto a ser editado não pode ser nulo\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeEditarProdutoComNomeContendoCaracteresEspeciais() throws CampoComValorInvalidoException {
		//Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Café", 2.5f);
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\n" + idProduto + "\r\nAçu@car\r\n "+ 2.5 + "\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n" + "$ Digite o novo nome do produto: \r\n"+
				"$ Digite o novo preço do produto: \r\n" + "ERR: nome produto não pode conter caracteres especiais ou números\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void testeEditarProdutoComValorNegativo() throws CampoComValorInvalidoException {
		//Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Café", 2.5f);
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\n" + idProduto + "\r\nAçucar\r\n "+ -2.5 + "\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n" + "$ Digite o novo nome do produto: \r\n"+
				"$ Digite o novo preço do produto: \r\n" + "ERR: preço do produto a ser editado não pode ser negativo\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void testeEditarProdutoCorretamente() throws CampoComValorInvalidoException {
		//Garante que o produto existe
		long idProduto = new ControladorProduto().criarProduto("Café", 2.5f);
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\n" + idProduto + "\r\nAçucar\r\n "+ 3.5 + "\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuProdutos().iniciar();
		String resultadoEsperado = stringMenuProduto + "$ Digite o id do produto: \r\n" + "$ Digite o novo nome do produto: \r\n"+
				"$ Digite o novo preço do produto: \r\n" + "MSG: O produto foi editado\r\n" + stringMenuProduto;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
}
