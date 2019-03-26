package repositorios;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class RepositorioClientes {

	private static RepositorioClientes repositorioClientes = null;
	private List<Cliente> clientes;
	
	private RepositorioClientes() {
		this.clientes = new ArrayList<Cliente>();
	}
	
	public void adicionar(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public Cliente get(long idCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getId() == idCliente)
				return cliente;
		}
		
		return null;
	}
	
	public static RepositorioClientes getInstance() {
		if (repositorioClientes == null) {
			repositorioClientes = new RepositorioClientes();
		}
		
		return repositorioClientes;
	}
	
}
