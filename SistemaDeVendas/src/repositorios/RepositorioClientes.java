package repositorios;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import excecoes.ClienteNaoExisteException;

public class RepositorioClientes {

	private static RepositorioClientes repositorioClientes = null;
	private List<Cliente> clientes;

	private RepositorioClientes() {
		this.clientes = new ArrayList<Cliente>();
	}

	public boolean adicionar(Cliente cliente) {
		this.clientes.add(cliente);

		return true;
	}

	public Cliente get(long idCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getId() == idCliente)
				return cliente;
		}

		return null;
	}

	public List<Cliente> getClienteList() {
		return this.clientes;
	}

	public boolean remover(long idCliente) throws ClienteNaoExisteException {
		Cliente cliente = get(idCliente);

		if (cliente == null) {
			throw new ClienteNaoExisteException("Este cliente não existe");
		}

		this.clientes.remove(cliente);

		return true;
	}

	public static RepositorioClientes getInstance() {
		if (repositorioClientes == null) {
			repositorioClientes = new RepositorioClientes();
		}

		return repositorioClientes;
	}

}
