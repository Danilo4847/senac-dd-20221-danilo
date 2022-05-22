package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import model.seletor.ClienteSeletor;
import model.vo.Cliente;
import model.vo.Endereco;
import model.vo.LinhaTelefonica;

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

	/**
	 * @return
	 */
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
		String query = "DELETE FROM CLIENTE WHERE id=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);

		try {
			stmt.setInt(1, id);
			status = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover cliente. Causa:" + e.getMessage());
		}

		return status;
	}

	public Cliente consultarCliente(int id) {
		Connection conexao = Banco.getConnection();
		String query = "SELECT CPF, NOME, ID FROM CLIENTE WHERE ID=" + id;
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);
		ResultSet resultado = null;

		Cliente cliente = null;
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				cliente = new Cliente();
				cliente.setCpf(resultado.getString(1));
				cliente.setNome(resultado.getString(2));
				cliente.setId(Integer.parseInt(resultado.getString(3)));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"não foi possivel executara  aquery de consulta dp cliente.   " + e.getMessage());
		}

		return cliente;
	}

	private Cliente construirDoResultSet(ResultSet resultado) throws SQLException {
		Cliente clienteConsultado = new Cliente();
		
		try {
		clienteConsultado.setId(resultado.getInt("id"));
		clienteConsultado.setCpf(resultado.getString("cpf"));
		clienteConsultado.setNome(resultado.getString("nome"));

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco enderecoDoCliente = enderecoDAO.consultar(resultado.getInt("id_endereco"));
		clienteConsultado.setEndereco(enderecoDoCliente);

		LinhaTelefonicaDAO linhaDAO = new LinhaTelefonicaDAO();
		ArrayList<LinhaTelefonica> linhas = linhaDAO.consultarPorIdCliente(resultado.getInt("id"));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro no construtor de result set de cliente "+e.getMessage());
		}

		return clienteConsultado;
	}


	public ArrayList<Cliente> consultarTodos(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = stmt.executeQuery();

			while(resultado.next()) {
				Cliente clienteConsultado = construirDoResultSet(resultado);
				clientes.add(clienteConsultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os clientes. Causa:" + e.getMessage());
		}

		return clientes;
	}

	public Cliente consultarClientePorCpf(String cpf) {
		Connection banco= Banco.getConnection();
		String query="SELECT * FROM CLIENTE WHERE CPF="+cpf;
		PreparedStatement stmt = Banco.getPreparedStatement(banco, query);
		Cliente cliente=null;

		ResultSet resultado = null;
		try {
			resultado=stmt.executeQuery(query);
			while(resultado.next()) {
				cliente = construirDoResultSet(resultado);
			}
		} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getCause());
		}

		return cliente;


	}
public ArrayList<Cliente> listaClientes(ClienteSeletor selecionado){
	Connection conexao = Banco.getConnection();
	String query="SELECT*FROM CLIENTE c";
	
	if(selecionado.temFiltro()) {
		query=cirarFiltros(query,selecionado);
	}
	

	PreparedStatement stmt=Banco.getPreparedStatement(conexao, query);
	ArrayList<Cliente>clientes=new ArrayList<Cliente>();
	
	if(selecionado.temPagina()) {
		
	}
	
	try {
		ResultSet resultado=stmt.executeQuery();		
	

	while(resultado.next()) {
		Cliente c=construirDoResultSet(resultado);
		
		
		clientes.add(c);
		
	}
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	
	return clientes;
}
	


	private String cirarFiltros(String query, ClienteSeletor cliente) {
		
		query+=" where ";
		boolean primeiro=true;
		
		if(cliente.getIdCliente()>0) {
			if(!primeiro) {
				query+=" and ";
			}
			query+=" c.id= "+cliente.getIdCliente();
			primeiro=false;
		}
		if(cliente.getCpfCliente()!=null) { 
			if(!primeiro) {
				query+=" and ";
			}
			query+=" c.cpf like '% "+cliente.getCpfCliente()+" %'";
		
		}
		else if(cliente.getNomeCliente()!=null) {
			if(!primeiro) {
				query+=" and ";
			}
			query+=" c.nome like '% "+cliente.getNomeCliente()+" '";
			
		}
		
		return query;
	}
	

	
	
	
	

}
