package fr.formation.gestionColis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nom;

	public Integer getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

}
