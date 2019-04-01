package controladores;

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

	public void editarCliente(long id, String nome) throws CampoComValorInvalidoException {
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
		cliente.setNome(nome);
	}

	public void removerCliente(long id) throws CampoComValorInvalidoException, ItemNaoEstaNoRepositorioException {
		if (id < 1) {
			throw new CampoComValorInvalidoException("A ID tem que ser >= 1");
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
