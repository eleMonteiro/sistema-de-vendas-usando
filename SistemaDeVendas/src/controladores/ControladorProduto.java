package controladores;

import entidades.Produto;
import excecoes.FloatNegativoException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.StringVaziaException;
import repositorios.RepositorioProdutos;

public class ControladorProduto {

	public long criarProduto(String nome, float preco) throws StringVaziaException, FloatNegativoException, FormatoDeStringInvalidoException {
		
		if( nome.equals("") ) {
			throw new StringVaziaException("nome do produto a ser cadastrado não pode ser nulo");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("preço do produto a ser cadastrado não pode ser negativo");
		}
		
		if( !nome.matches("^[A-ZÀ-Ÿa-zà-ÿáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("nome produto não pode conter caracteres especiais ou números");
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

	public boolean editarProduto(long codigo, String nome, float preco) throws StringVaziaException, FormatoDeStringInvalidoException, FloatNegativoException, ItemNaoEstaNoRepositorioException {
		
		if( nome.equals("") ) {
			throw new StringVaziaException("nome do produto a ser editado não pode ser nulo");
		}
		
		if( !nome.matches("^[A-ZÀ-Ÿa-zà-ÿáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("nome produto não pode conter caracteres especiais ou números");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("preço do produto a ser editado não pode ser negativo");
		}
		
		return RepositorioProdutos.getInstance().editar(codigo, nome, preco);
		
	}
	
	
	
}
