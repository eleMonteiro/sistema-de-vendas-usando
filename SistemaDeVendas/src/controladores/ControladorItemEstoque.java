package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.ItemEstoque;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioItensEstoque;

public class ControladorItemEstoque {

	public long criarItemEstoque(long idProduto, int quantidade) throws CampoComValorInvalidoException,
			ItemNaoEstaNoRepositorioException, NullPointerException, ItemJaEstaNoRepositorio {
		if (idProduto <= 0) {
			throw new CampoComValorInvalidoException("A id do produto precisa ser >= 1");
		}

		Produto produto = new ControladorProduto().getProduto(idProduto);

		if (produto == null) {
			throw new ItemNaoEstaNoRepositorioException("O produto a compor o item de estoque não existe");
		}

		if (quantidade < 0) {
			throw new CampoComValorInvalidoException(
					"A quantidade de produtos a compor o item de estoque precisa ser >= 0");
		}

		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		if (repositorioItensEstoque.getItemEstoquePorProduto(idProduto) != null) {
			throw new ItemJaEstaNoRepositorio("O item de estoque a ser criado já existe");
		}

		ItemEstoque itemEstoque = new ItemEstoque(produto, quantidade);
		repositorioItensEstoque.adicionar(itemEstoque);

		return itemEstoque.getId();
	}

	public ItemEstoque getItemEstoque(long idItemEstoque) {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		return repositorioItensEstoque.getItemEstoque(idItemEstoque);
	}

	public ItemEstoque getItemEstoquePorProduto(long idProduto) throws CampoComValorInvalidoException {
		if (idProduto <= 0) {
			throw new CampoComValorInvalidoException("A id do produto precisa ser >= 1");
		}

		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		return repositorioItensEstoque.getItemEstoquePorProduto(idProduto);
	}

	public void editarItemEstoque(long idItemEstoque, int quantidade)
			throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		if (idItemEstoque <= 0) {
			throw new CampoComValorInvalidoException("A id do item de estoque precisa ser >= 1");
		}

		ItemEstoque itemEstoque = getItemEstoque(idItemEstoque);

		if (itemEstoque == null) {
			throw new ItemNaoEstaNoRepositorioException("O item de estoque a ser editado não existe");
		}

		if (quantidade < 0) {
			throw new CampoComValorInvalidoException(
					"A quantidade de produtos do item de estoque a ser editado precisa ser >= 0");
		}

		itemEstoque.setQuantidade(quantidade);
	}

	public void removerItemEstoque(long idItemEstoque)
			throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		if (idItemEstoque <= 0) {
			throw new CampoComValorInvalidoException("A id do item de estoque precisa ser >= 1");
		}

		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		if (repositorioItensEstoque.getItemEstoque(idItemEstoque) == null) {
			throw new ItemNaoEstaNoRepositorioException("O item de estoque a ser removido não existe");
		}

		repositorioItensEstoque.remover(idItemEstoque);
	}

	public List<ItemEstoque> getItemEstoqueList() {
		RepositorioItensEstoque repositorioItensEstoque = RepositorioItensEstoque.getInstance();

		return repositorioItensEstoque.getItemEstoqueList();
	}

	public List<ItemEstoque> procurarItensEstoquePorProduto(String filtro) throws CampoComValorInvalidoException {
		if (filtro.equals("")) {
			throw new CampoComValorInvalidoException("O filtro da pesquisa não pode ser vazio");
		}

		if (!filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException(
					"O filtro da pesquisa não pode conter números ou caracteres especiais");
		}

		List<ItemEstoque> itensEstoque = getItemEstoqueList();
		List<ItemEstoque> itensEstoqueEncontrados = new ArrayList<ItemEstoque>();
		Iterator<ItemEstoque> iterator = itensEstoque.iterator();

		while (iterator.hasNext()) {
			ItemEstoque itemEstoque = iterator.next();
			Produto produto = itemEstoque.getProduto();

			if (produto.getNome().contains(filtro)) {
				itensEstoqueEncontrados.add(itemEstoque);
			}
		}

		return itensEstoqueEncontrados;
	}

}
