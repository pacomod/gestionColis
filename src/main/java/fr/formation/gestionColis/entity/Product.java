package fr.formation.gestionColis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the colis database table.
 * 
 */
@Entity
@Table(name = "colis")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "INTITULE")
	private String intitule;

	@Column(name = "POIDS")
	private float poids;

	@Column(name = "REFERENCE")
	private String reference;

	// bi-directional many-to-one association to Paquet
	@OneToMany(mappedBy = "coli")
	private List<Paquet> paquets;

	public Product() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

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

	public List<Paquet> getPaquets() {
		return this.paquets;
	}

	public void setPaquets(final List<Paquet> paquets) {
		this.paquets = paquets;
	}

	public Paquet addPaquet(final Paquet paquet) {
		this.getPaquets().add(paquet);
		paquet.setColi(this);

		return paquet;
	}

	public Paquet removePaquet(final Paquet paquet) {
		this.getPaquets().remove(paquet);
		paquet.setColi(null);

		return paquet;
	}

}