package controladores;

import java.util.List;

import DAO.ProdutoDAO;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;

public class ControladorProduto {

	public long criarProduto(String nome, float preco)
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
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
		return new ProdutoDAO().adicionar(produto);
	}

	public void remover(Produto produto) throws ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		
		if (produto == null) {
			throw new ItemNaoEstaNoRepositorioException("O produto a ser removido não existe");
		}

		new ProdutoDAO().remover(produto);
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

		Produto produto = new Produto(id, nome, preco);
		return new ProdutoDAO().editar(produto);
	}
	
	public Produto getProduto(long idProduto) {
		return new ProdutoDAO().buscarPorId(idProduto);
	}
	
	public List<Produto> getProdutoList() {
		return new ProdutoDAO().listar();
	}

	public List<Produto> procurarProduto(String filtro) throws CampoComValorInvalidoException {
		
		if (filtro.equals("")) {
			throw new CampoComValorInvalidoException("O filtro da pesquisa não pode ser vazio");
		}

		if (!filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException(
					"O filtro da pesquisa não pode conter números ou caracteres especiais");
		}
				
		return new ProdutoDAO().buscarPorNome(filtro);
	}

}
