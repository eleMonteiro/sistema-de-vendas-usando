package entidades;

public class Produto {

	private static long nextCodigo = 1;
	private long codigo;
	private String nome;
	private float preco;
	
	public Produto() {
	
	}
	
	public Produto(String nome, float preco) {
		this.codigo = nextCodigo;
		this.nome = nome;
		this.preco = preco;
		
		nextCodigo++;
	}
	
	public Produto(long codigo, String nome, float preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		
		nextCodigo++;
	}

	public long getCodigo() {
		return codigo;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
}
