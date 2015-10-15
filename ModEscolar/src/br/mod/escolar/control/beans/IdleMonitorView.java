package br.mod.escolar.control.beans;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class IdleMonitorView {
     
    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "Sessão Expirada.", "Você Foi Desconectado do Sistema"));
    }
 
    public void onActive() {
    	
    	try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}