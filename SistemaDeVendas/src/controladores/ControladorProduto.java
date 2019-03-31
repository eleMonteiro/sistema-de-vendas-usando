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
			throw new StringVaziaException("Nome do produto não identificado!");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("Preço Inválido!");
		}
		
		if( !nome.matches("^[A-ZÀ-Ÿa-zà-ÿáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("Nome Inválido!");
		}
		
		Produto produto = new Produto(nome, preco);
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		repositorioProdutos.adicionar(produto);
		
		return produto.getCodigo();
	}

	public boolean remover(long idProduto) throws ItemNaoEstaNoRepositorioException {
		RepositorioProdutos repositorioProdutos = RepositorioProdutos.getInstance();
		return repositorioProdutos.remove(idProduto);
	}

	public boolean editarProduto(long codigo, String nome, float preco) throws StringVaziaException, FormatoDeStringInvalidoException, FloatNegativoException, ItemNaoEstaNoRepositorioException {
		
		if( nome.equals("") ) {
			throw new StringVaziaException("Nome do produto não identificado!");
		}
		
		if( !nome.matches("^[A-ZÀ-Ÿa-zà-ÿáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("Nome Inválido!");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("Preço Inválido!");
		}
		
		return RepositorioProdutos.getInstance().editar(codigo, nome, preco);
		
	}
	
	
	
}
