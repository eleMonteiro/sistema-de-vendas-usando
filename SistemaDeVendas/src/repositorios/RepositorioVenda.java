package repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Venda;

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
		return vendas.add(venda);
	}

	public Venda get(long idVenda) {
		Iterator<Venda> iterator = vendas.iterator();
		
		while( iterator.hasNext() ) {
			Venda venda = iterator.next();
			if( venda.getId() == idVenda )
				return venda;
		}
		return null;
	}
}
