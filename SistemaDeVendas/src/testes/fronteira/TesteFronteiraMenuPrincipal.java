package testes.fronteira;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import fronteira.MenuPrincipal;

class TesteFronteiraMenuPrincipal {

	private final String stringMenuPrincipal = "# MENU PRINCIPAL #\n" + "[0] Sair\n" + "[1] Vendas\n" + "[2] Estoque\n"
			+ "[3] Produtos\n" + "[4] Clientes\n" + "$ Digite a sua opção: \n";
	private final String stringOpcaoSair = "MSG: Fechando...\n";
	private final String stringOpcaoNaoEInteiro = "ERR: A opção precisa ser um inteiro\n";
	private final String stringOpcaoInvalida = "ERR: Opção inválida\n";

	@Test
	void testeOpcaoSair() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoSendoUmaLetra() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("a\n0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoNaoEInteiro + stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoSendoUmCaractereEspecial() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("@\n0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoNaoEInteiro + stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoSendoUmDouble() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("1.0\n0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoNaoEInteiro + stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoInvalidaMenorQue0() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("-1\n0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoInvalida + stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

	@Test
	void testeOpcaoInvalidaMaiorQue4() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		System.setIn(new ByteArrayInputStream("5\n0".getBytes()));

		new MenuPrincipal().iniciar();
		String resultadoEsperado = stringMenuPrincipal + stringOpcaoInvalida + stringMenuPrincipal + stringOpcaoSair;
		assertEquals(resultadoEsperado, outputStream.toString());
	}

}
