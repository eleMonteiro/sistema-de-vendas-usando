package controladores;

import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioProdutos;

public class ControladorProduto {

	public long criarProduto(String nome, float preco) throws CampoComValorInvalidoException {
		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("nome do produto a ser cadastrado n�o pode ser nulo");
		}

		if (preco < 0) {
			throw new CampoComValorInvalidoException("pre�o do produto a ser cadastrado n�o pode ser negativo");
		}

		if (!nome.matches("^[A-Z�-�a-z�-��������������������������������'\\\\s]+$")) {
			throw new CampoComValorInvalidoException("nome produto n�o pode conter caracteres especiais ou n�meros");
		}

		Produto produto = new Produto(nome, preco);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);

		return produto.getId();
	}

	public boolean remover(long idProduto) throws ItemNaoEstaNoRepositorioException {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		return repositorioProdutos.remove(idProduto);
	}

	public boolean editarProduto(long codigo, String nome, float preco)
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("nome do produto a ser editado n�o pode ser nulo");
		}

		if (!nome.matches("^[A-Z�-�a-z�-��������������������������������'\\\\s]+$")) {
			throw new CampoComValorInvalidoException("nome produto n�o pode conter caracteres especiais ou n�meros");
		}

		if (preco < 0) {
			throw new CampoComValorInvalidoException("pre�o do produto a ser editado n�o pode ser negativo");
		}

		return RepositorioProdutos.getInstance().editar(codigo, nome, preco);
	}
}
