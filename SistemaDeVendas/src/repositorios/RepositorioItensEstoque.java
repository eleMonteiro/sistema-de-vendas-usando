package repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.ItemEstoque;

public class RepositorioItensEstoque {

	private static RepositorioItensEstoque repositorioItensEstoque = null;
	private List<ItemEstoque> itensEstoque;

	private RepositorioItensEstoque() {
		itensEstoque = new ArrayList<ItemEstoque>();
	}

	public static RepositorioItensEstoque getInstance() {
		if (repositorioItensEstoque == null) {
			repositorioItensEstoque = new RepositorioItensEstoque();
		}

		return repositorioItensEstoque;
	}

	public List<ItemEstoque> getItemEstoqueList() {
		return itensEstoque;
	}

	public boolean adicionar(ItemEstoque itemEstoque) {
//		if (itemEstoque == null) {
//			throw new NullPointerException("O item de estoque a ser adicionado não pode ser nulo");
//		}
//
//		if (getItemEstoquePorProduto(itemEstoque.getProduto().getId()) != null) {
//			throw new ItemJaEstaNoRepositorio("O item de estoque a ser adicionado já existe");
//		}

		return itensEstoque.add(itemEstoque);
	}

	public ItemEstoque getItemEstoque(long id) {
		Iterator<ItemEstoque> iterator = itensEstoque.iterator();

		while (iterator.hasNext()) {
			ItemEstoque itemEstoque = iterator.next();

			if (itemEstoque.getId() == id) {
				return itemEstoque;
			}
		}

		return null;
	}

	public ItemEstoque getItemEstoquePorProduto(long idProduto) {
		Iterator<ItemEstoque> iterator = itensEstoque.iterator();

		while (iterator.hasNext()) {
			ItemEstoque itemEstoque = iterator.next();

			if (itemEstoque.getProduto().getId() == idProduto) {
				return itemEstoque;
			}
		}

		return null;
	}

	public boolean remover(long id) {
		ItemEstoque itemEstoque = getItemEstoque(id);

//		if (itemEstoque == null) {
//			throw new ItemNaoEstaNoRepositorioException("O item de estoque a ser removido não existe");
//		}

		return itensEstoque.remove(itemEstoque);
	}

}
