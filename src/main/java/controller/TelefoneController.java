package controller;

import java.util.ArrayList;

import model.dao.TelefoneDAO;
import model.vo.Telefone;

public class TelefoneController {

	
	public ArrayList<Telefone>consultar(){
		TelefoneDAO telefone = new TelefoneDAO();
		
		return telefone.consultarTodos();
	}
	
}
