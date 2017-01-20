package fr.formation.gestionColis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestionColis.entity.Facture;

@ManagedBean
@ApplicationScoped
public class FactureDao extends AbstractDao<Facture> {

	@Override
	public Facture read(final Integer id) {
		return this.read(Facture.class, id);
	}

	@Override
	public List<Facture> readAll() {
		return this.readAll(Facture.class);
	}
}