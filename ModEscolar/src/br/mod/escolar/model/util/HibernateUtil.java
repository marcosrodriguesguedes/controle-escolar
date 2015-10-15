package br.mod.escolar.model.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	final static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory;
//	private static final SchemaExport schemaExport;
	private static final SchemaUpdate schemaUpdate;

	static {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration();

			sessionFactory = cfg.configure()
					.buildSessionFactory();
//			schemaExport = new SchemaExport(cfg);
			schemaUpdate = new SchemaUpdate(cfg);
			schemaUpdate.execute(false, true);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}

	public static List<?> createQuery(Class<?> c,
			Collection<SimpleExpression> exp) {
		Session session = getSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(c);
		for (SimpleExpression se : exp) {
			crit.add(se);
		}
		List<?> list = crit.list();
		session.close();
		return list;
	}

	public static void delete(Object obj) {
		Transaction tx = null;
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					logger.debug("Error rolling back transaction");
				}
				throw e;
			}
		}
	}

	public static Serializable create(Object obj) {
		Serializable id = null;
		Session session = getSession();
		
		
		Transaction tx = session.beginTransaction();
		
		try {
			id = session.save(obj);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					logger.debug("Error rolling back transaction");
				}
				throw e;
			}
		}
		return id;
	}

	public static void update(Object obj) {
		Transaction tx = null;
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					logger.debug("Error rolling back transaction");
				}
				throw e;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class clazz) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String simpleName = clazz.getSimpleName();
		List<E> result = session.createQuery("from " + simpleName + " ORDER BY nome").list();
		session.getTransaction().commit();
		return result;
	}
	
}
