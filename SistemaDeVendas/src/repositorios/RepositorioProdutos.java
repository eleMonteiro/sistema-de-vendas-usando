package repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Produto;
import excecoes.ItemNaoEstaNoRepositorioException;

public class RepositorioProdutos {

	private static RepositorioProdutos repositorioProdutos = null;
	private List<Produto> produtos = null;

	private RepositorioProdutos() {
		this.produtos = new ArrayList<>();
	}

	public List<Produto> getListProdutos() {
		return produtos;
	}

	public static RepositorioProdutos getInstance() {
		if (repositorioProdutos == null) {
			repositorioProdutos = new RepositorioProdutos();
		}

		return repositorioProdutos;
	}

	public boolean adicionar(Produto produto) {
		if (produto.equals(null))
			throw new NullPointerException("O produto a ser adicionado n�o pode ser nulo");

		return produtos.add(produto);
	}

	public Produto get(long idProduto) {
		Iterator<Produto> iterator = produtos.iterator();

		while (iterator.hasNext()) {
			Produto produto = iterator.next();
			if (produto.getId() == idProduto)
				return produto;
		}

		return null;
	}

	public boolean remove(long idProduto) throws ItemNaoEstaNoRepositorioException {
		Produto produto = get(idProduto);

		if (produto != null) {
			return produtos.remove(produto);

		}

		throw new ItemNaoEstaNoRepositorioException("O produto a ser removido n�o existe");
	}

	public boolean editar(long codigo, String nome, float preco) throws ItemNaoEstaNoRepositorioException {
		Produto produto = get(codigo);
		if (produto != null) {
			produto.setNome(nome);
			produto.setPreco(preco);

			return true;
		}

		throw new ItemNaoEstaNoRepositorioException("O produto a ser editado n�o existe");
	}

}
