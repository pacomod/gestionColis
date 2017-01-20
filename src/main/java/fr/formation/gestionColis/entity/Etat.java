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
import javax.persistence.Transient;

/**
 * The persistent class for the etat database table.
 * 
 */
@Entity
@Table(name = "etat")
@NamedQuery(name = "Etat.findAll", query = "SELECT e FROM Etat e")
public class Etat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOM")
	private String nom;

	private int ordre;

	// bi-directional many-to-one association to Commande
	@OneToMany(mappedBy = "etatBean")
	private List<Commande> commandes;

	@Transient // this field won't be serialized nor stored in db.
	private String test;

	public Etat() {
		super();
	}

	public Etat(final String nom) {
		this.nom = nom;
	}

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

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(final List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(final Commande commande) {
		this.getCommandes().add(commande);
		commande.setEtatBean(this);

		return commande;
	}

	public Commande removeCommande(final Commande commande) {
		this.getCommandes().remove(commande);
		commande.setEtatBean(null);

		return commande;
	}

	public int getOrdre() {
		return this.ordre;
	}

	public void setOrdre(final int ordre) {
		this.ordre = ordre;
	}

}