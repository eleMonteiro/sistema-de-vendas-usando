package repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Cliente;
import excecoes.ItemNaoEstaNoRepositorioException;

public class RepositorioClientes {

	private static RepositorioClientes repositorioClientes = null;
	private List<Cliente> clientes;

	private RepositorioClientes() {
		this.clientes = new ArrayList<Cliente>();
	}

	public static RepositorioClientes getInstance() {
		if (repositorioClientes == null) {
			repositorioClientes = new RepositorioClientes();
		}

		return repositorioClientes;
	}

	public List<Cliente> getClienteList() {
		return clientes;
	}

	public boolean adicionar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("O cliente a ser adicionado não pode ser nulo");
		}

		return clientes.add(cliente);
	}

	public Cliente get(long idCliente) {
		Iterator<Cliente> iterator = clientes.iterator();

		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();

			if (cliente.getId() == idCliente) {
				return cliente;
			}
		}

		return null;
	}

	public boolean remover(long idCliente) throws ItemNaoEstaNoRepositorioException {
		Cliente cliente = get(idCliente);

		if (cliente == null) {
			throw new ItemNaoEstaNoRepositorioException("O cliente a ser removido não existe");
		}

		return clientes.remove(cliente);
	}

}
