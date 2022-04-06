package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String query=" SELECT * FROM TELEFONE WHERE ID= "+id;
		PreparedStatement stmt=Banco.getPreparedStatement(conexaoConnection, query);
		Telefone telefoneConsultado = null;
		
		try {
			ResultSet resultado = stmt.executeQuery();
			
			if(resultado.next()) {
				telefoneConsultado = new Telefone();
				telefoneConsultado.setAtivo(resultado.getBoolean(1));
				telefoneConsultado.setDdd(resultado.getString(2));
				telefoneConsultado.setNumero(resultado.getString(3));
				telefoneConsultado.setId(Integer.parseInt(resultado.getString(4)));
				telefoneConsultado.setTipo(Integer.parseInt(resultado.getString(5)));
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar a query de consulta de telefone. Causa: " + e.getMessage());
		}

		return telefoneConsultado;
	}

	private Telefone consultarDoResultSet(ResultSet resultado) throws SQLException {
		Telefone telefoneConsultado = new Telefone();
		telefoneConsultado.setId(resultado.getInt("id"));
		telefoneConsultado.setDdd(resultado.getString("ddd"));
		telefoneConsultado.setNumero(resultado.getString("numero"));
		telefoneConsultado.setTipo(resultado.getInt("tipo"));
		telefoneConsultado.setAtivo(resultado.getBoolean("ativo"));

		return telefoneConsultado;
	}

	public ArrayList<Telefone> consultarTodos(){
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		Connection conexao = Banco.getConnection();

		String sql = " SELECT * FROM TELEFONE ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = stmt.executeQuery();

			while(resultado.next()) {
				Telefone telefoneConsultado = consultarDoResultSet(resultado);
				telefones.add(telefoneConsultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os telefones. Causa:" + e.getMessage());
		}

		return telefones;
	}
}
