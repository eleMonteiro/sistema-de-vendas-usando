package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import conexao.Conexao;
import entidades.ItemVenda;
import entidades.Venda;

public class VendaDAO implements IGenericoDAO<Venda>{

	private Connection connection;
	
	public VendaDAO() {

	}
	
	public long adicionar(Venda venda) {
		String sql = "INSERT INTO venda VALUES(?, ?, ?, ?)";
		String sqlItemVenda = "INSERT INTO itemVenda VALUES(?,?,?)";
		
		try {
			this.connection = Conexao.getInstance().getConnection();
			
			List<ItemVenda> itens = venda.getListItemVenda();
			Iterator<ItemVenda> iterator = itens.iterator();
			PreparedStatement statementItemVenda = connection.prepareStatement(sqlItemVenda);
			ItemVenda itemVenda = null;
			
			while ( iterator.hasNext() ) {
				itemVenda = iterator.next();
				statementItemVenda.setLong(1, venda.getId());
				statementItemVenda.setLong(2, itemVenda.getIdProduto());
				statementItemVenda.setInt(3, itemVenda.getQuantidade());
				
				statementItemVenda.execute();
			}
			
			statementItemVenda.close();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, venda.getId());
			statement.setDate(2, (java.sql.Date) venda.getData());
			statement.setLong(3, venda.getIdCliente());
			statement.setDouble(4, venda.getPrecoTotal());			
		
			statement.execute();
			statement.close();

			return venda.getId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean remover(long idVenda) {
		String sql = "DELETE FROM venda WHERE id = ?";
		try {
			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, idVenda);
			int linhasAfetadas = stmt.executeUpdate();
			stmt.close();
			
			if( linhasAfetadas > 0 )
				return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public Venda buscarPorId(long idVenda) {
		String sql = "SELECT id, idCliente, precoTotal FROM produto WHERE id = ?";

		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, idVenda);
			
			ResultSet resultSet = statement.executeQuery();
			Venda venda = null;
			long id = resultSet.getLong(1);
			long idCliente = resultSet.getLong(2);
			double precoTotal = resultSet.getDouble(3);
			venda = new Venda(id, idCliente, precoTotal);
				
			statement.close();
			
			return venda;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	public List<Venda> buscarPorIdCliente(long idVenda) {
		String sql = "SELECT id, idCliente, precoTotal FROM produto WHERE idCliente = ?";
		List<Venda> vendasCliente = new ArrayList<Venda>();
		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, idVenda);
			
			ResultSet resultSet = statement.executeQuery();
			Venda venda = null;
			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				long idCliente = resultSet.getLong(2);
				double precoTotal = resultSet.getDouble(3);
				venda = new Venda(id, idCliente, precoTotal);
				
				vendasCliente.add(venda);
			}
			statement.close();
			
			return vendasCliente;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	public List<Venda> listar() {
		String sql = "SELECT id, idCliente, precoTotal FROM venda";
		List<Venda> vendas = new ArrayList<Venda>();
		
		try {

			this.connection = Conexao.getInstance().getConnection();
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long idVenda = resultSet.getLong("id");
				long idCliente = resultSet.getLong("idCliente");
				double precoTotal = resultSet.getDouble("precoTotal");
				
				Venda venda = new Venda(idVenda, idCliente, precoTotal);
				vendas.add(venda);
			}

			statement.close();
			return vendas;
		} catch (SQLException e) {
	        throw new RuntimeException(e);	
		}
	}

}
