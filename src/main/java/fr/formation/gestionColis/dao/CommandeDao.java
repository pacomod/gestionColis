package fr.formation.gestionColis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestionColis.entity.Commande;

@ManagedBean
@ApplicationScoped
public class CommandeDao extends AbstractDao<Commande> {

	@Override
	public Commande read(final Integer id) {
		return this.read(Commande.class, id);
	}

	@Override
	public List<Commande> readAll() {
		return this.readAll(Commande.class);
	}

}
