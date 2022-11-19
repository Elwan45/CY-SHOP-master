package com.ecommerce.metier;

import javax.persistence.*;
import java.util.*;

@Entity
public class Commande {
    private int idcmd;
    private Integer idc;
    private Date datecmd;
    private String methodePay;
    private String etat;
    private Integer ida;
    private Client clientByIdc;
    private Adresse adresseByIda;
    private Set<Commentaire> commentairesByIdcmd = new HashSet<Commentaire>(0);
    private List<Lignecommande> lignecommandesByIdcmd = new ArrayList<Lignecommande>(0);

    public  Commande(){
        super();
    }
    public Commande(int i, Client clt, Adresse ad, Date date, String visa, String en_cours, ArrayList<Lignecommande> lignecommandes, Set<Commentaire> com) {
        this.idcmd = i;
        this.clientByIdc = clt;
        this.adresseByIda = ad;
        this.datecmd = date;
        this.methodePay=visa;
        this.etat=en_cours;
        this.lignecommandesByIdcmd = lignecommandes;
        this.commentairesByIdcmd=com;

    }

    @Id
    @Column(name = "idcmd")
    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }



    @Basic
    @Column(name = "datecmd")
    public Date getDatecmd() {
        return datecmd;
    }

    public void setDatecmd(Date datecmd) {
        this.datecmd = datecmd;
    }

    @Basic
    @Column(name = "methode_pay")
    public String getMethodePay() {
        return methodePay;
    }

    public void setMethodePay(String methodePay) {
        this.methodePay = methodePay;
    }

    @Basic
    @Column(name = "etat")
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande commande = (Commande) o;

        if (idcmd != commande.idcmd) return false;
        if (idc != null ? !idc.equals(commande.idc) : commande.idc != null) return false;
        if (datecmd != null ? !datecmd.equals(commande.datecmd) : commande.datecmd != null) return false;
        if (methodePay != null ? !methodePay.equals(commande.methodePay) : commande.methodePay != null) return false;
        if (etat != null ? !etat.equals(commande.etat) : commande.etat != null) return false;
        if (ida != null ? !ida.equals(commande.ida) : commande.ida != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcmd;
        result = 31 * result + (idc != null ? idc.hashCode() : 0);
        result = 31 * result + (datecmd != null ? datecmd.hashCode() : 0);
        result = 31 * result + (methodePay != null ? methodePay.hashCode() : 0);
        result = 31 * result + (etat != null ? etat.hashCode() : 0);
        result = 31 * result + (ida != null ? ida.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idc", referencedColumnName = "idc")
    public Client getClientByIdc() {
        return clientByIdc;
    }

    public void setClientByIdc(Client clientByIdc) {
        this.clientByIdc = clientByIdc;
    }

    @ManyToOne
    @JoinColumn(name = "ida", referencedColumnName = "ida")
    public Adresse getAdresseByIda() {
        return adresseByIda;
    }

    public void setAdresseByIda(Adresse adresseByIda) {
        this.adresseByIda = adresseByIda;
    }

    @OneToMany(mappedBy = "commandeByIdcmd")
    public Set<Commentaire> getCommentairesByIdcmd() {
        return commentairesByIdcmd;
    }

    public void setCommentairesByIdcmd(Set<Commentaire> commentairesByIdcmd) {
        this.commentairesByIdcmd = commentairesByIdcmd;
    }

    @OneToMany(mappedBy = "commandeByIdcmd")
    public List<Lignecommande> getLignecommandesByIdcmd() {
        return lignecommandesByIdcmd;
    }

    public void setLignecommandesByIdcmd(List<Lignecommande> lignecommandesByIdcmd) {
        this.lignecommandesByIdcmd = lignecommandesByIdcmd;
    }
    public float total(){
        float tot=0;
        for(Lignecommande lc:lignecommandesByIdcmd){
            tot+=lc.getProduitByIdp().getPrix()*lc.getQte();
        }

        return tot;
    }

}
