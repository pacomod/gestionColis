package fr.formation.gestionColis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the bordereau database table.
 * 
 */
@Entity
@Table(name = "bordereau")
@NamedQuery(name = "Bordereau.findAll", query = "SELECT b FROM Bordereau b")
public class Bordereau implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_SIGNATURE")
	private Date dateSignature;

	@Column(name = "DETAIL")
	private String detail;

	// bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name = "COMMANDE")
	private Commande commandeBean;

	public Bordereau() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(final Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(final String detail) {
		this.detail = detail;
	}

	public Commande getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(final Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}