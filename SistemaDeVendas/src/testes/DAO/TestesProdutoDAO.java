package testes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import DAO.ProdutoDAO;
import entidades.Produto;
public class TestesProdutoDAO {
	
	@Mock
	private DataSource dataSource = Mockito.mock(DataSource.class);
	
	@Mock
	private Connection connection = Mockito.mock(Connection.class);
	
	@Mock
	private PreparedStatement statement = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private PreparedStatement leituraStatement = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private ResultSet resultSet = Mockito.mock(ResultSet.class);
	
	@Mock
	private ResultSet nullResultSet = Mockito.mock(ResultSet.class);
	
	private Produto produto;
	private String produtoSolicitada = new String();
	
	@Before
	public void setUp() throws Exception {
		Assert.assertNotNull(dataSource);
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		
		Mockito.when(connection.prepareStatement(Mockito.any(String.class))).thenReturn(statement);
		Mockito.when(connection.prepareStatement(Mockito.startsWith("SELECT"))).thenReturn(leituraStatement);
		
		Mockito.when(statement.execute()).thenReturn(true);
		Mockito.doNothing().when(statement).close();
		
		produto = new Produto();
		produto.setNome("Café");

		Mockito.when(resultSet.first()).thenReturn(true);
		Mockito.when(resultSet.getString(1)).thenReturn(produto.getNome());

		Mockito.when(nullResultSet.first()).thenReturn(false);

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				produtoSolicitada = (String)invocation.getArguments()[1];
				return null;
			}
			
		}).when(leituraStatement).setString(Mockito.eq(1), Mockito.any(String.class));
		
		Mockito.when(leituraStatement.executeQuery()).thenAnswer(new Answer<ResultSet>() {
			@Override
			public ResultSet answer(InvocationOnMock invocation) throws Throwable {
				return produtoSolicitada == "Café" ? resultSet : nullResultSet;
			}
		});
	}
	
	@Test
	public void adicionaProduto() throws Exception {
		ProdutoDAO dao = new ProdutoDAO(dataSource);
		dao.adicionar(produto);
		Mockito.verify(statement,Mockito.times(1)).execute();
	}
	
	@Test
	public void buscarPessoaExistente() throws Exception {
		ProdutoDAO dao = new ProdutoDAO(dataSource);
		Produto produtoLida = dao.buscar("Café");
		Assert.assertEquals(produtoLida.getNome(), produto.getNome());
	}
}
