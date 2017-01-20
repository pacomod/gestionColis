package fr.formation.gestionColis.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the role database table.
 *
 */
@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

}