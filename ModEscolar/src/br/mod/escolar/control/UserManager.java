package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.User;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchUserException;
import br.mod.escolar.model.util.Base64Coder;
import br.mod.escolar.model.util.HibernateUtil;

public class UserManager {

	private static UserManager manager = null;

	public enum UserInfo {
		ID_TYPE("idType"), ID_PERSON("idPerson"), ID("id"), LOGIN("login"), PASSWORD(
				"password"), STATUS("active");
		private String description;

		private UserInfo(final String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	private UserManager() {
	
	}

	public static synchronized UserManager getInstance() {
		if (manager == null) {
			manager = new UserManager();
		}
		return manager;
	}

	public int createUser(User u) throws InvalidParameterException, DBException {
		String login = u.getLogin();
		String password = Base64Coder.encodeString(u.getPassword());
		u.setPassword(password);
		if (getUser(login) != null){
			
			throw new InvalidParameterException("O usuário com login \"" + login + "\" já existe.");
		
		}else{
			
			return (Integer) HibernateUtil.create(u);
		}
			
		
	}
	
	public void updateUser(User u) {
		String password = Base64Coder.encodeString(u.getPassword());
		u.setPassword(password);
		HibernateUtil.update(u);
	}
	
	public void removeUser(String login) throws NoSuchUserException, DBException {
		User u = getUser(login);
		if (u == null)
			throw new NoSuchUserException();
		HibernateUtil.delete(u);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<User> result = session.createSQLQuery(
				"select * from usuarios order by login").addEntity("t", User.class).list();
		session.getTransaction().commit();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public User getUser(String login) throws DBException {
		HibernateUtil.getSession();
	    Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("login", login));
		List<User> user = null;
		try {
			user = (List<User>) HibernateUtil.createQuery(User.class, exps);
		} catch (ConstraintViolationException e) {
			throw new DBException("Ocorreu um erro no processamento da sua requisicao.");
		}

		return user.isEmpty() ? null : user.get(0);
	}

	public String getUserInfo(String login, UserInfo info) throws NoSuchUserException, DBException {
		if (getUser(login) == null)
			throw new NoSuchUserException();
		switch (info) {
		case ID_TYPE:
			return String.valueOf(getUser(login).getIdType());
		case ID_PERSON:
			return String.valueOf(getUser(login).getIdPerson());
		case ID:
			return String.valueOf(getUser(login).getId());
		case LOGIN:
			return getUser(login).getLogin();
		case PASSWORD:
			return getUser(login).getPassword();		
		case STATUS:
			return String.valueOf(getUser(login).getActive());	
		default:
			return new String();
		}
	}

}
