package fr.formation.gestionColis.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.bean.EtatBean;
import fr.formation.gestionColis.bean.EtatOrderBean;
import fr.formation.gestionColis.dao.EtatDao;
import fr.formation.gestionColis.entity.Etat;
import fr.formation.gestionColis.exception.CreateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class EtatController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EtatController.class);

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	@ManagedProperty("#{etatBean}")
	private EtatBean etatBean;

	@ManagedProperty("#{etatOrderBean}")
	private EtatOrderBean etatOrderBean;

	@PostConstruct
	public void _init() {
		this.etatOrderBean.refresh();
	}

	public EtatBean getEtatBean() {
		return this.etatBean;
	}

	public EtatDao getEtatDao() {
		return this.etatDao;
	}

	public EtatOrderBean getEtatOrderBean() {
		return this.etatOrderBean;
	}

	public void save() {
		EtatController.LOGGER.info("Sauvegarde de l'état");
		if (this.etatBean.getId() == null) {
			try {
				final Etat etat = new Etat(this.etatBean.getNom());
				etat.setOrdre(this.etatOrderBean.getEtats().size());
				this.etatDao.create(etat);
				FacesMessages.info(
						"Nouvel état " + this.etatBean.getNom() + "créé avec succès.");
				this.etatBean.setNom(null);
			} catch (final CreateEntityException e) {
				EtatController.LOGGER
						.error("Erreur pendant la création d'un nouvel état", e);
				FacesMessages.error(
						"Impossible de créer l'état avec le nom " + this.etatBean.getNom());
			}
		} else {
			// TODO: Modification
		}
		this.etatOrderBean.refresh();
	}

	public void saveOrder() {
		EtatController.LOGGER.info("Sauvegarde de l'ordre des état");
	}

	public void setEtatBean(final EtatBean etatBean) {
		this.etatBean = etatBean;
	}

	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

	public void setEtatOrderBean(final EtatOrderBean etatOrderBean) {
		this.etatOrderBean = etatOrderBean;
	}

}
