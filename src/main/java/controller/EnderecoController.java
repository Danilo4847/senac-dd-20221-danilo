package controller;

import java.util.ArrayList;



import model.dao.EnderecoDAO;
import model.vo.Endereco;

public class EnderecoController {
	
	
	public ArrayList<Endereco> consultarTodos(){
		EnderecoDAO end = new EnderecoDAO();
		return end.consultarTodos();
				
		
	}
}
