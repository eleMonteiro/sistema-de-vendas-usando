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

	public Produto get(long idProduto) {
		for (Produto produto : produtos) {
			if( produto.getCodigo() == idProduto )
				return produto;
		}
		return null;
	}

	public boolean remove(Produto produto) {
		return produtos.remove(produto);
	}


	public boolean editar(long codigo, String nome, float preco) {
		Produto produto = get(codigo);
		if(produto != null) {
			produto.setNome(nome);
			produto.setPreco(preco);
			
			return true;
		}

		return false;
	}
	
}
