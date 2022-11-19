package com.ecommerce.metier;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adresse {
    private int ida;
    private String adresse;
    private String ville;
    private Integer codepostale;
    private String pays;
    private Integer idc;
    private Client clientByIdc;
    private Set<Commande> commandesByIda= new HashSet<Commande>(0);
    public Adresse(){
        super();
    }
    public Adresse(int i, Client clt, String adresse1, String ville, int parseInt, String pays, Set<Commande> o) {
        this.ida=i;
        this.clientByIdc=clt;
        this.adresse=adresse1;
        this.ville =ville;
        this.codepostale = parseInt;
        this.pays = pays;
        this.commandesByIda= o;
    }

    @Id
    @Column(name = "ida")
    public int getIdcat() {
        return ida;
    }

    public void setIdcat(int idcat) {
        this.ida = ida;
    }

    @Basic
    @Column(name = "adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "codepostale")
    public Integer getCodepostale() {
        return codepostale;
    }

    public void setCodepostale(Integer codepostale) {
        this.codepostale = codepostale;
    }

    @Basic
    @Column(name = "pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adresse adresse1 = (Adresse) o;

        if (ida != adresse1.ida) return false;
        if (adresse != null ? !adresse.equals(adresse1.adresse) : adresse1.adresse != null) return false;
        if (ville != null ? !ville.equals(adresse1.ville) : adresse1.ville != null) return false;
        if (codepostale != null ? !codepostale.equals(adresse1.codepostale) : adresse1.codepostale != null)
            return false;
        if (pays != null ? !pays.equals(adresse1.pays) : adresse1.pays != null) return false;
        if (idc != null ? !idc.equals(adresse1.idc) : adresse1.idc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ida;
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (codepostale != null ? codepostale.hashCode() : 0);
        result = 31 * result + (pays != null ? pays.hashCode() : 0);
        result = 31 * result + (idc != null ? idc.hashCode() : 0);
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

    @OneToMany(mappedBy = "adresseByIda")
    public Set<Commande> getCommandesByIda() {
        return commandesByIda;
    }

    public void setCommandesByIda(Set<Commande> commandesByIda) {
        this.commandesByIda = commandesByIda;
    }
}
