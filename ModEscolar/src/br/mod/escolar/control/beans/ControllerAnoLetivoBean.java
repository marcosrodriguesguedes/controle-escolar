package br.mod.escolar.control.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.mod.escolar.control.OperationsManager;
import br.mod.escolar.model.exception.NoSuchStudentException;


@ManagedBean
@ViewScoped
public class ControllerAnoLetivoBean {
	
	
	private String grau;
	
	private String serie;
	
	private OperationsManager op;
	
	private LoginBean login;
	
	public ControllerAnoLetivoBean(){
		
		op = OperationsManager.getInstance();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		
		login = (LoginBean) session.getAttribute("loginBean");
	}
	
	public void finalizarAnoLetivo(){
		
		try {
			op.concluirAnoLetivoDisciplina(login.getSession(), grau , serie);
		} catch (NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public OperationsManager getOp() {
		return op;
	}

	public void setOp(OperationsManager op) {
		this.op = op;
	}
	
	
	

}
