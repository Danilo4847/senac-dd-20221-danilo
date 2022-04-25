package controller;

import java.util.ArrayList;

import model.dao.ClienteDAO;
import model.vo.Cliente;

public class ClienteController {
	ClienteDAO cliente = new ClienteDAO();

	public ArrayList<Cliente>clientes(){ 
		return cliente.consultarTodos();
	}
	public Cliente consultarClienteCpf(String cpf) {
		return cliente.consultarClientePorCpf(cpf);
	}
	public boolean excluirCliente(int id) {
		return cliente.removerCliente(id);
	}
	public Cliente consultarClientePorId(int id) {
		return cliente.consultarCliente(id);
	}
	
}
