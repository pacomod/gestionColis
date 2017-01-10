package fr.formation.gestionColis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the liasse database table.
 * 
 */
@Entity
@Table(name="liasse")
@NamedQuery(name="Liasse.findAll", query="SELECT l FROM Liasse l")
public class Liasse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_SIGNATURE")
	private Date dateSignature;

	@Column(name="NATURE")
	private String nature;

	@Column(name="VALEUR")
	private double valeur;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="COMMANDE")
	private Commande commandeBean;

	public Liasse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public double getValeur() {
		return this.valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Commande getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}