package fr.formation.gestionColis.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public abstract class AbstractDao<ENTITY> {

	@PersistenceContext
	protected EntityManager em;

	@Resource
	private UserTransaction transaction;

	protected void executeWithTransaction(final Runnable runnable) {
		try {
			this.transaction.begin();
			try {
				runnable.run();
				this.transaction.commit();
			} catch (final PersistenceException e) {
				this.transaction.rollback();
			}
		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	public void create(final ENTITY entity) {
		this.executeWithTransaction(() -> this.em.persist(entity));
		// this.executeWithTransaction(new Runnable() {
		// @Override
		// public void run() {
		// AbstractDao.this.en.persist(entity);
		// }
		// });
	}

	protected ENTITY read(final Class<ENTITY> entityClass, final Integer id) {
		return this.em.find(entityClass, id);
	}

	public abstract ENTITY read(final Integer id);

	protected List<ENTITY> readAll(final Class<ENTITY> entityClass) {
		final TypedQuery<ENTITY> query = this.em.createNamedQuery(
				entityClass.getSimpleName().concat(".findAll"), entityClass);
		return query.getResultList();
	}

	public abstract List<ENTITY> readAll();

	public void update(final ENTITY entity) {
		this.executeWithTransaction(() -> this.em.merge(entity));
	}

	public void delete(final Integer id) {
		this.executeWithTransaction(() -> this.em.remove(this.read(id)));
	}

}
