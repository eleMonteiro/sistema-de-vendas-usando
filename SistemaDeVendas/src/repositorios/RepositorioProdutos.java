package repositorios;

import java.util.ArrayList;
import java.util.List;

import entidades.Produto;

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
		if( repositorioProdutos == null ) {
			repositorioProdutos = new RepositorioProdutos();
		}
		
		return repositorioProdutos;
	}

	public boolean adicionar(Produto produto) {
		return produtos.add(produto);
	}

	public Produto get(int idProduto) {
		for (Produto produto : produtos) {
			if( produto.getCodigo() == idProduto )
				return produto;
		}
		return null;
	}

	public Produto remove(int idProduto) {
		return produtos.remove(idProduto);
	}
	
}
