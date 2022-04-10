package controller;

import java.util.ArrayList;

import model.bo.LinhaTelefonicaBO;
import model.dao.LinhaTelefonicaDAO;
import model.vo.Cliente;
import model.vo.LinhaTelefonica;

public class LinhaTelefonicaController {
	LinhaTelefonicaDAO telefonica = new LinhaTelefonicaDAO();
	LinhaTelefonicaBO bo = new LinhaTelefonicaBO();
	public ArrayList<LinhaTelefonica> consulta(){
		return telefonica.consultarTodos();
	}
	
	
	public Cliente obterUltimoClienteLinha(int idCliente) {
		return bo.obterUltimoClienteDaLinhaTelefonica(idCliente);
	}
	public Cliente obterTelefoneDesabilitado(int idCliente) { 
		return bo.obterUltimoClienteDaLinhaTelefonica(idCliente);
	}
}
