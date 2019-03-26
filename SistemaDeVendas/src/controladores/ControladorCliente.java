package controladores;

import entidades.Cliente;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.StringVaziaException;
import repositorios.RepositorioClientes;

public class ControladorCliente {

	public long criarCliente(String nome) throws StringVaziaException, FormatoDeStringInvalidoException {
		if (nome.equals("")) {
			throw new StringVaziaException("");
		}
		
		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new FormatoDeStringInvalidoException("");
		}
		
		Cliente cliente = new Cliente(nome);
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);
		
		return cliente.getId();
	}
	
}
