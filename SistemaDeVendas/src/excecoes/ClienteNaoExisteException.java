package excecoes;

public class ClienteNaoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8152774937783911902L;

	public ClienteNaoExisteException(String mensagem) {
		super(mensagem);
	}

}
