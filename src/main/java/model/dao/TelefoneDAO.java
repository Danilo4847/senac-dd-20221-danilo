package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.vo.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone>{

	public Telefone inserir(Telefone novo) {
Connection conexao=Banco.getConnection();
String query="INSERT INTO TELEFONe (ddd,numero,tipo,ativo)VALUES(?,?,?,?)";
PreparedStatement stmt=Banco.getPreparedStatementWithPk(conexao, query);


try {
	
	
	stmt.setInt(1,novo.getId());
	stmt.setString(2, novo.getDdd());
	//stmt.
	
	
	
} catch (Exception e) {
	
	
}
return null;
	}

	public boolean alterar(TelefoneDAO novo) {
	
		return false;
	}

	public boolean deletar(int id) {

		return false;
	}

	public Telefone consultar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Telefone> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean alterar(Telefone novo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
