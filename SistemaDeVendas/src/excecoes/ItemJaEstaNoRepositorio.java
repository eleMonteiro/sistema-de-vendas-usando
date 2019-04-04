package excecoes;

public class ItemJaEstaNoRepositorio extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1098123936139322393L;

	public ItemJaEstaNoRepositorio(String mensagem) {
		super(mensagem);
	}

}
