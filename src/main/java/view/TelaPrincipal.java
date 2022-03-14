package view;

import model.dao.ClienteDAO;
import model.dao.EnderecoDAO;
import model.vo.Cliente;
import model.vo.Endereco;
import model.vo.Telefone;

public class TelaPrincipal {

	public static void main(String[]args) {
		Endereco endereco1 = new Endereco("Mauro Ramos", "10", "Florianopolis", 
				"SC", "88320-005"); 
		EnderecoDAO d = new EnderecoDAO();
		d.inserir(endereco1);
		
	//	d.consultar('1');
		
	//	d.consultarTodos();
		//Telefone tel = new Telefone("55","987456321",1,true);
	
		Cliente cliente = new Cliente("pedro","45454545",endereco1);
		Cliente cliente1 = new Cliente("Maria","45454545",endereco1);
		ClienteDAO c = new ClienteDAO();
c.inserirCliente(cliente);
c.atualizarCliente(cliente1);
c.removerCliente(3);
	}
	
}
