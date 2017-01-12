package fr.formation.gestionColis.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestionColis.bean.CoordonneesBean;
import fr.formation.gestionColis.dao.CoordonneesDao;
import fr.formation.gestionColis.entity.Coordonnees;

@ManagedBean
@ViewScoped
public class CoordonneesController {

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
		this.coordonneesDao.create(coordonnees);
		return "/views/dashboard";
	}

	public String delete(final Coordonnees coordonnees) {
		this.coordonneesDao.delete(coordonnees.getId());
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
