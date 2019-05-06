package DAO;

import java.util.List;

public interface IGenericoDAO<T> {

	public long  adicionar(T t);
    public boolean remover(long id);
    public List<T> buscarPorNome(String nome);
    public T buscarPorId(long id);
    public List<T> listar();
    public void editar(T t);

}
