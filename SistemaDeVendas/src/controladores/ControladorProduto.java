package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioProdutos;

public class ControladorProduto {

	public long criarProduto(String nome, float preco) throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("nome do produto a ser cadastrado não pode ser nulo");
		}

		if (preco < 0) {
			throw new CampoComValorInvalidoException("preço do produto a ser cadastrado não pode ser negativo");
		}

		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new CampoComValorInvalidoException("nome produto não pode conter caracteres especiais ou números");
		}

		Produto produto = new Produto(nome, preco);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		
		return produto.getId();
	}

	public boolean remover(long idProduto) throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		Produto produto = RepositorioProdutos.getInstance().get(idProduto);
		if (idProduto < 1) {
			throw new CampoComValorInvalidoException("A ID tem que ser >= 1");
		}
		
		if(produto == null) {
			throw new ItemNaoEstaNoRepositorioException("O produto a ser removido não existe");
		}
				
		return RepositorioProdutos.getInstance().remove(idProduto);
	}

	public boolean editarProduto(long id, String nome, float preco)
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		
		if (id < 1) {
			throw new CampoComValorInvalidoException("A ID tem que ser >= 1");
		}

		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("nome do produto a ser editado não pode ser nulo");
		}
    
		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException("nome produto não pode conter caracteres especiais ou números");
		}

		if (preco < 0) {
			throw new CampoComValorInvalidoException("preço do produto a ser editado não pode ser negativo");
		}

		return RepositorioProdutos.getInstance().editar(id, nome, preco);
	}
	
	public Produto getProduto(long id) {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		return repositorioProdutos.get(id);
	}
	
	public List<Produto> getProdutoList() {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();

		return repositorioProdutos.getListProdutos();
	}

	public List<Produto> procurarProduto(String filtro) throws CampoComValorInvalidoException {
		
		if (filtro.equals("")) {
			throw new CampoComValorInvalidoException("O filtro da pesquisa não pode ser vazio");
		}

		if (!filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException(
					"O filtro da pesquisa não pode conter números ou caracteres especiais");
		}
		
		List<Produto> produtos = getProdutoList();
		List<Produto> produtosEncontrados = new ArrayList<>();
		Iterator<Produto> iterator = produtos.iterator();

		while (iterator.hasNext()) {
			Produto produto = iterator.next();

			if (produto.getNome().contains(filtro)) {
				produtosEncontrados.add(produto);
			}
		}

		return produtosEncontrados;
	}

}
