package fr.formation.gestionColis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestionColis.entity.Paquet;

@ManagedBean
@ApplicationScoped
public class PaquetDao extends AbstractDao<Paquet> {

	@Override
	public Paquet read(final Integer id) {
		return this.read(Paquet.class, id);
	}

	@Override
	public List<Paquet> readAll() {
		return this.readAll(Paquet.class);
	}

}
