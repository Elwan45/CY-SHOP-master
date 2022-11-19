package com.ecommerce.metier;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Commentaire {
    private int idComm;
    private Integer idP;
    private Integer idcmd;
    private String commentaire;
    private Date datePub;
    private Produit produitByIdP;
    private Commande commandeByIdcmd;

    @Id
    @Column(name = "idComm")
    public int getIdComm() {
        return idComm;
    }

    public void setIdComm(int idComm) {
        this.idComm = idComm;
    }




    @Basic
    @Column(name = "commentaire")
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
        if (o == null || getClass() != o.getClass()) return false;

        Commentaire that = (Commentaire) o;

        if (idComm != that.idComm) return false;
        if (idP != null ? !idP.equals(that.idP) : that.idP != null) return false;
        if (idcmd != null ? !idcmd.equals(that.idcmd) : that.idcmd != null) return false;
        if (commentaire != null ? !commentaire.equals(that.commentaire) : that.commentaire != null) return false;
        if (datePub != null ? !datePub.equals(that.datePub) : that.datePub != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idComm;
        result = 31 * result + (idP != null ? idP.hashCode() : 0);
        result = 31 * result + (idcmd != null ? idcmd.hashCode() : 0);
        result = 31 * result + (commentaire != null ? commentaire.hashCode() : 0);
        result = 31 * result + (datePub != null ? datePub.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idP", referencedColumnName = "idP")
    public Produit getProduitByIdP() {
        return produitByIdP;
    }

    public void setProduitByIdP(Produit produitByIdP) {
        this.produitByIdP = produitByIdP;
    }

    @ManyToOne
    @JoinColumn(name = "idcmd", referencedColumnName = "idcmd")
    public Commande getCommandeByIdcmd() {
        return commandeByIdcmd;
    }

    public void setCommandeByIdcmd(Commande commandeByIdcmd) {
        this.commandeByIdcmd = commandeByIdcmd;
    }
}
