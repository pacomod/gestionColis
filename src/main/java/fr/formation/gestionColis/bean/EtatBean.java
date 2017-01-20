package fr.formation.gestionColis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EtatBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nom;
	// private int ordre;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	// public int getOrdre() {
	// return this.ordre;
	// }
	//
	// public void setOrdre(final int ordre) {
	// this.ordre = ordre;
	// }

}
