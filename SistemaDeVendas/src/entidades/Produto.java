package entidades;

public class Produto {

	private static long nextCodigo = 1;
	private long id;
	private String nome;
	private float preco;

	public Produto() {
	
	}

	public Produto(String nome, float preco) {
		this.id = nextCodigo;
		this.nome = nome;
		this.preco = preco;

		nextCodigo++;
	}

	public Produto(long codigo, String nome, float preco) {
		this.id = codigo;
		this.nome = nome;
		this.preco = preco;

		nextCodigo++;
	}

	public Produto(long codigo) {
		this.id = codigo;
	}
	
	public Produto(String nome) {
		this.nome = nome;
	}

	public Produto(float preco) {
		this.preco = preco;
	}

	public Produto(long codigo, String nome) {
		this.id = codigo;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
