package controladores;

import java.util.Date;
import java.util.List;

import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.CampoComValorInvalidoException;
import repositorios.RepositorioVenda;

public class ControladorVenda {

	public long criarVenda(Date data, Cliente cliente, double precoTotal, List<ItemVenda> itemVenda)
			throws CampoComValorInvalidoException {
		if (data == null)
			throw new NullPointerException("a data da venda a ser adicionada não pode ser nula");

		if (cliente == null)
			throw new NullPointerException("o cliente da venda a ser adicionada não pode ser nulo");

		if (precoTotal < 0)
			throw new CampoComValorInvalidoException("o valor da venda a ser adicionada não pode ser negativo");

		if (itemVenda.size() == 0)
			throw new NullPointerException("a lista de itens da venda a ser adicionada não pode ser vazia");
				
		Venda venda = new Venda(data, cliente, precoTotal, itemVenda);
		RepositorioVenda.getInstance().adicionar(venda);
	
		return venda.getId();
	}


	public List<Venda> getListVendas() {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		return repositorioVenda.getListVenda();
	}

	public Venda getVenda(long idVenda) throws ItemNaoEstaNoRepositorioException {
		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
		Venda venda = repositorioVenda.get(idVenda);
		
		if( venda == null) {
			throw new ItemNaoEstaNoRepositorioException("A venda com o identificador fornecido não existe!");
		}
		
		return venda;
	}
}
