package model.dao;

import java.util.ArrayList;

public interface BaseDAO<t> {

	
	public t inserir(t novo);
	public boolean alterar(t novo);
	public boolean deletar(int id);
	public t consultar(int id);
	public ArrayList<t> consultarTodos();
	
	
}
