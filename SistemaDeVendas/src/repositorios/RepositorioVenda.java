package repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;

public class RepositorioVenda {

	private static RepositorioVenda repositorioVenda = null;
	private List<Venda> vendas;
	
	private RepositorioVenda() {
		this.vendas = new ArrayList<>();
	}
	
	public static RepositorioVenda getInstance() {
		if( repositorioVenda == null )
			repositorioVenda = new RepositorioVenda();
		
		return repositorioVenda;
	}

	public List<Venda> getListVenda() {
		return vendas;
	}

	public boolean adicionar(Venda venda) {
		if(venda.equals(null))
			throw new NullPointerException("A venda a ser adicionado não pode ser nulo!");
		
		return vendas.add(venda);
	}

	public Venda get(long idVenda) throws ItemNaoEstaNoRepositorioException {
		Iterator<Venda> iterator = vendas.iterator();
		
		while( iterator.hasNext() ) {
			Venda venda = iterator.next();
			if( venda.getId() == idVenda )
				return venda;
		}
		
		throw new ItemNaoEstaNoRepositorioException("A venda com o identificador fornecido não exite!");
	}

	public boolean remove(long idVenda) throws ItemNaoEstaNoRepositorioException {
		Venda venda = get(idVenda);
		
		if( venda.equals(null))
			throw new ItemNaoEstaNoRepositorioException("A venda a ser removida não existe!");
		
		return vendas.remove(venda);
		
	}

}
