package fr.formation.gestionColis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String password;
	private Integer role;

	public Integer getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public Integer getRole() {
		return this.role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRole(final Integer role) {
		this.role = role;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
