package fr.formation.gestionColis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the etat database table.
 * 
 */
@Entity
@Table(name="etat")
@NamedQuery(name="Etat.findAll", query="SELECT e FROM Etat e")
public class Etat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOM")
	private String nom;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="etatBean")
	private List<Commande> commandes;

	public Etat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setEtatBean(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setEtatBean(null);

		return commande;
	}

}