package com.ecommerce.metier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categorie {
    private long idcat;
    private String libelle;
    private String description;
    private List<Produit> produitsByIdcat = new ArrayList<Produit>(0);

    @Id
    @Column(name = "idcat")
    public long getIdcat() {
        return idcat;
    }

    public void setIdcat(long idcat) {
        this.idcat = idcat;
    }

    @Basic
    @Column(name = "libelle")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorie categorie = (Categorie) o;

        if (idcat != categorie.idcat) return false;
        if (libelle != null ? !libelle.equals(categorie.libelle) : categorie.libelle != null) return false;
        if (description != null ? !description.equals(categorie.description) : categorie.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = idcat;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return (int) result;
    }

    @OneToMany(mappedBy = "categorieByIdcat")
    public List<Produit> getProduitsByIdcat() {
        return produitsByIdcat;
    }

    public void setProduitsByIdcat(List<Produit> produitsByIdcat) {
        this.produitsByIdcat = produitsByIdcat;
    }
}
