package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import controladores.ControladorVenda;
import entidades.ItemVenda;
import entidades.Venda;
import excecoes.ItemNaoEstaNoRepositorioException;

public class ItemVendaDAO implements IGenericoDAO<ItemVenda> {

	private Connection connection;
	
	public ItemVendaDAO() {
		
	}
	
	public long adicionar(ItemVenda itemVenda) {
		String sql = "INSERT INTO itemVenda values(?,?,?)";
		
		try {

			this.connection = new Conexao().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			Venda venda = new ControladorVenda().getVenda(itemVenda.getIdVenda());
			
			statement.setLong(1, venda.getId());
			statement.setLong(2, itemVenda.getIdProduto());
			statement.setFloat(3, itemVenda.getQuantidade());
		
			statement.execute();
			statement.close();

			
			return itemVenda.getIdVenda();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ItemNaoEstaNoRepositorioException e) {
		}
		return 0;
	}

	public void remover(ItemVenda itemVenda) {
		String sql = "DELETE FROM itemVenda WHERE idVenda = ? and idProduto = ?";
		try {

			this.connection = new Conexao().getConnection();
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, itemVenda.getIdVenda());
			stmt.setLong(2, itemVenda.getIdProduto());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ItemVenda buscarPorId(long idVenda) {
		String sql = "SELECT idVenda, idProduto, quantidade FROM itemVenda WHERE idVenda = ?";
		try {

			this.connection = new Conexao().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, idVenda);
			
			ResultSet resultSet = statement.executeQuery();
			ItemVenda itemVenda = null;
			if( resultSet.next()) {
				itemVenda = new ItemVenda();
				itemVenda.setIdVenda(resultSet.getLong(1));
				itemVenda.setIdProduto(resultSet.getLong(2));
				itemVenda.setQuantidade(resultSet.getInt(3));
			}
			
			statement.close();
			return itemVenda;
			
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	public List<ItemVenda> listar() {
		String sql = "SELECT idVenda, idProduto, quantidade FROM itemVenda";
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		try {

			this.connection = new Conexao().getConnection();
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long idVenda = resultSet.getLong("idVenda");
				long idProduto = resultSet.getLong("idProduto");
				int quantidade = resultSet.getInt("quantidade");
				ItemVenda itemVenda = new ItemVenda(idVenda, idProduto, quantidade);
				itens.add(itemVenda);
			}

			statement.close();
			return itens;
		} catch (SQLException e) {
	        throw new RuntimeException(e);	
		}
	}

}
