package entidades;

public class Cliente {

	private static long nextId = 1;
	private long id;
	private String nome;

	public Cliente(String nome) {
		this.id = nextId;
		this.nome = nome;
		nextId++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

}
