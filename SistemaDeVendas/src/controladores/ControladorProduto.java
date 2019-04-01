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
			throw new StringVaziaException("nome do produto a ser cadastrado n�o pode ser nulo");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("pre�o do produto a ser cadastrado n�o pode ser negativo");
		}
		
		if( !nome.matches("^[A-Z�-�a-z�-��������������������������������'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("nome produto n�o pode conter caracteres especiais ou n�meros");
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
			throw new StringVaziaException("nome do produto a ser editado n�o pode ser nulo");
		}
		
		if( !nome.matches("^[A-Z�-�a-z�-��������������������������������'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("nome produto n�o pode conter caracteres especiais ou n�meros");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("pre�o do produto a ser editado n�o pode ser negativo");
		}
		
		return RepositorioProdutos.getInstance().editar(codigo, nome, preco);
		
	}
	
	
	
}
