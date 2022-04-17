package controller;

import java.util.ArrayList;

import model.dao.ClienteDAO;
import model.vo.Cliente;

public class ClienteController {

	public ArrayList<Cliente>clientes(){ 
		ClienteDAO cliente = new ClienteDAO();
		return cliente.consultarTodos();
	}
	public Cliente consultarClienteCpf(String cpf) {
		ClienteDAO cliente = new ClienteDAO();
		return cliente.consultarClientePorCpf(cpf);
	}
	
}
