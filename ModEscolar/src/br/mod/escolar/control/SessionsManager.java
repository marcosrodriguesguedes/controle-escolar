package br.mod.escolar.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.mod.escolar.model.entities.Sessions;
import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.util.HibernateUtil;

public class SessionsManager {

	private static SessionsManager manager = null;
	private Map<String, User> sessions;

	public synchronized Map<String, User> getSessions() {
		return sessions;
	}

	/**
	 * 
	 * Evita a instânciação da classe, garantindo que uma única instância
	 * do gerenciador estará em execução.
	 */
	private SessionsManager() {
		this.sessions = new HashMap<String, User>();
	}

	/**
	 * Retorna uma referência para esta instância do gerenciador de sessões
	 * 
	 * @return instancia
	 */
	public static SessionsManager getInstance() {
		if (manager == null) {
			manager = new SessionsManager();
		}
		return manager;
	}

	/**
	 * adiciona uma sessão ao gerenciador
	 * 
	 * @param session
	 */
	public synchronized void addSession(String idSession, User user) {
		sessions.put(idSession, user);

	}

	/**
	 * retorna uma sessão gerenciada, a partir da sua id de sessão
	 * 
	 * @param sessionID
	 * @return
	 */
	public synchronized User getUserOfSession(String sessionID) {
		return sessions.get(sessionID);
	}

	public synchronized void createSession(Sessions e) {
		HibernateUtil.create(e);
	}

	/**
	 * remove uma sessão do gerenciamento desta instância
	 * 
	 * @param sessionID
	 *            a id da sessão a ser retirada do gerenciador
	 */
	public synchronized void removeSession(String sessionID) {
		sessions.remove(sessionID);
	}

	public synchronized boolean existSession(String sessionID) {
		return sessions.containsKey(sessionID);
	}

	public synchronized long getNumberOfSessions() {
		return sessions.size();
	}

	public synchronized Map<String, User> getAllSessions() {
		return sessions;
	}

	@SuppressWarnings("unchecked")
	public List<Sessions> getSessionsUser(String id_user, String status) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<Sessions> result = session
				.createSQLQuery(
						"select " + "{s.*} "
								+ "from session {s}  where {s}.id_user = '"
								+ id_user + "'" + " and {s}.ativa = '" + status
								+ "'").addEntity("s", Sessions.class).list();
		session.getTransaction().commit();

		return result.isEmpty() ? null : result;
	}

	public void updateSessionToInitivo(String sessionID, String id_user,
			String status) {

		List<Sessions> sessionsAtivas = getSessionsUser(id_user, status);

		if (null != sessionsAtivas) {
			for (int i = 0; i < sessionsAtivas.size(); i++) {
				if (!sessionsAtivas.get(i).getSessionID().equals(sessionID)) {

					sessionsAtivas.get(i).setAtiva("I");

					removeSession(sessionsAtivas.get(i).getSessionID());

					HibernateUtil.update(sessionsAtivas.get(i));

				}
			}

		}

	}

	public synchronized void cleanAllSessionsOfUser(User user) {
		for (Map.Entry<String, User> entry : sessions.entrySet()) {
			if (entry.getValue().equals(user)) {
				sessions.remove(entry.getKey());
			}
		}
		// Iterator it = sessions.entrySet().iterator();
		// while (it.hasNext()) {
		// Map.Entry<String, User> pairs = (Map.Entry<String, User>)it.next();
		// if(pairs.getValue().equals(user)){
		// sessions.remove(pairs.getKey());
		// }
		// }
	}
	
	public synchronized boolean SessaoExpirada(Date dataInsercao){
		
		 Date dataAtual = new Date();
		 
		
		 long difMinutos = (dataAtual.getTime() - dataInsercao.getTime()) / (60 * 1000);
		 
		 if(difMinutos > 1){
			 
			 return true;
		 }
		
		return false;
		
	}

}
