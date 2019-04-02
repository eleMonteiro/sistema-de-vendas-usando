package controladores;

import java.util.List;

import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioItensEstoque;

public class ControladorItemEstoque {

	public long criarItemEstoque(long idProduto, int quantidade)
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (idProduto <= 0) {
			throw new CampoComValorInvalidoException("A ID do produto precisa ser >= 1");
		}

		Produto produto = new ControladorProduto().getProduto(idProduto);

		if (produto == null) {
			throw new ItemNaoEstaNoRepositorioException("O produto a compor o item de estoque não existe");
		}

		if (quantidade < 0) {
			throw new CampoComValorInvalidoException(
					"A quantidade de produtos a compor o item de estoque precisa ser >= 0");
		}

		ItemEstoque itemEstoque = new ItemEstoque(produto, quantidade);
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();
		repositorioItensEstoque.adicionar(itemEstoque);

		return itemEstoque.getId();
	}

	public ItemEstoque getItemEstoque(long idItemEstoque) {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		return repositorioItensEstoque.getItemEstoque(idItemEstoque);
	}

	public void editarItemEstoque(long idItemEstoque, int quantidade)
			throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		if (idItemEstoque <= 0) {
			throw new CampoComValorInvalidoException("A ID do item de estoque precisa ser >= 1");
		}

		ItemEstoque itemEstoque = getItemEstoque(idItemEstoque);

		if (itemEstoque == null) {
			throw new ItemNaoEstaNoRepositorioException("O item de estoque a ser editado não existe");
		}

		if (quantidade < 0) {
			throw new CampoComValorInvalidoException("A quantidade do item de estoque a ser editado precisa ser >= 0");
		}

		itemEstoque.setQuantidade(quantidade);
	}

	public void removerItemEstoque(long idItemEstoque) throws ItemNaoEstaNoRepositorioException {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		repositorioItensEstoque.remover(idItemEstoque);
	}

	public List<ItemEstoque> getItemEstoqueList() {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		return repositorioItensEstoque.getItemEstoqueList();
	}

}
