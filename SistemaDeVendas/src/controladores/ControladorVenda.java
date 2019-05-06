package controladores;

import java.util.Date;
import java.util.List;

import DAO.VendaDAO;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.QuantidadeDoElementoInvalidaException;

public class ControladorVenda {

	public long criarVenda(Date data, long idCliente, double precoTotal, List<ItemVenda> itemVenda)
			throws CampoComValorInvalidoException, QuantidadeDoElementoInvalidaException,
			ItemNaoEstaNoRepositorioException {

		if (data == null)
			throw new NullPointerException("a data da venda a ser adicionada não pode ser nula");

		if ( idCliente <= 0)
			throw new NullPointerException("o cliente da venda a ser adicionada não pode ser nulo");
		
		if (precoTotal < 0)
			throw new CampoComValorInvalidoException("o valor da venda a ser adicionada não pode ser negativo");

		if (itemVenda.size() == 0)
			throw new NullPointerException("a lista de itens da venda a ser adicionada não pode ser vazia");
//
//		Venda venda = new Venda(data, cliente, precoTotal, itemVenda);
//		RepositorioVenda.getInstance().adicionar(venda);
//		return venda.getId();
		
		Venda venda = new Venda(data, idCliente, precoTotal, itemVenda); 
		return new VendaDAO().adicionar(venda);
	}

	public List<Venda> getListVendas() {
//		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
//		return repositorioVenda.getListVenda();
		return new VendaDAO().listar();
	}

	public List<Venda> getListVendaPorIdCliente(long idCliente){
//		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
//		return repositorioVenda.getListVendaByCliente(idCliente);
		return new VendaDAO().buscarPorIdCliente(idCliente);
	}
		
	public Venda getVenda(long idVenda) throws ItemNaoEstaNoRepositorioException {
//		RepositorioVenda repositorioVenda = RepositorioVenda.getInstance();
//		Venda venda = repositorioVenda.get(idVenda);
		
		Venda venda = new VendaDAO().buscarPorId(idVenda);
		
		if (venda == null) {
			throw new ItemNaoEstaNoRepositorioException("A venda com o identificador fornecido não existe!");
		}

		return venda;
	}

	public void EditarQuantidadeItemEstoque(List<ItemVenda> itemVenda) throws QuantidadeDoElementoInvalidaException,
			ItemNaoEstaNoRepositorioException, CampoComValorInvalidoException {
//		ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
//
//		ItemEstoque itemEstoque;
//		for (ItemVenda item : itemVenda) {
//			itemEstoque = controladorItemEstoque.getItemEstoque(item.getIdProduto());
//
//			if (itemEstoque.getQuantidade() < item.getQuantidade())
//				throw new QuantidadeDoElementoInvalidaException("quantidade do produto é insuficiente");
//
//			controladorItemEstoque.editarItemEstoque(itemEstoque.getId(),
//					itemEstoque.getQuantidade() - item.getQuantidade());
//		}
	}

	public double calcularPrecoTotal(List<ItemVenda> itemVenda) {
		ControladorProduto controladorProduto = new ControladorProduto();
		double valor = 0.0;
		Produto produto = null;
		for (ItemVenda item : itemVenda) {
			produto = controladorProduto.getProduto(item.getIdProduto());
			valor += produto.getPreco() * item.getQuantidade();
		}
		return valor;
	}
}