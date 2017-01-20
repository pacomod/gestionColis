package fr.formation.gestionColis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestionColis.bean.ProductBean;
import fr.formation.gestionColis.dao.ProductDao;
import fr.formation.gestionColis.entity.Product;
import fr.formation.gestionColis.exception.CreateEntityException;
import fr.formation.gestionColis.exception.DeleteEntityException;
import fr.formation.gestionColis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

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

	/**
	 * Product list
	 */
	private List<Product> products;

	/**
	 * id of product to edit
	 */
	private Integer productId;

	private boolean deleteProduct;

	/**
	 * creates or updates a product
	 */
	public String save() {
		final Product product = new Product();
		product.setIntitule(this.productBean.getIntitule());
		product.setPoids(this.productBean.getPoids());
		product.setReference(this.productBean.getReference());
		try {
			if (this.productId == null) {
				ProductController.LOGGER.debug("Création du nouveau produit {}",
						product);
				this.productDao.create(product);
				FacesMessages.info("Le produit a été créé avec succès.");
			} else {
				product.setId(this.getProductId());
				ProductController.LOGGER.info("Mise à jour du produit {}", product);
				this.productDao.update(product);
				FacesMessages.info("Le produit a été mis à jour avec succès.");
			}

		} catch (final CreateEntityException e) {
			ProductController.LOGGER.error("Impossible de créer le produit.", e);
			FacesMessages.error("Impossible de créer le produit.");
		} catch (final UpdateEntityException e) {
			ProductController.LOGGER.error(
					"Impossible de mettre à jour le produit d'id=" + this.productId, e);
			FacesMessages.error("Impossible de créer mettre à jour le produit.");
		}
		return "/views/dashboard";
	}

	public void prepareEdit() {
		final String productId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("productId");
		final String deleteProduct = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("deletProduct");
		if (deleteProduct != null) {
			ProductController.LOGGER.info("deleteProduct set");
			this.deleteProduct = true;
		}
		if (productId != null) {
			ProductController.LOGGER.info("productId=" + productId);
			this.productId = Integer.parseInt(productId);
			final Product product = this.productDao.read(this.productId);
			this.productBean.setIntitule(product.getIntitule());
			this.productBean.setPoids(product.getPoids());
			this.productBean.setReference(product.getReference());
		}
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
		try {
			this.productDao.delete(product.getId());
			this.products.remove(product);
			FacesMessages.info("Le produit a été effacé avec succès.");
		} catch (final DeleteEntityException e) {
			ProductController.LOGGER
					.error("Erreur lors de la suppression du produit.");
			FacesMessages.error("Impossible de supprimer le produit.");
		}
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

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

}
