package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.vo.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone> {

	public Telefone inserir(Telefone novoObjeto) {

		Connection conexao=Banco.getConnection();
		String query="INSERT INTO TELEFONE (DDD,NUMERO,TIPO,ATIVO) VALUES (?,?,?,?)";
		PreparedStatement stmt=Banco.getPreparedStatementWithPk(conexao, query);
		
		
		
		try {
		stmt.setString(1, novoObjeto.getDdd());
		stmt.setString(2, novoObjeto.getNumero());
		stmt.setInt(3,novoObjeto.getTipo());
		stmt.setBoolean(4,novoObjeto.isAtivo());
		
		stmt.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel executara a query de inserção de telefone "+e.getMessage());
		}
		
		
		return novoObjeto;
	}

	public boolean atualizar(Telefone objeto) {
	
		boolean atualizou = false;
		Connection conexao=Banco.getConnection();
	String query="UPDATE TELEFONE"+" SET DDD=?,NUMERO=?,TIPO=?,ATIVO=?"+" WHERE ID=?";
	PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);
	
	try {
		stmt.setString(1, objeto.getDdd());
		stmt.setString(2, objeto.getNumero());
		stmt.setInt(3,objeto.getTipo());
		stmt.setBoolean(4, objeto.isAtivo());
		
		int status =stmt.executeUpdate();
		atualizou=status>0;
		
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"Não foi possivel executara a query de alteração de telefone "+e.getMessage());
	}
	
	
		return atualizou;
	}

	public boolean remover(int id) {
		boolean removeu=false;
	Connection conexao=Banco.getConnection();
	String query ="DELETE * FROM TELEFONE WHERE ID=?";
	PreparedStatement stmt=Banco.getPreparedStatementWithPk(conexao, query);
	
	try {
		stmt.setInt(1, id);
		removeu=stmt.executeUpdate()>0;
		
	} catch (SQLException e) {
JOptionPane.showMessageDialog(null, "Não foi possivel executara a query de exclusão de telefone "+e.getMessage());
	}
	
	
		return removeu;
	}

	public Telefone consultar(int id) {

Connection conexaoConnection=Banco.getConnection();
String query="SELECT * FROM TELEFONE WHERE ID="+id;
		PreparedStatement stmt=Banco.getPreparedStatementWithPk(conexaoConnection, query);
		
		try {
			ResultSet resultado=stmt.executeQuery();
			Telefone consulta = new Telefone();
			consulta.setAtivo(resultado.getBoolean(1));
			consulta.setDdd(resultado.getString(2));
			consulta.setNumero(resultado.getString(3));
			consulta.setId(Integer.parseInt(resultado.getString(4)));
			consulta.setTipo(Integer.parseInt(resultado.getString(5)));
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nçao foi possivel executara a query de consulta");
		}
		
		
		
		return null;
	}

	public List<Telefone> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
