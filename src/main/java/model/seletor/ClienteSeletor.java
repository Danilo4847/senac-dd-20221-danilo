package model.seletor;

public class ClienteSeletor {
	
	private int idCliente;
	private String nomeCliente;
	private String cpfCliente;
	
	
	
	
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
		nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		cpfCliente = cpfCliente;
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
	
	
	
	

}
