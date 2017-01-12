package fr.formation.gestionColis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.bean.ProductBean;
import fr.formation.gestionColis.dao.ProductDao;
import fr.formation.gestionColis.entity.Product;

@ManagedBean
@ViewScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProductController.class);

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	private List<Product> products;

	public String save() {
		final Product product = new Product();
		product.setIntitule(this.productBean.getIntitule());
		product.setPoids(this.productBean.getPoids());
		product.setReference(this.productBean.getReference());
		this.productDao.create(product);
		return "/views/dashboard";
	}

	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setProductBean(final ProductBean productBean) {
		this.productBean = productBean;
	}

	@PostConstruct
	public void _init() {
		this.products = this.productDao.readAll();
	}

	public String delete(final Product product) {
		this.productDao.delete(product.getId());
		this.products.remove(product);
		return "/views/product/display";
	}

	public String update(final Product product) {
		ProductController.LOGGER.debug("Update du produit.");
		return "/views/product/create";
	}

	public List<Product> getProducts() {
		ProductController.LOGGER.debug("Chargement de la liste des produits.");
		return this.products;
	}
}
