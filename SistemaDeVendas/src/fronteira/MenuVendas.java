package fronteira;

import controladores.ControladorVenda;
import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;

public class MenuVendas extends Console {

	public MenuVendas() {
		super();
	}
	
	private void mostrarMenuDeProdutos() {
		System.out.println("# MENU DE VENDAS #");
		System.out.println("[0] Voltar");
		System.out.println("[1] Criar");
		System.out.println("[2] Procurar");
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
					criarVenda();
					break;
					
				case 2:
					buscarVenda();
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

	private void buscarVenda() {
		try {
				long idVenda = Integer.parseInt(requisitarDado("Digite o id da venda: "));
				Venda venda = new ControladorVenda().getVenda(idVenda);
				System.out.println("MSG: A venda foi encontrada");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id tem que ser um inteiro");
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: "+e.getMessage());
		}
		
	}

	private void criarVenda() {
		// TODO Auto-generated method stub
//		System.out.println("MSG: O produto foi editado");
//	} catch (NumberFormatException e) {
//		System.out.println("ERR: A id tem que ser um inteiro");
//	}
	}
	
}
