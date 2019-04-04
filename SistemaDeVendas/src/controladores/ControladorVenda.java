package controladores;

import java.util.Date;
import java.util.List;

import entidades.Cliente;
import entidades.ItemEstoque;
import entidades.ItemVenda;
import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.QuantidadeDoElementoInvalidaException;
import excecoes.CampoComValorInvalidoException;
import repositorios.RepositorioVenda;

public class ControladorVenda {

	public long criarVenda(Date data, Cliente cliente, double precoTotal, List<ItemVenda> itemVenda)
			throws CampoComValorInvalidoException, QuantidadeDoElementoInvalidaException, ItemNaoEstaNoRepositorioException {
		
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
	
	public void EditarQuantidadeItemEstoque(List<ItemVenda> itemVenda) throws QuantidadeDoElementoInvalidaException, ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();

		ItemEstoque itemEstoque;
		for (ItemVenda item : itemVenda) {
			itemEstoque = controladorItemEstoque.getItemEstoque(item.getProduto().getId());
			
			if( itemEstoque.getQuantidade() < item.getQuantidade() )
				throw new QuantidadeDoElementoInvalidaException("quantidade do produto é insuficiente");
			
			controladorItemEstoque.editarItemEstoque(itemEstoque.getId(), itemEstoque.getQuantidade()-item.getQuantidade());
		}
	}
	
	public double calcularPrecoTotal(List<ItemVenda> itemVenda) {
		double valor = 0.0;
		for (ItemVenda item : itemVenda) {
			valor += item.getProduto().getPreco() * item.getQuantidade();
		}
		return valor;
	}
}
