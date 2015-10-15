package br.mod.escolar.control;

import java.util.List;

import org.hibernate.Session;

import br.mod.escolar.model.entities.EscolarConfig;
import br.mod.escolar.model.util.HibernateUtil;

public class ConfigManager {
	
	public static Integer NUMBER_STUDENT_CLASS = 1;
	
	private static ConfigManager manager = null;
	
	private  ConfigManager() {
		
	}
	
	public static synchronized ConfigManager getInstance() {
		if (manager == null) {
			manager = new ConfigManager();
		}
		return manager;
	}

	
	@SuppressWarnings("unchecked")
	public EscolarConfig getEscolarConfig(String id_config) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<EscolarConfig> result = session
				.createSQLQuery(
						"select "
								+ "{c.*} "
								+ "from escolar_config {c} where {c}.codConfig = '"
								+ id_config + "'" + ";")

				.addEntity("c", EscolarConfig.class).list();
		session.getTransaction().commit();
		return result.isEmpty() ? null : result.get(0);
	}
	
	
	public void updateConfig(String id_config, String value){
		
		EscolarConfig ec = getEscolarConfig(id_config);
		
		ec.setValor(new Integer(value));
		
		HibernateUtil.update(ec);
	}

}

