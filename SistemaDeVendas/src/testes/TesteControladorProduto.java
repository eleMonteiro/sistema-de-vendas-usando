package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controladores.ControladorProduto;
import excecoes.FloatNegativoException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.StringVaziaException;

class TesteControladorProduto {

	@Test
	void TesteCriarProdutoComNomeVazio() {
		assertThrows(StringVaziaException.class, () -> {
			ControladorProduto controladorProduto = new ControladorProduto();
			controladorProduto.criarProduto("", 19);
		});
	}
	
	@Test
	void TesteCriarProdutoComPrecoNegativo() {
		assertThrows(FloatNegativoException.class, () -> {
			ControladorProduto controladorProduto = new ControladorProduto();
			controladorProduto.criarProduto("Café", -19);
		});
	}
	
	@Test
	void TesteCriarProdutoComNomeInvalido() {
		assertThrows(FormatoDeStringInvalidoException.class, () -> {
			ControladorProduto controladorProduto = new ControladorProduto();
			controladorProduto.criarProduto("Ñescau", 19);
		});
	}

}