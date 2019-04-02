package entidades;

public class ItemEstoque {

	private static long proximaID = 1;
	private long id;
	private Produto produto;
	private int quantidade;

	public ItemEstoque(Produto produto, int quantidade) {
		this.id = proximaID;
		this.produto = produto;
		this.quantidade = quantidade;
		proximaID++;
	}

	public long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
