package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import entidades.Produto;

public class ProdutoDAO implements IGenericoDAO<Produto> {

	private Connection connection;
	private DataSource dataSource;
	
	public ProdutoDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void adicionar(Produto produto) {
		String sql = "insert into produto values(?,?)";
		
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, produto.getNome());
			statement.setFloat(2, produto.getPreco());
		
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Produto remover(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Produto buscar(String nome) {
		String sql = "SELECT nome FROM produto WHERE nome = ?";
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, nome);
			
			ResultSet resultSet = statement.executeQuery();
			Produto produto = null;
			if( resultSet.first()) {
				produto = new Produto();
				produto.setNome(resultSet.getString(1));
			}
			
			statement.close();
			return produto;
			
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	public List<Produto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
