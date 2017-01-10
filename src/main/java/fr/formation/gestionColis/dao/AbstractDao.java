package fr.formation.gestionColis.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class AbstractDao<ENTITY> {

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

	}

	public ENTITY read(final Integer id) {

		return null;
	}

	public void update(final ENTITY entity) {

	}

	public void delete(final ENTITY entity) {

	}

}
