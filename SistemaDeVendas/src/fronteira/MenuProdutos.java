package fronteira;

import java.util.Iterator;
import java.util.List;

import controladores.ControladorProduto;
import entidades.Produto;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;

public class MenuProdutos extends Console {

	public MenuProdutos() {
		super();
	}

	private void mostrarMenuDeProdutos() {
		System.out.println("# MENU DE PRODUTOS #");
		System.out.println("[0] Voltar");
		System.out.println("[1] Criar");
		System.out.println("[2] Editar");
		System.out.println("[3] Remover");
		System.out.println("[4] Procurar");
		System.out.println("[5] Listar");
	}

	public void iniciar() {
		int opcao = -1;

		do {
			mostrarMenuDeProdutos();

			try {
				opcao = Integer.parseInt(requisitarDado("Digite a sua opção:"));
				switch (opcao) {
				case 0:
					break;

				case 1:
					criarProduto();
					break;

				case 2:
					editarProduto();
					break;

				case 3:
					removerProduto();
					break;

				case 4:
					buscarProduto();
					break;
				case 5:
					listarProdutos();
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

	private void listarProdutos() {
		List<Produto> produtos = new ControladorProduto().getProdutoList();
		Iterator<Produto> iterator = produtos.iterator();
		
		while ( iterator.hasNext() ) {
			Produto produto = iterator.next();
			System.out.println("(" + produto.getId() + ") " + produto.getNome() );
		}
	}

	private void editarProduto() {
		try {
			long idProduto = Integer.parseInt(requisitarDado("Digite o id do produto: "));
			String nomeProduto = requisitarDado("Digite o novo nome do produto: ");
			float precoProduto = Float.parseFloat(requisitarDado("Digite o novo preço do produto: "));
			new ControladorProduto().editarProduto(idProduto, nomeProduto, precoProduto);
			System.out.println("MSG: O produto foi editado");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id tem que ser um inteiro");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void buscarProduto() {
		try {
			long idProduto = Integer.parseInt(requisitarDado("Digite o id do produto: "));
			Produto produto = new ControladorProduto().getProduto(idProduto);
			System.out.println("("+ idProduto +")" + produto.getNome());
		} catch (NumberFormatException e) {
			System.out.println("ERR: O id tem que ser um inteiro");
		}
	}

	private void removerProduto() {
		try {
			long idProduto = Long.parseLong("Digite o id do produto: ");
			new ControladorProduto().remover(idProduto);
			System.out.println("MSG: O produto foi removido");
		} catch (NumberFormatException e) {
			System.out.println("ERR: O id tem que ser um inteiro");
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	}

	private void criarProduto() {
		String nomeProduto = requisitarDado("Digite o nome do novo produto: ");
		float precoProduto = Float.parseFloat(requisitarDado("Digite o preço do novo produto: "));
		try {
			new ControladorProduto().criarProduto(nomeProduto, precoProduto);
			System.out.println("MSG: Novo produto criado");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("ERR: " + e.getMessage());
		} 
	}

}