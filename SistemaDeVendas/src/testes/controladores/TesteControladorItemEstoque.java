package testes.controladores;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import controladores.ControladorItemEstoque;
import controladores.ControladorProduto;
import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;

class TesteControladorItemEstoque {

	@Test
	void testeCriarItemEstoqueComIDDeprodutoInvalida() {
		long idProduto = -1;
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		}, () -> "A ID do produto precisa ser >= 1");
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
	void testeCriarItemEstoqueComQuantidadeInvalida() throws CampoComValorInvalidoException {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidade = -1;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		}, () -> "A quantidade de produtos a compor o item de estoque precisa ser >= 0");
	}

	@Test
	void testeCriarItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		long idProduto = new ControladorProduto().criarProduto("Arroz", 2);
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		int quantidadeItensAntes = controladorItemEstoque.getItemEstoqueList().size();
		controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
		int quantidaeItensDepois = controladorItemEstoque.getItemEstoqueList().size();

		assertEquals(quantidadeItensAntes + 1, quantidaeItensDepois);
	}

	@Test
	void testeEditarItemEstoqueComIDInvalida() {
		long idItemEstoque = -1;
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidade);
		}, () -> "A ID do item de estoque precisa ser >= 1");
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
	void testeEditarItemEstoqueComQuantidadeInvalida()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Arroz", 2);
		int quantidadeAntiga = 50;
		int quantidadeNova = -1;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidadeAntiga);

		assertThrows(CampoComValorInvalidoException.class, () -> {
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidadeNova);
		}, () -> "A quantidade do item de estoque a ser editado precisa ser >= 0");
	}

	@Test
	void testeEditarItemEstoqueCorretamente() throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
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
	void testeRemoverItemEstoqueCorretamente()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
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
	void testeGetItemEstoqueQueEstaNoRepositorio()
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		ControladorProduto controladorProduto = new ControladorProduto();
		long idProduto = controladorProduto.criarProduto("Arroz", 2);
		int quantidade = 50;
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
		long idItemEstoque = controladorItemEstoque.criarItemEstoque(idProduto, quantidade);

		assertNotNull(controladorItemEstoque.getItemEstoque(idItemEstoque));
	}

	@Test
	void testeGetItemEstoqueList() {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		assertNotNull(controladorItemEstoque.getItemEstoqueList());
	}

}
