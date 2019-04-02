package fronteira;

import controladores.ControladorCliente;
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

	}

	private void procurarCliente() {

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
					procurarCliente();
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
