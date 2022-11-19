package com.ecommerce.metier;

import javax.persistence.*;
import java.util.*;
import com.ecommerce.metier.*;

@Entity
public class Produit {
    private int idP;
    private Integer idcat;
    private String libelle;
    private String marque;
    private Integer prix;
    private String description;
    private Integer fraisExpedition;
    private boolean disponibilite;
    private Integer qtestck;
    private Date datePub;
    private List<Lignecommande> lignecommandesByIdP = new ArrayList<>(0);
    private Set<Commentaire> commentairesByIdP = new HashSet<Commentaire>(0);
    private List<Image> imagesByIdP = new ArrayList<Image>(0);

    private Categorie categorieByIdcat;

    public Produit(){
        super();
    }
    public Produit(int i, Categorie cat, String libelle, String marque, int prix, String description, int frais, boolean disponible, int qte, Date date ,List<Lignecommande> lc,Set<Commentaire> comt, List<Image> images) {
        this.idP = i;
        this.categorieByIdcat = cat;
        this.libelle=libelle;
        this.marque=marque;
        this.prix=prix;
        this.description=description;
        this.fraisExpedition=frais;
        this.disponibilite = disponible;
        this.qtestck = qte;
        this.datePub = date;
        this.commentairesByIdP = comt;
        this.lignecommandesByIdP = lc;
        this.imagesByIdP = images;

    }

    @Id
    @Column(name = "idP")
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
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
    @Column(name = "Marque")
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "prix")
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "fraisExpedition")
    public Integer getFraisExpedition() {
        return fraisExpedition;
    }

    public void setFraisExpedition(Integer fraisExpedition) {
        this.fraisExpedition = fraisExpedition;
    }

    @Basic
    @Column(name = "disponibilite")
    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Basic
    @Column(name = "qtestck")
    public Integer getQtestck() {
        return qtestck;
    }

    public void setQtestck(Integer qtestck) {
        this.qtestck = qtestck;
    }

    @Basic
    @Column(name = "date_pub")
    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return idP == produit.idP && disponibilite == produit.disponibilite && idcat.equals(produit.idcat) && Objects.equals(libelle, produit.libelle) && Objects.equals(marque, produit.marque) && Objects.equals(prix, produit.prix) && Objects.equals(description, produit.description) && Objects.equals(fraisExpedition, produit.fraisExpedition) && Objects.equals(qtestck, produit.qtestck) && Objects.equals(datePub, produit.datePub) && Objects.equals(lignecommandesByIdP, produit.lignecommandesByIdP) && Objects.equals(commentairesByIdP, produit.commentairesByIdP) && Objects.equals(imagesByIdP, produit.imagesByIdP) && Objects.equals(categorieByIdcat, produit.categorieByIdcat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idP, idcat, libelle, marque, prix, description, fraisExpedition, disponibilite, qtestck, datePub, lignecommandesByIdP, commentairesByIdP, imagesByIdP, categorieByIdcat);
    }

    @OneToMany(mappedBy = "produitByIdP")
    public Set<Commentaire> getCommentairesByIdP() {
        return commentairesByIdP;
    }

    public void setCommentairesByIdP(Set<Commentaire> commentairesByIdP) {
        this.commentairesByIdP = commentairesByIdP;
    }

    @OneToMany(mappedBy = "produitByIdp")
    public List<Image> getImagesByIdP() {
        return imagesByIdP;
    }

    public void setImagesByIdP(List<Image> imagesByIdP) {
        this.imagesByIdP = imagesByIdP;
    }

    @OneToMany(mappedBy = "produitByIdp")
    public List<Lignecommande> getLignecommandesByIdP() {
        return lignecommandesByIdP;
    }

    public void setLignecommandesByIdP(List<Lignecommande> lignecommandesByIdP) {
        this.lignecommandesByIdP = lignecommandesByIdP;
    }

    @ManyToOne
    @JoinColumn(name = "idcat", referencedColumnName = "idcat")
    public Categorie getCategorieByIdcat() {
        return categorieByIdcat;
    }

    public void setCategorieByIdcat(Categorie categorieByIdcat) {
        this.categorieByIdcat = categorieByIdcat;
    }
}
