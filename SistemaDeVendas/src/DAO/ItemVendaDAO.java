package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entidades.ItemVenda;

public class ItemVendaDAO implements IGenericoDAO<ItemVenda> {

	private Connection connection;
	
	public ItemVendaDAO() {
		
	}
	
	public long adicionar(ItemVenda itemVenda) {
		String sql = "INSERT INTO itemVenda values(?,?,?)";
		
		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, itemVenda.getId());
			statement.setLong(2, itemVenda.getIdProduto());
			statement.setFloat(3, itemVenda.getQuantidade());
		
			statement.execute();
			statement.close();

			return itemVenda.getId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean remover(long idVenda, long idProduto) {
		String sql = "DELETE FROM itemVenda WHERE idVenda = ? and idProduto = ?";
		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, idVenda);
			stmt.setLong(2, idProduto);
			int linhasAfetadas = stmt.executeUpdate();
			stmt.close();
			
			if( linhasAfetadas > 0 )
				return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;	}

	public ItemVenda buscarPorId(long idVenda) {
		String sql = "SELECT idVenda, idProduto, quantidade FROM itemVenda WHERE idVenda = ?";
		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, idVenda);
			
			ResultSet resultSet = statement.executeQuery();
			ItemVenda itemVenda = null;
			if( resultSet.next()) {
				itemVenda = new ItemVenda();
				itemVenda.setId(resultSet.getLong(1));
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

			this.connection = Conexao.getInstance().getConnection();
			
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
