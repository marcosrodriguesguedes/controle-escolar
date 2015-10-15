package br.mod.escolar.control;

import java.util.List;

import org.hibernate.Session;

import br.mod.escolar.model.entities.MaterialDeApoio;
import br.mod.escolar.model.util.HibernateUtil;

public class MaterialDeApoioManager {
	
	private static MaterialDeApoioManager manager = null;
	
	private MaterialDeApoioManager(){
		
	}
	
	public static synchronized MaterialDeApoioManager getInstance(){
		
		if (manager == null) {
			manager = new MaterialDeApoioManager();
		}
		return manager;
		
	}
	
    public int createMaterialApoio(MaterialDeApoio m){
		
		return (Integer) HibernateUtil.create(m);
	}
	
    @SuppressWarnings("unchecked")
	public List<MaterialDeApoio> getMaterialApoio() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<MaterialDeApoio> result = session.createSQLQuery(
				"select " + "{m.*} " + "from material_apoio {m};").addEntity("m", MaterialDeApoio.class).list();
		session.getTransaction().commit();
		
		return result.isEmpty() ? null : result;
	}

}
