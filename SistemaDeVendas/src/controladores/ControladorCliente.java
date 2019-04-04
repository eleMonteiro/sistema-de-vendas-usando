package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entidades.Cliente;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import repositorios.RepositorioClientes;

public class ControladorCliente {

	public long criarCliente(String nome) throws CampoComValorInvalidoException {
		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("O nome não pode ser vazio");
		}

		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException("O nome não pode conter números ou caracteres especiais");
		}

		Cliente cliente = new Cliente(nome);
		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();
		repositorioClientes.adicionar(cliente);

		return cliente.getId();
	}

	public void editarCliente(long id, String nome)
			throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (id < 1) {
			throw new CampoComValorInvalidoException("A ID tem que ser >= 1");
		}

		if (nome.equals("")) {
			throw new CampoComValorInvalidoException("O nome não pode ser vazio");
		}

		if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException("O nome não pode conter números ou caracteres especiais");
		}

		Cliente cliente = getCliente(id);

		if (cliente == null) {
			throw new ItemNaoEstaNoRepositorioException("O cliente a ser editado não existe");
		}

		cliente.setNome(nome);
	}

	public void removerCliente(long id) throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (id < 1) {
			throw new CampoComValorInvalidoException("A id tem que ser >= 1");
		}

		RepositorioClientes repositorioClientes = RepositorioClientes.getInstance();

		if (repositorioClientes.get(id) == null) {
			throw new ItemNaoEstaNoRepositorioException("O cliente a ser removido não existe");
		}

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

	public List<Cliente> procurarClientes(String filtro) throws CampoComValorInvalidoException {
		if (filtro.equals("")) {
			throw new CampoComValorInvalidoException("O filtro da pesquisa não pode ser vazio");
		}

		if (!filtro.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")) {
			throw new CampoComValorInvalidoException(
					"O filtro da pesquisa não pode conter números ou caracteres especiais");
		}

		List<Cliente> clientes = getClienteList();
		List<Cliente> clientesEncontrados = new ArrayList<Cliente>();
		Iterator<Cliente> iterator = clientes.iterator();

		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();

			if (cliente.getNome().contains(filtro)) {
				clientesEncontrados.add(cliente);
			}
		}

		return clientesEncontrados;
	}

}
