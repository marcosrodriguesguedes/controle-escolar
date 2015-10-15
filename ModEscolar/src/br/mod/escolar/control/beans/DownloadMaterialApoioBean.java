package br.mod.escolar.control.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

import br.mod.escolar.control.FacadeAuth;
import br.mod.escolar.model.entities.Documents;
import br.mod.escolar.model.entities.MaterialDeApoio;
import br.mod.escolar.model.exception.NoSuchSessionException;

@ManagedBean
@ViewScoped
public class DownloadMaterialApoioBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StreamedContent file;

    private TreeNode root;
    
    private TreeNode[] selectedNodes;
    
    private FacadeAuth fachada;
    
    private LoginBean login;
    
    public DownloadMaterialApoioBean(){
    	
    	ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		login = (LoginBean) session.getAttribute("loginBean");
    	
    	
    	fachada = FacadeAuth.getInstance();
    	
    	
    	root = new CheckboxTreeNode(new Documents("Arquivos","-", "Pasta"), null);
    	
    	TreeNode documents = new CheckboxTreeNode(new Documents("Documentos", "-", "Folder"), root);
    	
    	
    	
    	try {
			
    		List<MaterialDeApoio> list = fachada.getMaterialApoio(login.getSession());
    		
    		if(null!= list){
    			
    			for (int i = 0; i < list.size(); i++) {
    				
    				Documents d = new Documents(list.get(i).getUrlArquivo(),"--", list.get(i).getNucleo());
    				
    				@SuppressWarnings("unused")
					TreeNode expenses = new CheckboxTreeNode("document",d, documents);
    			}
    		}
			
			
	
    	} catch (NoSuchSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    }
    
    
    public void fileDownloadController() {     
    	
    	Documents d = ((Documents) selectedNodes[0].getData());
        
    	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/documentos/"+d.getDescrypt());
        file = new DefaultStreamedContent(stream, "image/docx/rar", d.getDescrypt());
        
       
    }

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return the selectedNodes
	 */
	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	/**
	 * @param selectedNodes the selectedNodes to set
	 */
	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	/**
	 * @return the fachada
	 */
	public FacadeAuth getFachada() {
		return fachada;
	}

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(FacadeAuth fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the login
	 */
	public LoginBean getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(LoginBean login) {
		this.login = login;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
	
	
}
