package testes;

import org.junit.jupiter.api.Test;

import entidades.Produto;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
class TesteEntidadeProduto {

	@Test
	void TesteRetornoConstrutorProduto() {
		Produto produtoAtual = new Produto();
		Assert.assertNotNull(produtoAtual);
	}
}