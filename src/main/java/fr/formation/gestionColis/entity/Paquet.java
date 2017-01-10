package fr.formation.gestionColis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paquet database table.
 * 
 */
@Entity
@Table(name="paquet")
@NamedQuery(name="Paquet.findAll", query="SELECT p FROM Paquet p")
public class Paquet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_RECIPISSE")
	private Date dateRecipisse;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="paquetBean")
	private List<Commande> commandes;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="COLIS")
	private Product coli;

	//bi-directional many-to-one association to Coordonnees
	@ManyToOne
	@JoinColumn(name="EXPEDITEUR")
	private Coordonnees coordonnee1;

	//bi-directional many-to-one association to Coordonnees
	@ManyToOne
	@JoinColumn(name="DESTINATAIRE")
	private Coordonnees coordonnee2;

	public Paquet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateRecipisse() {
		return this.dateRecipisse;
	}

	public void setDateRecipisse(Date dateRecipisse) {
		this.dateRecipisse = dateRecipisse;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setPaquetBean(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setPaquetBean(null);

		return commande;
	}

	public Product getColi() {
		return this.coli;
	}

	public void setColi(Product coli) {
		this.coli = coli;
	}

	public Coordonnees getCoordonnee1() {
		return this.coordonnee1;
	}

	public void setCoordonnee1(Coordonnees coordonnee1) {
		this.coordonnee1 = coordonnee1;
	}

	public Coordonnees getCoordonnee2() {
		return this.coordonnee2;
	}

	public void setCoordonnee2(Coordonnees coordonnee2) {
		this.coordonnee2 = coordonnee2;
	}

}