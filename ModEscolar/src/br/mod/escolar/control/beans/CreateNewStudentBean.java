package br.mod.escolar.control.beans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.mod.escolar.control.ConfigManager;
import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.control.TurmasManager;
import br.mod.escolar.model.entities.EscolarConfig;
import br.mod.escolar.model.entities.ParentalInfo;
import br.mod.escolar.model.entities.Student;
import br.mod.escolar.model.entities.TurmasConfig;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.exception.NoSuchSessionException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.UserTypeDefinition;

@ManagedBean
@ViewScoped
public class CreateNewStudentBean {
	
	final static Logger logger = LoggerFactory.getLogger(CreateNewStudentBean.class);

    private String name;

    private String periody;

    private String yearStundent;

    private String studentScore;

    private String gen;

    private String race;

    private String birthday;

    private String marialState;

    private String birthplace;

    private String ufNaturality;

    private String civilCertificate;

    private String book;

    private String sheet;

    private String rgNumber;

    private String rgRegistration;

    private String stateRg;

    private String numberVoting;

    private String session;

    private String zone;

    private String stateVonting;

    private String ctps;

    private String reservist;

    private String nameFather;

    private boolean fatherlife;

    private boolean fatherNotlife;

    private String nameMather;

    private boolean nameMatherlive;

    private boolean nameMatherNotlive;

    private String previdency;

    private String nBrother;

    private String nBrotherBag;

    private String profession;

    private String businessAddress;

    private String ufbusiness;

    private String country;

    private String fone;

    private String email;

    private String nameResponsability;

    private String ageResponsability;

    private String countryResponsability2;

    private String ufResponsability;

    private String foneResponsability;

    private String parentyResponsability;

    private String familyIncome;

    private String formationScoreResponsability;

    private String profissionResponsability;

    private String adressResponsability;

    private String countryResponsability;

    private String teachingUnit;

    private String adressTeachingUnit;

    private String periodyTeachingUnit;

    private String yearTeachingUnit;

    private String scoreTeachingUnit;

    private String countryTeachingUnit;

    private String ufTeachingUnit;

    private String foneTeachingUnit;

    private String fisicyEducation;

    private String sport;

    private String cultura;

    private String arts;

    private String outh;

    private boolean rural;

    private boolean urbano;

    private boolean transportationYes;

    private boolean transportationNo;

    private boolean libraryYes;

    private boolean libraryNo;

    private String sessionId;

    private String urlImage;

    private String studentGrade;

    private String studentShift;

    private StreamedContent imagem;

    private FacadeAuth facadeAuth;
    
    private TurmasManager turmasManaget;
    
    private ConfigManager managerConfig;

    private Student student;
    
    private String active;
    
    public static int TIPO_STUDENT = 12;

    public CreateNewStudentBean() {
    	
    	 PropertyConfigurator.configure("log4j.properties");
    	
    	 this.facadeAuth = FacadeAuth.getInstance();
    	 this.turmasManaget = TurmasManager.getInstance();
         this.managerConfig = ConfigManager.getInstance();
    	 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
    	 HttpSession session = (HttpSession) externalContext.getSession(true);  
    	 LoginBean sessaoBean = (LoginBean )session.getAttribute("loginBean");
    	 logger.debug("Usuario:"+sessaoBean.getUsername());
    	 this.student = new Student();
		
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

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public boolean isFatherlife() {
        return fatherlife;
    }

    public void setFatherlife(boolean fatherlife) {
        this.fatherlife = fatherlife;
    }

    public boolean isFatherNotlife() {
        return fatherNotlife;
    }

    public void setFatherNotlife(boolean fatherNotlife) {
        this.fatherNotlife = fatherNotlife;
    }

    public String getUfResponsability() {
        return ufResponsability;
    }

    public void setUfResponsability(String ufResponsability) {
        this.ufResponsability = ufResponsability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriody() {
        return periody;
    }

    public void setPeriody(String periody) {
        this.periody = periody;
    }

    public String getYearStundent() {
        return yearStundent;
    }

    public void setYearStundent(String yearStundent) {
        this.yearStundent = yearStundent;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
    @Temporal(TemporalType.TIMESTAMP)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarialState() {
        return marialState;
    }

    public void setMarialState(String marialState) {
        this.marialState = marialState;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getUfNaturality() {
        return ufNaturality;
    }

    public void setUfNaturality(String ufNaturality) {
        this.ufNaturality = ufNaturality;
    }

    public String getCivilCertificate() {
        return civilCertificate;
    }

    public void setCivilCertificate(String civilCertificate) {
        this.civilCertificate = civilCertificate;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getRgNumber() {
        return rgNumber;
    }

    public void setRgNumber(String rgNumber) {
        this.rgNumber = rgNumber;
    }

    public String getRgRegistration() {
        return rgRegistration;
    }

    public void setRgRegistration(String rgRegistration) {
        this.rgRegistration = rgRegistration;
    }

    public String getStateRg() {
        return stateRg;
    }

    public void setStateRg(String stateRg) {
        this.stateRg = stateRg;
    }

    public String getNumberVoting() {
        return numberVoting;
    }

    public void setNumberVoting(String numberVoting) {
        this.numberVoting = numberVoting;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStateVonting() {
        return stateVonting;
    }

    public void setStateVonting(String stateVonting) {
        this.stateVonting = stateVonting;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getReservist() {
        return reservist;
    }

    public String getUfbusiness() {
        return ufbusiness;
    }

    public void setUfbusiness(String ufbusiness) {
        this.ufbusiness = ufbusiness;
    }

    public void setReservist(String reservist) {
        this.reservist = reservist;
    }

    public String getNameFather() {
        return nameFather;
    }

    public void setNameFather(String nameFather) {
        this.nameFather = nameFather;
    }

    public String getNameMather() {
        return nameMather;
    }

    public void setNameMather(String nameMather) {
        this.nameMather = nameMather;
    }

    public boolean isNameMatherlive() {
        return nameMatherlive;
    }

    public void setNameMatherlive(boolean nameMatherlive) {
        this.nameMatherlive = nameMatherlive;
    }

    public boolean isNameMatherNotlive() {
        return nameMatherNotlive;
    }

    public void setNameMatherNotlive(boolean nameMatherNotlive) {
        this.nameMatherNotlive = nameMatherNotlive;
    }

    public String getPrevidency() {
        return previdency;
    }

    public void setPrevidency(String previdency) {
        this.previdency = previdency;
    }

    public String getnBrother() {
        return nBrother;
    }

    public void setnBrother(String nBrother) {
        this.nBrother = nBrother;
    }

    public String getnBrotherBag() {
        return nBrotherBag;
    }

    public void setnBrotherBag(String nBrotherBag) {
        this.nBrotherBag = nBrotherBag;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameResponsability() {
        return nameResponsability;
    }

    public void setNameResponsability(String nameResponsability) {
        this.nameResponsability = nameResponsability;
    }

    public String getAgeResponsability() {
        return ageResponsability;
    }

    public void setAgeResponsability(String ageResponsability) {
        this.ageResponsability = ageResponsability;
    }

    public String getCountryResponsability2() {
        return countryResponsability2;
    }

    public void setCountryResponsability2(String countryResponsability2) {
        this.countryResponsability2 = countryResponsability2;
    }

    public String getFoneResponsability() {
        return foneResponsability;
    }

    public void setFoneResponsability(String foneResponsability) {
        this.foneResponsability = foneResponsability;
    }

    public String getParentyResponsability() {
        return parentyResponsability;
    }

    public void setParentyResponsability(String parentyResponsability) {
        this.parentyResponsability = parentyResponsability;
    }

    public String getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(String familyIncome) {
        this.familyIncome = familyIncome;
    }

    public String getFormationScoreResponsability() {
        return formationScoreResponsability;
    }

    public void setFormationScoreResponsability(
            String formationScoreResponsability) {
        this.formationScoreResponsability = formationScoreResponsability;
    }

    public String getProfissionResponsability() {
        return profissionResponsability;
    }

    public void setProfissionResponsability(String profissionResponsability) {
        this.profissionResponsability = profissionResponsability;
    }

    public String getAdressResponsability() {
        return adressResponsability;
    }

    public void setAdressResponsability(String adressResponsability) {
        this.adressResponsability = adressResponsability;
    }

    public String getCountryResponsability() {
        return countryResponsability;
    }

    public void setCountryResponsability(String countryResponsability) {
        this.countryResponsability = countryResponsability;
    }

    public String getTeachingUnit() {
        return teachingUnit;
    }

    public void setTeachingUnit(String teachingUnit) {
        this.teachingUnit = teachingUnit;
    }

    public String getAdressTeachingUnit() {
        return adressTeachingUnit;
    }

    public void setAdressTeachingUnit(String adressTeachingUnit) {
        this.adressTeachingUnit = adressTeachingUnit;
    }

    public String getPeriodyTeachingUnit() {
        return periodyTeachingUnit;
    }

    public void setPeriodyTeachingUnit(String periodyTeachingUnit) {
        this.periodyTeachingUnit = periodyTeachingUnit;
    }

    public String getYearTeachingUnit() {
        return yearTeachingUnit;
    }

    public void setYearTeachingUnit(String yearTeachingUnit) {
        this.yearTeachingUnit = yearTeachingUnit;
    }

    public String getScoreTeachingUnit() {
        return scoreTeachingUnit;
    }

    public void setScoreTeachingUnit(String scoreTeachingUnit) {
        this.scoreTeachingUnit = scoreTeachingUnit;
    }

    public String getCountryTeachingUnit() {
        return countryTeachingUnit;
    }

    public void setCountryTeachingUnit(String countryTeachingUnit) {
        this.countryTeachingUnit = countryTeachingUnit;
    }

    public String getUfTeachingUnit() {
        return ufTeachingUnit;
    }

    public void setUfTeachingUnit(String ufTeachingUnit) {
        this.ufTeachingUnit = ufTeachingUnit;
    }

    public String getFoneTeachingUnit() {
        return foneTeachingUnit;
    }

    public void setFoneTeachingUnit(String foneTeachingUnit) {
        this.foneTeachingUnit = foneTeachingUnit;
    }

    public String getFisicyEducation() {
        return fisicyEducation;
    }

    public void setFisicyEducation(String fisicyEducation) {
        this.fisicyEducation = fisicyEducation;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public String getArts() {
        return arts;
    }

    public void setArts(String arts) {
        this.arts = arts;
    }

    public String getOuth() {
        return outh;
    }

    public void setOuth(String outh) {
        this.outh = outh;
    }

    public boolean getRural() {
        return rural;
    }

    public void setRural(boolean rural) {
        this.rural = rural;
    }

    public boolean getUrbano() {
        return urbano;
    }

    public void setUrbano(boolean urbano) {
        this.urbano = urbano;
    }

    public boolean getTransportationYes() {
        return transportationYes;
    }

    public void setTransportationYes(boolean transportationYes) {
        this.transportationYes = transportationYes;
    }

    public boolean getTransportationNo() {
        return transportationNo;
    }

    public void setTransportationNo(boolean transportationNo) {
        this.transportationNo = transportationNo;
    }

    public boolean getLibraryYes() {
        return libraryYes;
    }

    public void setLibraryYes(boolean libraryYes) {
        this.libraryYes = libraryYes;
    }

    public boolean getLibraryNo() {
        return libraryNo;
    }

    public String getStudentShift() {
        return studentShift;
    }

    public void setStudentShift(String studentShift) {
        this.studentShift = studentShift;
    }

    public void setLibraryNo(boolean libraryNo) {
        this.libraryNo = libraryNo;
    }

    public FacadeAuth getFacadeAuth() {
        return facadeAuth;
    }

    public void setFacadeAuth(FacadeAuth facadeAuth) {
        this.facadeAuth = facadeAuth;
    }

   
    
    public void fileUploadAction(FileUploadEvent event) {
        try {
           

            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
           
            String realPath = context.getRealPath("/");
            
            System.out.println(realPath);
 
 // Aqui cria o diretorio caso não exista
            File file = new File(realPath + "/fotos/");
            file.mkdirs();
            
            UploadedFile arq = event.getFile();
          	 
        	InputStream in = new BufferedInputStream(arq.getInputstream());
        	
        	byte[] arquivo = IOUtils.toByteArray(in);
            
        	String caminho = realPath + "/fotos/" + event.getFile().getFileName();    
      
 // esse trecho grava o arquivo no diretório
        	
        	
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            
            student.setPictureUrl(event.getFile().getFileName());
            

        } catch (Exception ex) {
            System.out.println("Erro no upload de imagem" + ex);
        }
    }
    
    
          
    

    public void createStudent(ActionEvent actionEvent) throws Exception {

        UIParameter parameter = (UIParameter) actionEvent.getComponent().findComponent("idsession");

        sessionId = parameter.getValue().toString();

        FacesContext context = FacesContext.getCurrentInstance();

        
        student.setName(name);

        student.setBook(book);
        //Registro de Nascimento
        student.setCivilCertificate(civilCertificate);

        student.setPeriody(periody);
        //Serie
        student.setGrade(studentGrade);
        //Nivel de ensino
        student.setGrade2(studentScore);
        //Raca
        student.setRace(race);
        //Turno
        student.setShift(studentShift);
        //Sexo
        student.setGender(gen);
        //Naturalidade
        student.setBirthplace(birthplace);
       
        // naturalidade (estado)
        student.setState(ufNaturality);
        //Folha
        student.setSheet(sheet);
        //RG
        student.setRg(rgNumber);
        //Estado Emissor RG
        student.setStateRg(stateRg);
        //Orgao Emissor
        student.setComplementRg(rgRegistration);
        //Titulo de eleitor
        student.setNumberVoting(numberVoting);
        //Zona
        student.setAreaElection(zone);
        //Secao
        student.setSection(session);
        //Estado do titulo
        student.setStateOfTitulo(stateVonting);
        //Carteira de Trabalho
        student.setCtps(ctps);
        //Reservista
        student.setReservista(reservist);
        //Aniversario
        try {
			student.setBirthday(parseDate(birthday));
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
			logger.error("Erro ao realizar parser: Data de nascimento Aluno:"+ e1.getMessage());
			
		}
        //
        student.setUnidadeDeEnsinoOriundo(teachingUnit);
        
        student.setUfUnidadeDeEnsinoOriundo(ufTeachingUnit);
        
        if(libraryYes){
        	
        	student.setUtilizaBicicleta(true);
        	
        }else{
        	 
        	student.setUtilizaBicicleta(false);
        }
        //Nao utiliza bicicleta como transporte
        if(libraryNo){
        	
        	student.setNutilizaBicicleta(true);
        }else{
        	
        	student.setNutilizaBicicleta(false);
        }
        
        student.setTelefoneUnidadeDeEnsinoOriundo(foneTeachingUnit);
        //Ano LEtivo
        student.setAnoLetivo(yearStundent);
        //Estado Civil
        student.setEstadoCivil(marialState);
        //Pai vivo
        if(fatherlife){
        	
        	student.setPaiVivo(true);
        	
        }else{
        	 
        	student.setPaiVivo(false);
        }
        //Pai nao vivo
        if(fatherNotlife){
        	
        	student.setPaiNaoVivo(true);
        }else{
        	
        	student.setPaiNaoVivo(false);
        }
        
        if(transportationYes){
        	
        	student.setUtilizaTransporteEscolar(true);
        	
        }else{
        	 
        	student.setUtilizaTransporteEscolar(false);
        }
        
        if(transportationNo){
        	
        	student.setnUtilizaTransporteEscolar(true);
        }else{
        	
        	student.setnUtilizaTransporteEscolar(false);
        }
       
        //Mae viva
        if(nameMatherlive){
        	
        	student.setMaeViva(true);
       
        }else{
        	
        	student.setMaeViva(false);
        }
       
        //Mae nao viva
        if(nameMatherNotlive){
        	
        	student.setMaeNaoViva(true);
        
        }else{
        	
        	student.setMaeNaoViva(false);
        }
        //Orgao previdenciario
        student.setPrevidencia(previdency);
        //numero de irmaos
        student.setNumeroDeIrmaosEstudantes(nBrother);
        //numero de irmaos bolsitas
        student.setNumeroDeIrmaosEstudantesBolsistas(nBrotherBag);
        //Profissao Resp
        student.setProfissao(profession);
        //End profissional do resp
        student.setEndProfissao(businessAddress);
        //municipio profissional do resp
        student.setMunicioProfissao(country);
        //estado profissional do resp
        student.setEstadoProfissao(ufbusiness);
        //telefone
        student.setPhoneNumber(fone);
        //email
        student.setEmail(email);
        //nome do resp
        student.setNomeResponsavel(nameResponsability);
        //idade do resp
        student.setIdadeReponsavel(ageResponsability);
        //estado do resp
        student.setEstadoReponsavel(ufResponsability);
        //telefona resp
        student.setTelefoneReponsavel(foneResponsability);
        //Grau de Parentesco
        student.setParentyScoreResponsability(parentyResponsability);
        //Renda familiar
        student.setRendaFamiliarReponsavel(familyIncome);
        //nivel de formacao do resp
        student.setFormationScoreResponsability(formationScoreResponsability);
        //profissao resp
        student.setProfissaoReponsavel(profissionResponsability);
        //endereco do resp
        student.setEndProfissionalReponsavel(adressResponsability);
        //Municipio dos pais
        student.setMunicipioPais(country);
        //Municipio resp
        student.setMunicipioReponsavel(countryResponsability);
        
        student.setUnidadeDeEnsinoOriundo(teachingUnit);
        
        student.setEnderecoUnidadeDeEnsinoOriundo(adressTeachingUnit);
        
        student.setAnoUnidadeDeEnsinoOriundo(yearTeachingUnit);
        
        student.setPeriodoUnidadeDeEnsinoOriundo(periodyTeachingUnit);
        
        student.setNivelUnidadeDeEnsinoOriundo(scoreTeachingUnit);
        
        student.setMunicipioUnidadeDeEnsinoOriundo(countryTeachingUnit);
        
        student.setUfUnidadeDeEnsinoOriundo(ufTeachingUnit);
        
        student.setTelefoneUnidadeDeEnsinoOriundo(foneTeachingUnit);
        
        student.setDadosEducacaoFisica(fisicyEducation);
        
        student.setCultura(cultura);
        
        student.setEsporte(sport);
        
        student.setArte(arts);
        
        student.setOutras(outh);
        
        if(urbano){
        	
        	student.setUrbano(true);
        }else{
        	student.setUrbano(false);
        }
        
        if(rural){
        	
        	student.setRual(true);
        }else{
        	
        	student.setRual(false);
        }
        
        student.setActive(active);
        //Nome Pai(Filiacao)
        student.setNomePai(nameFather);
        //Nome Mae(Filiacao)
        student.setNomeMae(nameMather);
        
        
        ParentalInfo parental = new ParentalInfo();

        parental.setFatherName(nameFather);

        parental.setMotherName(nameMather);
        
      
      

        try {
        	
        	 SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
        	
        	List<TurmasConfig> turmaCadastrada = turmasManaget.getTurma(formatAno.format(new Date()),student.getGrade(), student.getGrade2()); 
        	
        	if(null == turmaCadastrada){
        		
        		TurmasConfig turma = new TurmasConfig();
        		turma.setAno(formatAno.format(new Date()));
        		turma.setGrau(student.getGrade2());
        		turma.setSerie(student.getGrade());
        		turma.setNumeroAlunoAtual(1);
        		EscolarConfig e = managerConfig.getEscolarConfig(String.valueOf(ConfigManager.NUMBER_STUDENT_CLASS));
        		
        		if(null == e){
        			
        			EscolarConfig ec = new EscolarConfig();
        			ec.setCodConfig(ConfigManager.NUMBER_STUDENT_CLASS);
        			ec.setValor(35);
        			HibernateUtil.create(ec);
        			e = ec;
        		}
        		turma.setNumeroMaxAlunosTurma(e.getValor());
        		turma.setTurma("A");
        		turmasManaget.createTurma(turma);
        		student.setClassId(turma.getTurma());
        		
        		
        	}else{
        		
        		boolean adicionadoATurma = false;
        		
        		String ultimaTurma = "";
        		
        		for (int i = 0; i < turmaCadastrada.size(); i++) {
					
        			if(turmaCadastrada.get(i).getNumeroAlunoAtual() < turmaCadastrada.get(i).getNumeroMaxAlunosTurma()){
        				
        				student.setClassId(turmaCadastrada.get(i).getTurma());
        				
        				turmaCadastrada.get(i).setNumeroAlunoAtual(turmaCadastrada.get(i).getNumeroAlunoAtual()+1);
        				
        				adicionadoATurma = true;
        				
        				turmasManaget.updateTurma(turmaCadastrada.get(i));
        				
        				break;
        			
        			}
        			
        		 ultimaTurma = turmaCadastrada.get(i).getTurma();
				
        		}
        		
        		if(!adicionadoATurma){
        			
        			TurmasConfig turma = new TurmasConfig();
            		turma.setAno(formatAno.format(new Date()));
            		turma.setGrau(student.getGrade2());
            		turma.setSerie(student.getGrade());
            		turma.setNumeroAlunoAtual(1);
            		
            		EscolarConfig e = managerConfig.getEscolarConfig(String.valueOf(ConfigManager.NUMBER_STUDENT_CLASS));
            		
            		if(null == e){
            			
            			EscolarConfig ec = new EscolarConfig();
            			ec.setCodConfig(ConfigManager.NUMBER_STUDENT_CLASS);
            			ec.setValor(35);
            			HibernateUtil.create(ec);
            			e = ec;
            		}
            		
            		turma.setNumeroMaxAlunosTurma(e.getValor());
            		turma.setTurma(turmasManaget.getCaractereTurma(ultimaTurma));
            		turmasManaget.createTurma(turma);
            		student.setClassId(turma.getTurma());
            		
        			
        		}
        		
        	}
        	
        	
            int id = facadeAuth.newStudent(sessionId, student, parental, null);
            
            logger.debug("Novo Aluno criado: "+ student.getName());
            
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
           
            User u = new User();
    		
            u.setLogin(rgNumber);
    		u.setPassword(format.format(parseDate(birthday)));
    		u.setIdType(Integer.parseInt(UserTypeDefinition.STUDENT));
    		u.setNameUser(name);
    		u.setIdPerson(id);
    		u.setActive(new Integer(active));
    
    		facadeAuth.createUser(sessionId, u);
    		
    		logger.debug("Novo Usu�rio criado: "+ u.getNameUser());
    		

            context.addMessage(null, new FacesMessage("Successo",
                    "Aluno cadastrado no sistema: " + student.getName()));
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "ALUNO : "+ student.getName().toUpperCase() +" ADICIONADO AO SISTEMA");  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
			

        } catch (NoSuchSessionException | NoSuchPermissionException
                | NoSuchUserTypeException | InvalidParameterException | DBException e) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "ERRO AO SALVAR O ALUNO: "+student.getName().toUpperCase()+" : "+e.getMessage());  
            
    	    RequestContext.getCurrentInstance().showMessageInDialog(message); 
            
    	    e.printStackTrace();
            
            logger.error("Erro ao adicionar Aluno," + e.getMessage());
            
        }

    }

    public StreamedContent getImagem() {
       
       return imagem;
    }

    public void setImagem(StreamedContent imagem) {
       
        this.imagem = imagem;
        
        
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
