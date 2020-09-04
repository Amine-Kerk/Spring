package dev.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ingredient")
public class Ingredient {
	// TODO Mapping JPA
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	@Column(name="nom")
	private String nom;
	
	@ManyToMany(mappedBy = "listIngredients")
	private List<Plat> listPlatsQuiContiennentIng;

	
	
	
	
	
	// TODO Générer les getters/setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Plat> getListPlatsQuiContiennentIng() {
		return listPlatsQuiContiennentIng;
	}

	public void setListPlatsQuiContiennentIng(List<Plat> listPlatsQuiContiennentIng) {
		this.listPlatsQuiContiennentIng = listPlatsQuiContiennentIng;
	}

	
	
	@Override
	public String toString() {
		return "Id = " + id + "|| Nom = " + nom;
	}

	

}
