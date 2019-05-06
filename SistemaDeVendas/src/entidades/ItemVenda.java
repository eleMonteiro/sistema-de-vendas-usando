package entidades;

public class ItemVenda {

	private static long nextId = 1;
	private long id; 
	private long idProduto;
	private int quantidade;

	public ItemVenda() {
	
	}
	
	public ItemVenda(long idProduto, int quantidade) {
		this.setId(nextId);
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		
		nextId++;
	}

	public ItemVenda(long idProduto) {
		this.idProduto = idProduto;
	}

	public ItemVenda(int quantidade) {
		this.quantidade = quantidade;
	}

	public ItemVenda(long idVenda, long idProduto, int quantidade) {
		this.id = idVenda;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
