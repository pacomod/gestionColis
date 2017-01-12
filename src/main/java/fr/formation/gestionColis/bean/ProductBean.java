package fr.formation.gestionColis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String intitule;
	private float poids;
	private String reference;

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(final String intitule) {
		this.intitule = intitule;
	}

	public float getPoids() {
		return this.poids;
	}

	public void setPoids(final float poids) {
		this.poids = poids;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}
}
