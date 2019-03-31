package entidades;

import java.util.Date;
import java.util.List;

public class Venda {

	private static long nextId = 1;
	private long id;
	private Date data;
	private Cliente cliente;
	private double precoTotal;
	private List<ItemVenda> itemVenda;
	
	
	
	
	public Venda(Date data, Cliente cliente, double precoTotal, List<ItemVenda> itemVenda) {
		this.id = nextId;
		this.data = data;
		this.cliente = cliente;
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

	public Venda(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venda(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Venda(List<ItemVenda> itemVenda) {
		this.itemVenda = itemVenda;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
