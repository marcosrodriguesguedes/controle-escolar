package br.mod.escolar.control.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Teacher;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.UserTypeDefinition;


@ManagedBean
@ViewScoped
public class CreateNewTeacherBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    final static Logger logger = LoggerFactory.getLogger(CreateNewTeacherBean.class);
    
    private String nome;
    
    private String pai;
    
    private String mae;

    private String birthday;
    
    private String sexo;
    
    private String telefone;
    
    private String cpf;
    
    private String pis_pasep;
    
    private String titulo;
    
    private String rg;
    
    private String orgao_rg;
    
    private String banco;
    
    private String agencia;
    
    private String conta;
    
    private String endereco;
    
    private String bairro;
    
    private String complemento;
    
    private String cep;
    
    private String cidade;
    
    private String ufNaturality;
    
    private String email;
    
    private String lotacao;
    
    private Integer matricula;
    
    private String cidadefuncional;
    
    private Date dataContrato;
    
    private Date dataAdmisao;
    
    private FacadeAuth facadeAuth;
    
    private LoginBean login;
    
    private Teacher teacher;
    
    
    public CreateNewTeacherBean(){
     
    	facadeAuth = FacadeAuth.getInstance();
    	PropertyConfigurator.configure("log4j.properties");	
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
   	 	HttpSession session = (HttpSession) externalContext.getSession(true);  
   	 	login = (LoginBean)session.getAttribute("loginBean"); 
   	 
	
   
    }
    
    private static Date parseDate(String data) throws Exception {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
        try {  
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            date = (java.util.Date)formatter.parse(data);  
        } catch (Exception e) {              
            throw e;  
        }  
        return date;  
    }  
    
    
    public void createTeacher(ActionEvent actionEvent) throws Exception{
    	teacher = new Teacher();
    	
    	teacher.setName(nome);
    	
    	teacher.setNomeFather(pai);
    	
    	teacher.setMotherName(mae);
    	
    	try {
			teacher.setBirthday(parseDate(birthday));
   
		} catch (Exception e1) {
			logger.error("Erro no parse: "+e1.getMessage());
			e1.printStackTrace();
		} 	
    	
    	teacher.setCpf(cpf);
    	
    	teacher.setIdTeacher(matricula);
    	
    	teacher.setCity(cidade);
    	
    	teacher.setAddressLine(endereco);
    	
    	teacher.setDistrict(bairro);
    	
    	teacher.setAgencia(agencia);
    	
    	teacher.setAreaElection(titulo);
    	
    	teacher.setBanco(banco);
    	
    	teacher.setCep(cep);
    	
    	teacher.setCidadefuncional(cidadefuncional);
    	
    	teacher.setConta(conta);
    	
    	teacher.setDataContrato(dataContrato);
    	
    	teacher.setDateOfAdmission(dataAdmisao);
    	
    	teacher.setDataContrato(dataContrato);
    	
    	teacher.setGender(sexo);
    	
    	teacher.setEmail(email);
    	
    	teacher.setDddPhoneNumber(telefone);
    	
    	teacher.setNumberCTPS(pis_pasep);
    	
    	teacher.setComplementRg(orgao_rg);;
    	
    	teacher.setRg(rg);
    	
    	teacher.setComplement(complemento);
    	
    	teacher.setLotacao(lotacao);
    	
    	teacher.setBirthplace(ufNaturality);
    		
    	try {
    		
    		
			int id = facadeAuth.newTeacher(login.getSession(), teacher);
			
			User u = new User();
			
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
    		
            u.setLogin(cpf);
    		u.setPassword(format.format(parseDate(birthday)));
    		u.setIdType(Integer.parseInt(UserTypeDefinition.TEACHER));
    		u.setNameUser(nome);
    		u.setIdPerson(id);
    		u.setActive(new Integer(1));
    		
    		facadeAuth.createUser(login.getSession(), u);
    		
    		logger.debug("Novo Usuário criado :"+u.getNameUser());
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Professor Adicionado Com Sucesso");  
	          
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		
    	} catch (NoSuchSessionException | NoSuchPermissionException
				| NoSuchUserTypeException | InvalidParameterException | DBException e ) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", "Professor Nao Adicionado");  
	        logger.error("erro ao criar professor");  
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
			e.printStackTrace();
			
		}
    	
    	
    }
    
    
    public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public static Logger getLogger() {
		return logger;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPis_pasep() {
		return pis_pasep;
	}

	public void setPis_pasep(String pis_pasep) {
		this.pis_pasep = pis_pasep;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgao_rg() {
		return orgao_rg;
	}

	public void setOrgao_rg(String orgao_rg) {
		this.orgao_rg = orgao_rg;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUfNaturality() {
		return ufNaturality;
	}

	public void setUfNaturality(String ufNaturality) {
		this.ufNaturality = ufNaturality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getCidadefuncional() {
		return cidadefuncional;
	}

	public void setCidadefuncional(String cidadefuncional) {
		this.cidadefuncional = cidadefuncional;
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public Date getDataAdmisao() {
		return dataAdmisao;
	}

	public void setDataAdmisao(Date dataAdmisao) {
		this.dataAdmisao = dataAdmisao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    

}
