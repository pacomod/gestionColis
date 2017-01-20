package fr.formation.gestionColis.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.formation.gestionColis.dao.EtatDao;
import fr.formation.gestionColis.entity.Etat;

@ManagedBean
@ApplicationScoped
public class EtatService {

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	public Etat getFirst() {
		return this.etatDao.readByOrder(0);
	}

	public Etat getNext(final Integer currentOrder) {
		return this.etatDao.readByOrder(currentOrder + 1);
	}

	/**
	 * @param etatDao the etatDao to set
	 */
	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}
}
