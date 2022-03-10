package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
