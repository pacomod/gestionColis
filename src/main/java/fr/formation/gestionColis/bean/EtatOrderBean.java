package fr.formation.gestionColis.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestionColis.dao.EtatDao;
import fr.formation.gestionColis.entity.Etat;

@ManagedBean
@ViewScoped
public class EtatOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Etat> etats;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	@PostConstruct
	public void _init() {
		this.refresh();
	}

	public void refresh() {
		this.etats = this.etatDao.readAll();
		// Java 7 (lambda)
		Collections.sort(this.etats,
				(o1, o2) -> Integer.compare(o1.getOrdre(), o2.getOrdre()));
		// Java 8 (functional)
		// Collections.sort(this.etats, Comparator.comparingInt(Etat::getOrdre));
	}

	public List<Etat> getEtats() {
		return this.etats;
	}

	public void setEtats(final List<String> etats) {
		for (int i = 0; i < etats.size(); i++) {
			final Integer id = Integer.parseInt(etats.get(i));
			final Integer index = this.etats.stream().map(Etat::getId)
					.collect(Collectors.toList()).indexOf(id);
			this.etats.get(index).setOrdre(i);
		}
	}

	public EtatDao getEtatDao() {
		return this.etatDao;
	}

	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

}
