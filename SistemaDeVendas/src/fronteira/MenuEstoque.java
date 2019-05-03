package fronteira;

import java.util.Iterator;
import java.util.List;

import controladores.ControladorItemEstoque;
import entidades.ItemEstoque;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemJaEstaNoRepositorio;
import excecoes.ItemNaoEstaNoRepositorioException;

public class MenuEstoque extends Console {

	public MenuEstoque() {
		super();
	}

	private void mostrarMenuDeEstoque() {
		System.out.println("# MENU DE ESTOQUE #");
		System.out.println("[0] Voltar");
		System.out.println("[1] Criar");
		System.out.println("[2] Editar");
		System.out.println("[3] Remover");
		System.out.println("[4] Procurar");
		System.out.println("[5] Listar");
	}

	private void criarItemEstoque() {
		try {
			long idProduto = Long.parseLong(requisitarDado("Digite a id do produto: "));
			int quantidade = Integer.parseInt(requisitarDado("Digite a quantidade de produtos: "));
			ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
			controladorItemEstoque.criarItemEstoque(idProduto, quantidade);
			System.out.println("MSG: O item de estoque foi criado");
		} catch (NumberFormatException e) {
			System.out.println(
					"ERR: A id do produto e a quantidade de produtos a compor o novo item de estoque precisam ser inteiros");
		} catch (NullPointerException | CampoComValorInvalidoException | ItemNaoEstaNoRepositorioException
				| ItemJaEstaNoRepositorio e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void editarItemEstoque() {
		try {
			long idItemEstoque = Long.parseLong(requisitarDado("Digite a id do item de estoque: "));
			int quantidade = Integer.parseInt(requisitarDado("Digite a quantidade de produtos: "));
			ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
			controladorItemEstoque.editarItemEstoque(idItemEstoque, quantidade);
			System.out.println("MSG: O item de estoque foi editado");
		} catch (NumberFormatException e) {
			System.out.println(
					"ERR: A id do item de estoque e a quantidade de produtos do item de estoque precisam ser inteiros");
		} catch (NullPointerException | CampoComValorInvalidoException | ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void removerItemEstoque() {
		try {
			long idItemEstoque = Long.parseLong(requisitarDado("Digite a id do item de estoque: "));
			ControladorItemEstoque controladorItemEstoque = new ControladorItemEstoque();
			controladorItemEstoque.removerItemEstoque(idItemEstoque);
			System.out.println("MSG: O item de estoque foi removido");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id do item de estoque precisa ser um inteiro");
		} catch (CampoComValorInvalidoException | ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void procurarItensEstoque() {
		String filtro = requisitarDado("Digite o filtro da pesquisa: ");

		try {
			List<ItemEstoque> itensEstoque = new ControladorItemEstoque().procurarItensEstoquePorProduto(filtro);
			Iterator<ItemEstoque> iterator = itensEstoque.iterator();

			while (iterator.hasNext()) {
				ItemEstoque itemEstoque = iterator.next();
				System.out.println("(" + itemEstoque.getId() + ") Produto: (" + itemEstoque.getProduto().getId() + ") "
						+ itemEstoque.getProduto().getNome() + "; Quantidade: " + itemEstoque.getQuantidade());
			}
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void listarItensEstoque() {
		List<ItemEstoque> itensEstoque = new ControladorItemEstoque().getItemEstoqueList();
		Iterator<ItemEstoque> iterator = itensEstoque.iterator();

		while (iterator.hasNext()) {
			ItemEstoque itemEstoque = iterator.next();
			System.out.println("(" + itemEstoque.getId() + ") Produto: (" + itemEstoque.getProduto().getId() + ") "
					+ itemEstoque.getProduto().getNome() + "; Quantidade: " + itemEstoque.getQuantidade());
		}
	}

	public void iniciar() {
		int opcao = -1;

		do {
			mostrarMenuDeEstoque();

			try {
				opcao = Integer.parseInt(requisitarDado("Digite a sua opção: "));

				switch (opcao) {
				case 0:
					break;

				case 1:
					criarItemEstoque();
					break;

				case 2:
					editarItemEstoque();
					break;

				case 3:
					removerItemEstoque();
					break;

				case 4:
					procurarItensEstoque();
					break;

				case 5:
					listarItensEstoque();
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
