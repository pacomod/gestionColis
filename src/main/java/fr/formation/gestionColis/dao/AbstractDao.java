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

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.exception.CreateEntityException;
import fr.formation.gestionColis.exception.DaoException;
import fr.formation.gestionColis.exception.DeleteEntityException;
import fr.formation.gestionColis.exception.FunctionalDaoException;
import fr.formation.gestionColis.exception.TechnicalDaoException;
import fr.formation.gestionColis.exception.UpdateEntityException;

public abstract class AbstractDao<ENTITY> {

	@PersistenceContext
	protected EntityManager em;

	@Resource
	private UserTransaction transaction;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractDao.class);

	protected void executeWithTransaction(final Runnable runnable)
			throws DaoException {
		try {
			this.transaction.begin();
			try {
				runnable.run();
				this.transaction.commit();
			} catch (final PersistenceException e) {
				this.transaction.rollback();
				AbstractDao.LOGGER.error("Erreur lors de l'exécution d'une transaction",
						e);
			}
		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException e) {
			AbstractDao.LOGGER.error("Erreur de gestion de la transaction", e);
			if (ExceptionUtils.indexOfThrowable(e,
					ConstraintViolationException.class) >= 0) {
				throw new FunctionalDaoException(e);
			} else {
				throw new TechnicalDaoException(
						"Erreur pendant l'excécution des traitements du DAO", e);
			}
		}
	}

	public void create(final ENTITY entity) throws CreateEntityException {
		try {
			this.executeWithTransaction(() -> this.em.persist(entity));
		} catch (final FunctionalDaoException e) {
			throw new CreateEntityException(
					"Impossible de créer l'entité à cause d'une contrainte de la base de données.",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER
					.error("Erreur technique lors de la création de l'entité.", e);
		}
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

	public void update(final ENTITY entity) throws UpdateEntityException {
		try {
			this.executeWithTransaction(() -> this.em.merge(entity));
		} catch (final FunctionalDaoException e) {
			throw new UpdateEntityException(
					"Impossible de mettre à jour l'entité à cause "
							+ "d'une contrainte de la base de données.",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER
					.error("Erreur technique pendant la mise à jour de l'entité.", e);
		}
	}

	public void delete(final Integer id) throws DeleteEntityException {
		try {
			this.executeWithTransaction(() -> this.em.remove(this.read(id)));
		} catch (final FunctionalDaoException e) {
			throw new DeleteEntityException(
					"Impossible de supprimer l'entité à cause d'une contrainte technique",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER
					.error("Erreur technique lors de la suppression de l'entité.", e);
		}
	}

}
