package fronteira;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import controladores.ControladorCliente;
import controladores.ControladorProduto;
import controladores.ControladorVenda;
import entidades.Cliente;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;
import excecoes.CampoComValorInvalidoException;
import excecoes.ItemNaoEstaNoRepositorioException;
import excecoes.QuantidadeDoElementoInvalidaException;

public class MenuVendas extends Console {

	public MenuVendas() {
		super();
	}
	
	private void mostrarMenuDeProdutos() {
		System.out.println("# MENU DE VENDAS #");
		System.out.println("[0] Voltar");
		System.out.println("[1] Criar");
		System.out.println("[2] Procurar");
		System.out.println("[3] Listar");
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
					
				case 3:
					listarVenda();
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

	private void listarVenda() {
		List<Venda> vendas = new ControladorVenda().getListVendas();
		Iterator<Venda> iterator = vendas.iterator();
		
		while ( iterator.hasNext() ) {
			Venda venda = iterator.next();
			System.out.println("("+ venda.getId()+") " + venda.getCliente().getNome() + "| " + venda.getPrecoTotal() + " |");
		}
	}

	private void buscarVenda() {
		try {
			long idVenda = Integer.parseInt(requisitarDado("Digite o id da venda: "));
			new ControladorVenda().getVenda(idVenda);
			System.out.println("MSG: A venda foi encontrada");
		} catch (NumberFormatException e) {
			System.out.println("ERR: A id tem que ser um inteiro");
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: "+e.getMessage());
		}
		
	}

	private void criarVenda() {
		
		Date data = new Date();
		
		List<ItemVenda> itemVenda = new ArrayList<>();
		ItemVenda item;
		
		String idProduto = null;
		int quantidade = 0;
		List<Produto> produtos = null;
		List<Integer> quantidades = null;
		ControladorProduto controladorProduto = new ControladorProduto();
		try {
			long idCliente = Integer.parseInt(requisitarDado("Digite o id do cliente: "));
			
			while( !idProduto.equals("sair")) {
				idProduto = requisitarDado("Digite o nome do produto a ser cadastrado OU sair para terminar a inseção dos produtos: ");
				quantidade = Integer.parseInt(requisitarDado("Digite a quantidade do produto: "));	
				produtos = controladorProduto.procurarProduto(idProduto);
				quantidades.add(quantidade);
			}
			
			int cont = 0;
			for (Produto produto : produtos) {
				item = new ItemVenda(produto, quantidades.get(cont));
				itemVenda.add(item);
				cont++;
			}
			
			ControladorVenda controladorVenda = new ControladorVenda();
			Cliente cliente = new ControladorCliente().getCliente(idCliente);
		
			controladorVenda.criarVenda(data, cliente, controladorVenda.calcularPrecoTotal(itemVenda), itemVenda);
			System.out.println("MSG: A venda foi adicionada");
		} catch (NumberFormatException e) {
			System.out.println("ERR: O id e a quantidade tem que serem um inteiros");
		} catch (CampoComValorInvalidoException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (QuantidadeDoElementoInvalidaException e) {
			System.out.println("ERR: " + e.getMessage());
		} catch (ItemNaoEstaNoRepositorioException e) {
			System.out.println("ERR: " + e.getMessage());
		}
	
	}
	
}
