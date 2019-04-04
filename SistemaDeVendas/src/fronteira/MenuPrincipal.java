package fronteira;

public class MenuPrincipal extends Console {

	public MenuPrincipal() {
		super();
	}

	private void mostrarMenuPrincipal() {
		System.out.println("# MENU PRINCIPAL #");
		System.out.println("[0] Sair");
		System.out.println("[1] Vendas");
		System.out.println("[2] Estoque");
		System.out.println("[3] Produtos");
		System.out.println("[4] Clientes");
	}

	public void iniciar() {
		int opcao = -1;

		do {
			mostrarMenuPrincipal();

			try {
				opcao = Integer.parseInt(requisitarDado("Digite a sua opção: "));

				switch (opcao) {
				case 0:
					System.out.println("MSG: Fechando...");
					break;

				case 1:
					new MenuVendas().iniciar();
					break;

				case 2:
					new MenuEstoque().iniciar();
					break;

				case 3:
					new MenuProdutos().iniciar();
					break;

				case 4:
					new MenuClientes().iniciar();
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
