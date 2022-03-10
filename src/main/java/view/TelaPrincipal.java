package view;

import model.dao.EnderecoDAO;
import model.vo.Endereco;

public class TelaPrincipal {

	public static void main(String[]args) {
		Endereco endereco1 = new Endereco("Mauro Ramos", "10", "Florianópolis", 
				"SC", "88320-005"); 
		EnderecoDAO d = new EnderecoDAO();
		d.inserir(endereco1);
		
		
		
	}
	
}
