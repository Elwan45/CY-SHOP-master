package com.ecommerce.metier;

import com.ecommerce.dao.HibernateSession;
import org.hibernate.Session;

import java.util.ArrayList;

public class Panier {
    private ArrayList<LignePanier> items=new ArrayList<LignePanier>();
    private int idc;

    public ArrayList<LignePanier> getItems() {
        return items;
    }
    public Panier(){
        super();
    }


    public void setItems(ArrayList<LignePanier> items) {
        this.items = items;
    }



    public void addItem(int idp,int qte){
        boolean flag=true;
        for(LignePanier lp:items){
            if(lp.getProduit().getIdP()==idp){
                lp.setQte(lp.getQte()+qte);
                flag=false;
                break;
            }
        }
        if(flag){
            Session s= HibernateSession.getSession();
            Produit pr=(Produit) s.get(Produit.class, idp);
            LignePanier lp=new LignePanier(pr, qte);
            items.add(lp);
        }
    }

    public void augmenterQte(int idp) {

        for(LignePanier lp:items){
            while (lp.getQte()<lp.getProduit().getQtestck()){
                if(lp.getProduit().getIdP()==idp ){
                    lp.setQte(lp.getQte()+1);
                    //lp.getProduit().setQtestck(lp.getProduit().getQtestck()-1);

                    break;
                }
            }

        }
    }
    public void dimunierQte(int idp) {
        for(LignePanier lp:items){
            if(lp.getProduit().getIdP()==idp){
                lp.setQte(lp.getQte()-1);
                if(lp.getQte()==0){
                    items.remove(lp);
                }
                break;
            }
        }
    }

    public float fraisexpedition(){
        float f=0;
        for(LignePanier lp:items){
            f+=lp.getProduit().getFraisExpedition();

        }
        return f;
    }

    public float total(){
        float f=0;
        for(LignePanier lp:items){
            f+=lp.getProduit().getPrix()*lp.getQte();

        }
        return f;
    }

    public void supprimerProduit(int idp) {
        for(LignePanier lp:items){
            if(lp.getProduit().getIdP()==idp){
                items.remove(lp);
                break;
            }
        }
    }
}
