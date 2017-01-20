package fr.formation.gestionColis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.bean.LiasseBean;
import fr.formation.gestionColis.dao.LiasseDao;
import fr.formation.gestionColis.entity.Liasse;

@ManagedBean
@ViewScoped
public class LiasseController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(EtatController.class);

	private static final long serialVersionUID = 1L;

	private Integer liasseId;

	// Injection de dépendance ;-) Merci Jérémy
	@ManagedProperty("#{liasseBean}")
	private LiasseBean liasseBean;

	@ManagedProperty("#{liasseDao}")
	private LiasseDao liasseDao;

	private List<Liasse> liasses;

	@PostConstruct
	public void _init() {
		LiasseController.LOGGER.debug("Chargement de la liste des liasses");
		this.liasses = this.liasseDao.readAll();
	}

	/**
	 *
	 * LISTE DES GETTERS SETTERS
	 */

	public LiasseBean getLiasseBean() {
		return this.liasseBean;
	}

	public Integer getLiasseId() {
		return this.liasseId;
	}

	public void setLiasseId(final Integer liasseId) {
		this.liasseId = liasseId;
	}

	public void setLiasseBean(final LiasseBean liasseBean) {
		this.liasseBean = liasseBean;
	}

	public LiasseDao getLiasseDao() {
		return this.liasseDao;
	}

	public void setLiasseDao(final LiasseDao liasseDao) {
		this.liasseDao = liasseDao;
	}

	public List<Liasse> getLiasses() {
		return this.liasses;
	}

	public void setLiasses(final List<Liasse> liasses) {
		this.liasses = liasses;
	}

}
