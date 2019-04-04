package testes.fronteira;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.QuantidadeDoElementoInvalidaException;
import fronteira.MenuVendas;
import repositorios.RepositorioVenda;

class TesteFronteiraMenuVendas {
	
	private final String stringMenuVenda = "# MENU DE VENDAS #\r\n"+"[0] Voltar\r\n"+"[1] Criar\r\n"+ "[2] Procurar\r\n"+"[3] Listar\r\n"+"$ Digite a sua opção:\r\n";
	private final String  stringOpacaoNaoEInteiro = "ERR: A opção precisa ser um inteiro\r\n";
	private final String stringOpcaoInvalida = "ERR: Opção inválida\r\n";

	@Test
	void TesteOpcaoSendoUmaLetra() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("a\r\n0".getBytes()));
		
		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + stringOpacaoNaoEInteiro + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoSendoUmCaractereEspecial() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("@\r\n0".getBytes()));
	
		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + stringOpacaoNaoEInteiro + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoSendoUmDouble() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("3.5\r\n0".getBytes()));
	
		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + stringOpacaoNaoEInteiro + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoInvalidaMenorQue0() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("-1\r\n0".getBytes()));

		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + stringOpcaoInvalida + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteOpcaoInvalidaMaiorQue3() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("4\r\n0".getBytes()));

		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + stringOpcaoInvalida + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteBuscarVendaComIDNaoInteiro() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "2\r\nA\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + "$ Digite o id da venda: \r\n"
				+ "ERR: A id tem que ser um inteiro\r\n" + stringMenuVenda;
		
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void TesteCriarVendaComIDDoClienteNaoInteiro() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "1\r\nA\r\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + "$ Digite o id do cliente: \r\n"+
			"ERR: O id e a quantidade tem que serem um inteiros\r\n"+ stringMenuVenda;
		
		assertEquals(resultadoEsperado, outputStream.toString());
	}
	
	@Test
	void testeListarVendas() throws CampoComValorInvalidoException, NullPointerException, ItemNaoEstaNoRepositorioException, ItemJaEstaNoRepositorio, QuantidadeDoElementoInvalidaException {
		// Garante que só existem os seguintes clientes
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		repositorioVenda.getListVenda().clear();
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		ControladorVenda controladorVenda = new ControladorVenda();
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto1 = controladorProduto.criarProduto("Café", 2.5f);
		long idProduto2 = controladorProduto.criarProduto("Açucar", 3.5f);
		
		Produto produto1 = controladorProduto.getProduto(idProduto1);
		Produto produto2 = controladorProduto.getProduto(idProduto2);
		
		controladorItemEstoque.criarItemEstoque(produto1.getId(), 20);
		controladorItemEstoque.criarItemEstoque(produto2.getId(), 25);
		
		List<ItemVenda> itemVenda1 = new ArrayList<>();
		itemVenda1.add(new ItemVenda(produto1, 3));
		List<ItemVenda> itemVenda2 = new ArrayList<>();
		itemVenda2.add(new ItemVenda(produto2, 4));
		
		long idVenda1 = controladorVenda.criarVenda(new Date(), new Cliente("Rafael"), controladorVenda.calcularPrecoTotal(itemVenda1), itemVenda1);
		Venda venda1 = controladorVenda.getVenda(idVenda1);
		long idVenda2 = controladorVenda.criarVenda(new Date(), new Cliente("Pedro"), controladorVenda.calcularPrecoTotal(itemVenda2), itemVenda2);
		Venda venda2 = controladorVenda.getVenda(idVenda2);
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String entrada = "3\n0";
		System.setIn(new ByteArrayInputStream(entrada.getBytes()));

		new MenuVendas().iniciar();
		String resultadoEsperado = stringMenuVenda + "(" + venda1.getId() + ") " + venda1.getCliente().getNome() +"| "+ venda1.getPrecoTotal()+ " |" +"\r\n("
				+ venda2.getId() + ") " + venda2.getCliente().getNome()+ "| " + venda2.getPrecoTotal() +" |"+ "\r\n" + stringMenuVenda;
		assertEquals(resultadoEsperado, outputStream.toString());
	}
}
