package com.ecommerce.metier;

import javax.persistence.*;

@Entity
public class Lignecommande {
    private int idlc;
    private Integer idcmd;
    private Integer idp;
    private Integer qte;
    private Integer prixAchat;
    private Commande commandeByIdcmd;
    private Produit produitByIdp;

    public Lignecommande(int i, Produit produit, Commande cmd, int qte, Integer prix) {
        this.idlc = i;
        this.produitByIdp = produit;
        this.commandeByIdcmd = cmd;
        this.qte = qte;
        this.prixAchat = prix;
    }
    public Lignecommande(){
        super();
    }

    @Id
    @Column(name = "idlc")
    public int getIdlc() {
        return idlc;
    }

    public void setIdlc(int idlc) {
        this.idlc = idlc;
    }


    @Basic
    @Column(name = "qte")
    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    @Basic
    @Column(name = "prix_achat")
    public Integer getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Integer prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lignecommande that = (Lignecommande) o;

        if (idlc != that.idlc) return false;
        if (idcmd != null ? !idcmd.equals(that.idcmd) : that.idcmd != null) return false;
        if (idp != null ? !idp.equals(that.idp) : that.idp != null) return false;
        if (qte != null ? !qte.equals(that.qte) : that.qte != null) return false;
        if (prixAchat != null ? !prixAchat.equals(that.prixAchat) : that.prixAchat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idlc;
        result = 31 * result + (idcmd != null ? idcmd.hashCode() : 0);
        result = 31 * result + (idp != null ? idp.hashCode() : 0);
        result = 31 * result + (qte != null ? qte.hashCode() : 0);
        result = 31 * result + (prixAchat != null ? prixAchat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcmd", referencedColumnName = "idcmd")
    public Commande getCommandeByIdcmd() {
        return commandeByIdcmd;
    }

    public void setCommandeByIdcmd(Commande commandeByIdcmd) {
        this.commandeByIdcmd = commandeByIdcmd;
    }

    @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "idP")
    public Produit getProduitByIdp() {
        return produitByIdp;
    }

    public void setProduitByIdp(Produit produitByIdp) {
        this.produitByIdp = produitByIdp;
    }
}
