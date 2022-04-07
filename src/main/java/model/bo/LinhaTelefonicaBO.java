package model.bo;

import model.dao.LinhaTelefonicaDAO;
import model.vo.Cliente;

public class LinhaTelefonicaBO {
	
	public Cliente obterUltimoClienteDaLinhaTelefonica(int idcliente) {
		LinhaTelefonicaDAO linha = new LinhaTelefonicaDAO();
		return linha.obterUltimoClienteDaLinhaTelefonica(idcliente);
	}

}
