package fronteira;

import java.util.Iterator;
import java.util.List;

import controladores.ControladorCliente;
import entidades.Cliente;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;

public class MenuClientes extends Console {

	public MenuClientes() {
		super();
	}

	private void mostrarMenuDeClientes() {
		System.out.println("# MENU DE CLIENTES #");
		System.out.println("[0] Voltar");
		System.out.println("[1] Criar");
		System.out.println("[2] Editar");
		System.out.println("[3] Remover");
		System.out.println("[4] Procurar");
		System.out.println("[5] Listar");
	}

	private void criarCliente() {
		String nomeCliente = requisitarDado("Digite o nome do novo cliente: ");

		try {
			new ControladorCliente().criarCliente(nomeCliente);
			System.out.println("MSG: Novo cliente criado");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void editarCliente() {
		try {
			long idCliente = Integer.parseInt(requisitarDado("Digite a id do cliente: "));
			String nomeCliente = requisitarDado("Digite o novo nome do cliente: ");
			new ControladorCliente().editarCliente(idCliente, nomeCliente);
			System.out.println("MSG: O cliente foi editado");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id tem que ser um inteiro");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void removerCliente() {
		try {
			long idCliente = Integer.parseInt(requisitarDado("Digite a id do cliente: "));
			new ControladorCliente().removerCliente(idCliente);
			System.out.println("MSG: O cliente foi removido");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id tem que ser um inteiro");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void procurarClientes() {
		String filtro = requisitarDado("Digite o filtro da pesquisa: ");

		try {
			List<Cliente> clientes = new ControladorCliente().procurarClientes(filtro);
			Iterator<Cliente> iterator = clientes.iterator();

			while (iterator.hasNext()) {
				Cliente cliente = iterator.next();
				System.out.println("(" + cliente.getId() + ") " + cliente.getNome());
			}
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void listarClientes() {
		List<Cliente> clientes = new ControladorCliente().getClienteList();
		Iterator<Cliente> iterator = clientes.iterator();

		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();
			System.out.println("(" + cliente.getId() + ") " + cliente.getNome());
		}
	}

	public void iniciar() {
		int opcao = -1;

		do {
			mostrarMenuDeClientes();

			try {
				opcao = Integer.parseInt(requisitarDado("Digite a sua opção: "));

				switch (opcao) {
				case 0:
					break;

				case 1:
					criarCliente();
					break;

				case 2:
					editarCliente();
					break;

				case 3:
					removerCliente();
					break;

				case 4:
					procurarClientes();
					break;

				case 5:
					listarClientes();
					break;

				default:
					System.out.println("ERR: Opção inválida");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("ERR: A opção precisa ser um inteiro");
			}
		} while (opcao != 0);
	}

}
