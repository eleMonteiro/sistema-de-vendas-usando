package controladores;

import java.util.List;

import entidades.Cliente;
import excecoes.ClienteNaoExisteException;
import excecoes.FormatoDeStringInvalidoException;
import excecoes.IDInvalidaException;
import excecoes.StringVaziaException;
import repositorios.RepositorioClientes;

public class ControladorCliente {

	public long criarCliente(String nome) throws StringVaziaException, FormatoDeStringInvalidoException {
		if (nome.equals("")) {
			throw new StringVaziaException("O nome não pode ser vazio");
		}

		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new FormatoDeStringInvalidoException("O nome não pode conter números ou caracteres especiais");
		}

		Cliente cliente = new Cliente(nome);
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);

		return cliente.getId();
	}

	public void editarCliente(long id, String nome) throws StringVaziaException, FormatoDeStringInvalidoException,
			IDInvalidaException, ClienteNaoExisteException {
		if (id < 1) {
			throw new IDInvalidaException("A ID tem que ser >= 1");
		}
		
		if (nome.equals("")) {
			throw new StringVaziaException("O nome não pode ser vazio");
		}

		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new FormatoDeStringInvalidoException("O nome não pode conter números ou caracteres especiais");
		}

		Cliente cliente = getCliente(id);
		
		if (cliente == null) {
			throw new ClienteNaoExisteException("Este cliente não existe");
		}
		
		cliente.setNome(nome);
	}

	public void removerCliente(long id) throws IDInvalidaException, ClienteNaoExisteException {
		if (id < 1) {
			throw new IDInvalidaException("A ID tem que ser >= 1");
		}
		
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.remover(id);
	}

	public Cliente getCliente(long id) {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		return repositorioClientes.get(id);
	}

	public List<Cliente> getClienteList() {
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		return repositorioClientes.getClienteList();
	}
}
