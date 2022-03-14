package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.vo.Endereco;
public class EnderecoDAO {

	
	public Endereco inserir(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		
		String query = "INSERT INTO ENDERECO (rua, numero, cep, uf, cidade) VALUES (?,?,?,?,?)";

		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conexao, query);
		try {

			pstmt.setString(1,novoEndereco.getRua());
			pstmt.setString(2,novoEndereco.getNumero());
			pstmt.setString(3,novoEndereco.getCep());
			pstmt.setString(4,novoEndereco.getUf());
			pstmt.setString(5,novoEndereco.getCidade());

			pstmt.execute();

			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));

			}

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Não foi possivel executar a query de inserção de endereço");
			System.out.println(e.getCause());
		} 

		return novoEndereco;
		
	}
	public boolean atualizar(Endereco endereco) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO "
					+" SET RUA=?, UF=?, CIDADE=?, NUMERO=?, CEP=? "
					+" WHERE ID=?";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getUf());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getCep());
			stmt.setInt(6, endereco.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar endereÃ§o. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	
	public boolean remover(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover endereÃ§o. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}
	
	public List<Endereco> consultar(int id) {
		
		ArrayList<Endereco> enderecoConsultado = new ArrayList<Endereco>();
	
	Connection conexao = Banco.getConnection();

	ResultSet resultado =null;
	
	Endereco valor = new Endereco();
	String query="SELECT * FROM ENDERECO WHERE IDENDERECO ="+id;
	Statement st = Banco.getPreparedStatementWithPk(conexao, query);	
	
		try {
			
			resultado=st.executeQuery(query);
			
			if(resultado.next()) {
			valor.setCep(resultado.getString(1));
			valor.setCidade(resultado.getString(2));
			valor.setUf(resultado.getString(3));
			valor.setNumero(resultado.getString(4));
			valor.setId(Integer.parseInt(resultado.getString(5)));
			valor.setRua(resultado.getString(6));
			}
			
			enderecoConsultado.add(valor);
			
			
		}catch (Exception e) {
		JOptionPane.showConfirmDialog(null,"Erro na execução da query de consulta");
		System.out.println(e.getMessage());
		}
		
		return enderecoConsultado;
	}
	
	
	
	public ArrayList<Endereco> consultarTodos(){

		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
	
		Connection conexao= Banco.getConnection();
		ResultSet resultado=null;
		
		String query="SELECT*FROM ENDERECO";
		
		PreparedStatement s = Banco.getPreparedStatementWithPk(conexao, query);
		
		try {
			resultado=s.executeQuery(query);
		while(resultado.next()) {
			Endereco endereco = new Endereco();
			endereco.setCep(resultado.getString(1));
			endereco.setCidade(resultado.getString(2));
			endereco.setNumero(resultado.getString(3));
			endereco.setRua(resultado.getString(4));
			endereco.setUf(resultado.getString(5));
			endereco.setId(Integer.parseInt(resultado.getString(6)));
			
			enderecos.add(endereco);
		}
			
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Não foi possivel executar a query de consulta geral");
		JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		return enderecos;
	}
}
