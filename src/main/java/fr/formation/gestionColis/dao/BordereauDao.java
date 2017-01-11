package fr.formation.gestionColis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestionColis.entity.Bordereau;

@ManagedBean
@ApplicationScoped
public class BordereauDao extends AbstractDao<Bordereau> {

	@Override
	public Bordereau read(final Integer id) {
		return this.read(Bordereau.class, id);
	}

	@Override
	public List<Bordereau> readAll() {
		return this.readAll(Bordereau.class);
	}

}
