package entidades;

public class ItemVenda {

	private long idVenda; 
	private long idProduto;
	private int quantidade;

	public ItemVenda() {
	
	}
	
	public ItemVenda(long idProduto, int quantidade) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public ItemVenda(long idProduto) {
		this.idProduto = idProduto;
	}

	public ItemVenda(int quantidade) {
		this.quantidade = quantidade;
	}

	public ItemVenda(long idVenda, long idProduto, int quantidade) {
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}
}
