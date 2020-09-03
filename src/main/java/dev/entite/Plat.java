package dev.entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plat")
public class Plat {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")

    private int id;

@Column(name="nom")
    private String nom;
@Column(name="prix")
    private Integer prixEnCentimesEuros;
    //ajoutant de la relation (plat <> ingredient)
@ManyToMany 
@JoinTable(name="plat_ingredient",joinColumns = @JoinColumn(name ="plat_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="ingredient_id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    public Plat() {
    }

    public Plat(String nom, Integer prixEnCentimesEuros) {
        this.nom = nom;
        this.prixEnCentimesEuros = prixEnCentimesEuros;
    }

   

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPrixEnCentimesEuros() {
		return prixEnCentimesEuros;
	}

	public void setPrixEnCentimesEuros(Integer prixEnCentimesEuros) {
		this.prixEnCentimesEuros = prixEnCentimesEuros;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	

	@Override
	public String toString() {
		return "Plat [id=" + id + ", nom=" + nom + ", prixEnCentimesEuros=" + prixEnCentimesEuros + ", ingredients="
				 + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return nom.equals(plat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
