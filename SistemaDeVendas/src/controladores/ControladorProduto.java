package controladores;

import excecoes.FloatNegativoException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.StringVaziaException;

public class ControladorProduto {

	public void criarProduto(String nome, float preco) throws StringVaziaException, FloatNegativoException, FormatoDeStringInvalidoException {
		if( nome.equals("")) {
			throw new StringVaziaException("Nome do produto n�o identificado!");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("Pre�o Inv�lido!");
		}
		
		if( nome.matches("^[A-Za-z�������������������������������'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("Nome Inv�lido!");
		}
	}


}
