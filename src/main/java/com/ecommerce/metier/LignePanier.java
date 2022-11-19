package com.ecommerce.metier;

public class LignePanier {
    private Produit produit;
    private int qte;


    public LignePanier() {
        super();
    }

    public LignePanier(Produit pr, int qte) {
        this.produit=pr;
        this.qte=qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

}
