package entidades;

import java.util.Date;
import java.util.List;

public class Venda {

	private static long nextId = 1;
	private long id;
	private Date data;
	private long idCliente;
	private double precoTotal;
	private List<ItemVenda> itemVenda;

	public Venda() {
		this.id = nextId;
		nextId++;
	}

	public Venda(Date data, long idCliente, double precoTotal, List<ItemVenda> itemVenda) {
		this.id = nextId;
		this.data = data;
		this.idCliente = idCliente;
		this.precoTotal = precoTotal;
		this.itemVenda = itemVenda;

		nextId++;
	}

	public Venda(long id) {
		this.id = id;
	}

	public Venda(Date data) {
		this.data = data;
	}

	public Venda(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Venda(List<ItemVenda> itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Venda(long idVenda, long idCliente, double precoTotal) {
		this.id = idVenda;
		this.idCliente = idCliente;
		this.precoTotal = precoTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public List<ItemVenda> getListItemVenda() {
		return itemVenda;
	}

	public void setListItemVenda(List<ItemVenda> itemVenda) {
		this.itemVenda = itemVenda;
	}

}
