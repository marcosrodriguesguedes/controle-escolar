package br.mod.escolar.control.beans;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Equipe;
import br.mod.escolar.model.entities.Notas;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.util.DownloadRelatoriosUtil;

@ManagedBean
@ViewScoped
public class ListEquipesBean {

	private List<Equipe> filteredEquipe;

	private List<Equipe> equipes;

	private List<Student> studentsEquipe;

	private Equipe selectedEquipe;

	private Equipe[] selectedEquipes;

	private FacadeAuth facadeAuth;

	private LoginBean login;
	
	private String nota;
	
	private Notas notaAluno;

	public ListEquipesBean() {

		facadeAuth = FacadeAuth.getInstance();

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");

		try {
		    
			
			if(null != login.getId_teacher()){
				equipes = facadeAuth.getEquipesTeacher(login.getSession(),
						login.getId_teacher());
			}else{
				equipes = facadeAuth.getEquipesTeacher(login.getSession(),
						login.getId_pessoal());
			}
			
			
	
		} catch (NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Equipe> getFilteredEquipe() {
		return filteredEquipe;
	}

	public void setFilteredEquipe(List<Equipe> filteredEquipe) {
		this.filteredEquipe = filteredEquipe;
	}

	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Equipe getSelectedEquipe() {
		return selectedEquipe;
	}

	public void setSelectedEquipe(Equipe selectedEquipe) {
		this.selectedEquipe = selectedEquipe;
	}

	public Equipe[] getSelectedEquipes() {
		return selectedEquipes;
	}

	public void setSelectedEquipes(Equipe[] selectedEquipes) {
		this.selectedEquipes = selectedEquipes;
	}

	public FacadeAuth getFacadeAuth() {
		return facadeAuth;
	}

	public void setFacadeAuth(FacadeAuth facadeAuth) {
		this.facadeAuth = facadeAuth;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}
	
	public void updateEquipe(){
		
		try {
			
			
			
			facadeAuth.updateEquipe(login.getSession(), selectedEquipe);
			
			if(null != selectedEquipe.getNota()){
				
				List<Student> l = facadeAuth.getStudentsEquipes(login.getSession(), String.valueOf(selectedEquipe.getId()));
				
				for (int i = 0; i < l.size(); i++) {
					
					notaAluno = facadeAuth.getNotasAll(login.getSession(),String.valueOf(selectedEquipe.getId_disciplina()), String.valueOf(l.get(i).getId()));
					
					if(null!= notaAluno){
						
						loadNotaEquipe(notaAluno);
						
						facadeAuth.updateNotas(notaAluno);
						
					}else{
						
					   notaAluno = new Notas();
					   
					   notaAluno.setId_aluno(l.get(i).getId());
					   
					   notaAluno.setId_disciplina(selectedEquipe.getId_disciplina());
					   
					   loadNotaEquipe(notaAluno);
					   
					   facadeAuth.CriarAvaliacaoAnual(notaAluno);

					   
					}
					
					
				
				}
				
				 
			}
			
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "NOTA DA EQUIPE ATUALIZADA");  
	            
	    	 RequestContext.getCurrentInstance().showMessageInDialog(message);
		
		} catch (NoSuchSessionException | NoSuchStudentException | SQLException e) {
			    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "ERRO AO ATUALIZAR NOTAS DA EQUIPE");  
	            
	    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
	    	    
			e.printStackTrace();
		}
	}
	
    public void deleteEquipe(){
		
    	try {
			
    		 facadeAuth.deleteEquipe(login.getSession(), selectedEquipe);
			
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "EQUIPE REMOVIDA");  
	            
	    	 RequestContext.getCurrentInstance().showMessageInDialog(message);
		} catch (NoSuchSessionException e) {
			   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "ERRO AO REMOVER EQUIPE");  
	            
	    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
		
			e.printStackTrace();
		}
		
	}
    
    public void gerarRelatorioEquipe(){
    	
    	try {
			String path = facadeAuth.gerarRelatorioEquipes(login.getSession(), selectedEquipe.getSerie(), 
					selectedEquipe.getGrau(), selectedEquipe.getTurma(),String.valueOf(selectedEquipe.getId_disciplina()), String.valueOf(selectedEquipe.getId_teacher()), selectedEquipe.getAno());
			DownloadRelatoriosUtil.fileDownloadController(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    public void deledeStudentEquipe(){
		
		
	}

	public void getStudentEquipe() {
		try {
			studentsEquipe = facadeAuth.getStudentsEquipes(login.getSession(),
					String.valueOf(selectedEquipe.getId()));
		} catch (NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the studentsEquipe
	 */
	public List<Student> getStudentsEquipe() {
		return studentsEquipe;
	}

	/**
	 * @param studentsEquipe the studentsEquipe to set
	 */
	public void setStudentsEquipe(List<Student> studentsEquipe) {
		this.studentsEquipe = studentsEquipe;
	}
	
	private BigDecimal aproximacao(BigDecimal valor){
        int decimalPlace = 1; 
        
        valor  = valor.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);  
		
		return valor;
		
	}
	
	
	private void loadNotaEquipe(Notas notasAluno) throws NoSuchSessionException{
		
		Equipe e = selectedEquipe;
		
		if(null != e){
			
			switch (e.getBimestre()) {
			
			case "1":
				
				notasAluno.setNota3_b1(e.getNota());
				
				
				break;
				
				
			case "2":
				
				notasAluno.setNota3_b2(e.getNota());
				
				
				break;
				
			case "3":

				notasAluno.setNota3_b3(e.getNota());


				break;

			case "4":

				notasAluno.setNota3_b3(e.getNota());


				break;

			default:
				break;
			}
		}
	}
}