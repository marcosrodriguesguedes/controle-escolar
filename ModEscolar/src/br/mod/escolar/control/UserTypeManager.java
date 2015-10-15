package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;

import br.mod.escolar.model.entities.UserType;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchUserTypeException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class UserTypeManager {

	public enum UserTypeInfo {
		ID("id"), TYPE("type"), DESCRIPTION("description"), PAGE("page");

		private String descricao;

		private UserTypeInfo(final String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}

	}

	private static UserTypeManager manager = null;

	private UserTypeManager() {

	}

	public static synchronized UserTypeManager getInstance() {
		if (manager == null) {
			manager = new UserTypeManager();
		}
		return manager;
	}

	public int createUserType(int id, String type, String description,
			String page) throws Exception {
		validate(type, description, page);

		UserType t = new UserType(id, type, description, page);
		return (Integer) HibernateUtil.create(t);

	}

	public static void validate(String type, String description, String page)
			throws InvalidParameterException {

		if (!Validator.isStringValid(type))
			throw new InvalidParameterException(Messages.INVALID_NAME_USER);
		if (!Validator.isStringValid(description))
			throw new InvalidParameterException(
					Messages.INVALID_DESCRIPTION_USER);
		if (!Validator.isStringValid(page))
			throw new InvalidParameterException(Messages.INVALID_PAGE_USER);

	}

	@SuppressWarnings("unchecked")
	public UserType getUserType(int id) throws NoSuchUserTypeException {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("id", id));
		List<UserType> user = null;
		try {
			user = (List<UserType>) HibernateUtil.createQuery(UserType.class,
					exps);
		} catch (ConstraintViolationException e) {
			throw new NoSuchUserTypeException();
		}

		return user.isEmpty() ? null : user.get(0);
	}

	public String getUserTypeInfo(int id, UserTypeInfo info)
			throws NoSuchUserTypeException {
		if (getUserType(id) == null)
			throw new NoSuchUserTypeException();
		switch (info) {
		case TYPE:
			return getUserType(id).getType();
		case DESCRIPTION:
			return getUserType(id).getDescription();
		case PAGE:
			return getUserType(id).getPage();
		default:
			return new String();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserType> getUserType(UserTypeInfo info, String value)
			throws DBException {

		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		if (info.equals(UserTypeInfo.TYPE)) {
			exps.add(Restrictions.like(info.getDescricao(), value,
					MatchMode.ANYWHERE));
		} else if (info.equals(UserTypeInfo.DESCRIPTION)) {
			exps.add(Restrictions.like(info.getDescricao(), value));
		}

		List<UserType> user = null;
		try {
			user = (List<UserType>) HibernateUtil.createQuery(UserType.class,
					exps);
		} catch (ConstraintViolationException e) {
			throw new DBException(
					"A busca ocasionou uma violação nas regras de integridade do banco de dados.");
		}

		return user;
	}

	public UserType removeUserType(int id) throws NoSuchUserTypeException {

		UserType d = getUserType(id);
		HibernateUtil.delete(d);
		return d;
	}

	public void updateUserType(int id, String type, String description,
			String page) throws InvalidParameterException,
			NoSuchUserTypeException {
		UserType d = null;
		if (Validator.isNumberValid(id)) {
			d = getUserType(id);
			validate(type, description, page);

		} else {
			throw new InvalidParameterException(Messages.INVALID_USER_TYPE_ID);
		}

		if (d == null) {
			throw new NoSuchUserTypeException();
		}

		d.setId(id);
		d.setType(type);
		d.setDescription(description);
		d.setPage(page);

		HibernateUtil.update(d);
	}

	@SuppressWarnings("unchecked")
	public List<UserType> getAllUserTypes() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<UserType> result = session.createSQLQuery(
				"select " + "{u.*} " + "from evl_tipos_usuarios {u} "
						+ " order by  {u}.descricao;").addEntity("u",
				UserType.class).list();
		session.getTransaction().commit();
		return result;
	}

}
