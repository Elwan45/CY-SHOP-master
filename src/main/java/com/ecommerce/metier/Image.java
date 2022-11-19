package com.ecommerce.metier;

import javax.persistence.*;

@Entity
public class Image {
    private int idimg;
    private String url;
    private Boolean isprincipal;
    private Integer idp;
    private Produit produitByIdp;

    public Image(){
        super();
    }
    public Image(int i, Produit p, String filename, boolean b) {
        this.idimg=i;
        this.produitByIdp=p;
        this.url=filename;
        this.isprincipal=b;
    }

    @Id
    @Column(name = "idimg")
    public int getIdimg() {
        return idimg;
    }

    public void setIdimg(int idimg) {
        this.idimg = idimg;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "isprincipal")
    public Boolean getIsprincipal() {
        return isprincipal;
    }

    public void setIsprincipal(Boolean isprincipal) {
        this.isprincipal = isprincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (idimg != image.idimg) return false;
        if (url != null ? !url.equals(image.url) : image.url != null) return false;
        if (isprincipal != null ? !isprincipal.equals(image.isprincipal) : image.isprincipal != null) return false;
        if (idp != null ? !idp.equals(image.idp) : image.idp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idimg;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (isprincipal != null ? isprincipal.hashCode() : 0);
        result = 31 * result + (idp != null ? idp.hashCode() : 0);
        return result;
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
