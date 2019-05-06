package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entidades.Produto;

public class ProdutoDAO implements IGenericoDAO<Produto> {

	private Connection connection;
	private DataSource dataSource;
	
	public ProdutoDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public long adicionar(Produto produto) {
		String sql = "insert into produto values(?,?)";
		
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setFloat(3, produto.getPreco());
		
			statement.execute();
			statement.close();

			return produto.getId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean remover(long id) {
		String sql = "DELETE FROM produto WHERE id = ?";
		try {
			this.connection = dataSource.getConnection();

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			int linhasAfetadas = stmt.executeUpdate();
			stmt.close();
			
			if( linhasAfetadas > 0 )
				return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public List<Produto> buscarPorNome(String nome) {
		String sql = "SELECT nome, preco FROM produto WHERE nome LIKE '%?%';";
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, nome);
			
			ResultSet resultSet = statement.executeQuery();
			Produto produto = null;
			
			if( resultSet.next()) {
				String nomeProduto = resultSet.getString(1);
				float precoProduto = resultSet.getFloat(2);
				produto = new Produto(nomeProduto, precoProduto);
				
				produtos.add(produto);
			}
			
			statement.close();
			
			return produtos;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	public Produto buscarPorId(long id) {
		String sql = "SELECT id, nome FROM produto WHERE id = ?";
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			Produto produto = null;
			if( resultSet.first()) {
				produto = new Produto();
				produto.setId(resultSet.getLong(1));
				produto.setNome(resultSet.getString(2));
			}
			
			statement.close();
			return produto;
			
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	public List<Produto> listar() {
		String sql = "SELECT nome, preco FROM produto";
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			this.connection = dataSource.getConnection();
			PreparedStatement statement = this.connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String nome = resultSet.getString("nome");
				float preco = resultSet.getFloat("preco");
				Produto produto = new Produto(nome, preco);
				produtos.add(produto);
			}

			statement.close();
			return produtos;
		} catch (SQLException e) {
	        throw new RuntimeException(e);	
		}		
	}

	public void editar(Produto t) {
		String sql = "";
	}
}
