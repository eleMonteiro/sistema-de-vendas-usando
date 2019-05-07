package DAO;

import java.util.List;

public interface IGenericoDAO<T> {

	public long adicionar(T t);
	public void remover(T t);
    public T buscarPorId(long id);
    public List<T> listar();

}
