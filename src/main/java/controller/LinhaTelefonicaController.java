package controller;

import java.util.ArrayList;

import model.dao.LinhaTelefonicaDAO;
import model.vo.LinhaTelefonica;

public class LinhaTelefonicaController {

public ArrayList<LinhaTelefonica> consulta(){
	LinhaTelefonicaDAO telefonica = new LinhaTelefonicaDAO();
	return telefonica.consultarTodos();
}
	
}
