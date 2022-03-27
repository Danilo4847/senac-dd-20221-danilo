package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import model.vo.Cliente;

public class ClienteDAO {

	public Cliente inserirCliente(Cliente cliente) {
		Connection conexao = Banco.getConnection();
		String query = "INSERT INTO CLIENTE(nome,cpf)VALUES(?,?)";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);

		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());

			stmt.execute();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				cliente.setId(resultado.getInt(1));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "não foi possivel executar a query de inserção de cliente");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return cliente;

	}

	/*-----------------------------------------------------------------------------------------------------------------------------*/

	public boolean atualizarCliente(Cliente id) {
		boolean status = false;

		Connection conexao = Banco.getConnection();
		String query = "UPDATE CLIENTE SET nome=?,cpf=?";

		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);

		try {

			stmt.setString(1, id.getNome());
			stmt.setString(2, id.getCpf());

			int linhasAfetadas = stmt.executeUpdate();
			status = linhasAfetadas > 0;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na query de update do cliente");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return status;
	}

	public boolean removerCliente(int id) {
		boolean status = false;
		Connection conexao = Banco.getConnection();
		String query = "DELETE FROM CLIENTE WHERE idcliente=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);

		try {
			stmt.setInt(1, id);
			status = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover cliente. Causa:" + e.getMessage());
		}

		return status;
	}

	public void consultarCliente(int id) {
		Connection conexao = Banco.getConnection();
		String query = "SELECT*FROM CLIENTE WHERE IDCLIENTE=" + id;
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setCpf(resultado.getString(1));
				cliente.setNome(resultado.getString(2));
				cliente.setId(Integer.parseInt(resultado.getString(3)));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"não foi possivel executara  aquery de consulta dp cliente.   " + e.getMessage());
		}

	}

	public ArrayList<Cliente> concultarTodosClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		return clientes;

	}


}
