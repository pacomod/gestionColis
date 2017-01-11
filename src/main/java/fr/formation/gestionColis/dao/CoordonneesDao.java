package fr.formation.gestionColis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestionColis.entity.Coordonnees;

@ManagedBean
@ApplicationScoped
public class CoordonneesDao extends AbstractDao<Coordonnees> {

	@Override
	public Coordonnees read(final Integer id) {
		return this.read(Coordonnees.class, id);
	}

	@Override
	public List<Coordonnees> readAll() {
		return this.readAll(Coordonnees.class);
	}

}
