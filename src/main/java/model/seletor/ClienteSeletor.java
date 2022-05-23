package model.seletor;

import view.TelaListagemClientes;

public class ClienteSeletor {
	
	private int idCliente;
	private String nomeCliente;
	private String cpfCliente;
	
	private int limite;
	private int pagina;
	
	
	public ClienteSeletor() {
	
		this.limite = 0;
		this.pagina = -1;
	}
	public int getLimite() {
		return limite;
	}
	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		
		if(cpfCliente != null && !cpfCliente.trim().isEmpty()) {
			this.cpfCliente = cpfCliente;
		}
	}
	
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public boolean temFiltro() {
		if(this.idCliente>0) {
			return true;
		}
		if((this.nomeCliente !=null)&&(this.nomeCliente.trim().length()>0)){
			return true;
		}
		if((this.cpfCliente != null)&&(this.cpfCliente.trim().length()>0)) {
			return true;
		}
		return false;
		
	}
	public boolean temPagina() {
		return((this.limite>0)&&(this.pagina>-1)) ;
	}
	
	public int getOffset() {
		return(this.limite*(this.pagina-1));
	}
	
	
}
