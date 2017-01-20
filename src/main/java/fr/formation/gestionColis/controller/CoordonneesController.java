package fr.formation.gestionColis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.bean.CoordonneesBean;
import fr.formation.gestionColis.dao.CoordonneesDao;
import fr.formation.gestionColis.entity.Coordonnees;
import fr.formation.gestionColis.exception.CreateEntityException;
import fr.formation.gestionColis.exception.DeleteEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CoordonneesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CoordonneesController.class);

	@ManagedProperty("#{coordonneesDao}")
	private CoordonneesDao coordonneesDao;

	@ManagedProperty("#{coordonneesBean}")
	private CoordonneesBean coordonneesBean;

	private List<Coordonnees> coordonnees;

	@PostConstruct
	public void _init() {
		this.coordonnees = this.coordonneesDao.readAll();
	}

	public String save() {
		final Coordonnees coordonnees = new Coordonnees();
		coordonnees.setAddressLine1(this.coordonneesBean.getAddressLine1());
		coordonnees.setAddressLine2(this.coordonneesBean.getAddressLine2());
		coordonnees.setCity(this.coordonneesBean.getCity());
		coordonnees.setCountry(this.coordonneesBean.getCountry());
		coordonnees.setEmail(this.coordonneesBean.getEmail());
		coordonnees.setFirstname(this.coordonneesBean.getFirstname());
		coordonnees.setLastname(this.coordonneesBean.getLastname());
		coordonnees.setPhoneNumber(this.coordonneesBean.getPhoneNumber());
		coordonnees.setPostalCode(this.coordonneesBean.getPostalCode());
		try {
			this.coordonneesDao.create(coordonnees);
			FacesMessages.info("La coordonée a été créée avec succès.");
		} catch (final CreateEntityException e) {
			CoordonneesController.LOGGER.error("Impossible de créer la coordonée.");
			FacesMessages.error("Impossible de créer la coordonée.");
		}
		return "/views/dashboard";
	}

	public String delete(final Coordonnees coordonnees) {
		try {
			this.coordonneesDao.delete(coordonnees.getId());
			FacesMessages.info("La coordonnee a été effacé avec succès.");
		} catch (final DeleteEntityException e) {
			CoordonneesController.LOGGER
					.error("Erreur lors de la suppression de la coordonée.");
			FacesMessages.error("Impossible de supprimer la coordonée.");
		}
		this.coordonnees.remove(coordonnees);
		return "/views/address/display";
	}

	public void setCoordonneesDao(final CoordonneesDao coordonneesDao) {
		this.coordonneesDao = coordonneesDao;
	}

	public void setCoordonneesBean(final CoordonneesBean coordonneesBean) {
		this.coordonneesBean = coordonneesBean;
	}

	public List<Coordonnees> getCoordonnees() {
		return this.coordonneesDao.readAll();
	}
}
