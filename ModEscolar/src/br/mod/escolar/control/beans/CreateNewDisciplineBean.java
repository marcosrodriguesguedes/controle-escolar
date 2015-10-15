package br.mod.escolar.control.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Discipline;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;

@ManagedBean
@ViewScoped
public class CreateNewDisciplineBean {
	
	private List<String> areaCHT;

	private String nome;
	
    private String area;
	
	private int serie;
	
	private String grau;
	
	private String turno;
	
    private String horasSemanais;
    
    private Integer horasAnuais_temp;
    
    private String horasAnuais;
	
	private FacadeAuth facadeAuth;

	private Discipline disciplina;
	
	
	private LoginBean login;
	
	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	public void setFacadeAuth(FacadeAuth facadeAuth) {
		this.facadeAuth = facadeAuth;
	}

	public Discipline getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Discipline disciplina) {
		this.disciplina = disciplina;
	}

	
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public CreateNewDisciplineBean(){
		
		facadeAuth = FacadeAuth.getInstance();
		
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
    	 HttpSession session = (HttpSession) externalContext.getSession(true);  
    	 login = (LoginBean )session.getAttribute("loginBean"); 
	
		
		
		areaCHT = new ArrayList<String>();
		
		areaCHT.add("FILOSOFIA");
		areaCHT.add("HISTÃ“RIA");
		areaCHT.add("SOCIOLOGIA");
		areaCHT.add("GEOGRAFIA");
		
		
		
		areaCHT.add("PORTUGUES");
		areaCHT.add("LITERATURA");
		areaCHT.add("ESPANHOL");
		areaCHT.add("INGLES");
		areaCHT.add("ARTE");
		areaCHT.add("EDUCACAO FISICA");
		
		
		
		areaCHT.add("BIOLOGIA");
		areaCHT.add("FISICA");
		areaCHT.add("MATEMÃ�TICA");
		areaCHT.add("QUÃ�MICA");
		
		
			
		
	}

	public List<String> getAreaCHT() {
		return areaCHT;
	}

	public void setAreaCHT(List<String> areaCHT) {
		this.areaCHT = areaCHT;
	}

	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public String getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(String horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	 
	
	/**
	 * @return the horasAnuais
	 */
	public String getHorasAnuais() {
		return horasAnuais;
	}

	/**
	 * @param horasAnuais the horasAnuais to set
	 */
	public void setHorasAnuais(String horasAnuais) {
		this.horasAnuais = horasAnuais;
	}
	
	public void setaHoraSemanal(){
		setHorasSemanais(getHorasSemanais());
	}
	
	public void setaHoraAnual(){
		int horasanuais = Integer.parseInt(getHorasAnuais());
		int horassemais = Integer.parseInt(getHorasSemanais());
		if( horasanuais <= horassemais){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Horas anuais não pode ser menor que horas semanais"));
			setHorasAnuais("");
		}
		
	}

	public void createDicipline(ActionEvent actionEvent) {
		
		
		  FacesMessage msg = null;
		 
		  disciplina = new Discipline();
		  
		  disciplina.setArea(area);
		  
		  disciplina.setHoursPerWeek(horasSemanais);
		  
		  disciplina.setHoursPerYear(horasAnuais);
		  
		  disciplina.setName(nome);
		  
		  disciplina.setCodDiscipline(nome);
		  
		  disciplina.setGrade2(grau);
		  
		  disciplina.setGrade(serie);
		  
		  disciplina.setTurno(turno);
		  
		  
		  try {
			
			  facadeAuth.newDiscipline(getLogin().getSession(), disciplina,null);
			
			 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!", disciplina.getName());
			
		} catch (NoSuchSessionException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sessao Expirada!",e.getMessage());
			e.printStackTrace();
		} catch (NoSuchPermissionException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Permissao Negada!",e.getMessage());
			e.printStackTrace();
		} catch (NoSuchUserTypeException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuï¿½rio nao Autorizado!",e.getMessage());
			e.printStackTrace();
		}
		  
		  FacesContext.getCurrentInstance().addMessage(null, msg);
		 
		 
	 }
	
	
	
}
