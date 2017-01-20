package fr.formation.gestionColis.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String password;

	private int roleid;

	private String username;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public int getRoleid() {
		return this.roleid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRoleid(final int roleid) {
		this.roleid = roleid;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}