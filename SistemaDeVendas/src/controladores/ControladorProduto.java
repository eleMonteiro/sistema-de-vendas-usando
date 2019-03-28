package controladores;

import excecoes.FloatNegativoException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.StringVaziaException;

public class ControladorProduto {

	public void criarProduto(String nome, float preco) throws StringVaziaException, FloatNegativoException, FormatoDeStringInvalidoException {
		if( nome.equals("")) {
			throw new StringVaziaException("Nome do produto não identificado!");
		}
		
		if( preco < 0 ) {
			throw new FloatNegativoException("Preço Inválido!");
		}
		
		if( nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\\\s]+$")) {
			throw new FormatoDeStringInvalidoException("Nome Inválido!");
		}
	}


}
