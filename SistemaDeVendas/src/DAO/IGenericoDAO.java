package DAO;

import java.util.List;

public interface IGenericoDAO<T> {

	public void  adicionar(T t);
    public T remover(long id);
    public T buscar(String nome);
    public List<T> listar();

}
