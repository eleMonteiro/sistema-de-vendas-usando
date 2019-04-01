package controladores;

import java.util.Date;
import java.util.List;

import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Venda;
import excecoes.CampoComValorInvalidoException;
import repositorios.RepositorioVenda;

public class ControladorVenda {

	public boolean criarVenda(Date data, Cliente cliente, double precoTotal, List<ItemVenda> itemVenda)
			throws CampoComValorInvalidoException {
		if (data == null)
			throw new NullPointerException("a data da venda a ser adicionada n�o pode ser nula");

		if (cliente == null)
			throw new NullPointerException("o cliente da venda a ser adicionada n�o pode ser nulo");

		if (precoTotal < 0)
			throw new CampoComValorInvalidoException("o valor da venda a ser adicionada n�o pode ser negativo");

		if (itemVenda.size() == 0)
			throw new NullPointerException("a lista de itens da venda a ser adicionada n�o pode ser vazia");

		Venda venda = new Venda(data, cliente, precoTotal, itemVenda);

		return RepositorioVenda.getInstance().adicionar(venda);
	}
}
