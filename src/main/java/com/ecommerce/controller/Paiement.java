package com.ecommerce.controller;

import com.ecommerce.dao.HibernateSession;
import com.ecommerce.dao.HibernateUtil;
import com.ecommerce.metier.*;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "Paiement", urlPatterns = {"/Paiement"})
public class Paiement extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Paiement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Paiement at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Session s = HibernateSession.getSession();
        s.beginTransaction();
        long nocarte = Long.parseLong(request.getParameter("nocarte"));
        String datecarte = request.getParameter("datecarte");
        int crypto = Integer.parseInt(request.getParameter("crypto"));
        //if(nocarte.equals("123456789")&&datecarte.equals("12/21")&&crypto.equals("123")){
        Panier p = (Panier) request.getSession().getAttribute("panier");
        Integer mnt = 0;
        for (LignePanier lp2 : p.getItems()) {
            mnt += lp2.getQte() * lp2.getProduit().getPrix();
        }

        Query q = s.createQuery("Select c from Carte c where  c.dateExpiration=:datecarte and c.numero=:nocarte and c.crypto=:crypto");
        q.setLong("nocarte", nocarte);
        q.setString("datecarte", datecarte);
        q.setInteger("crypto", crypto);

        List<Carte> l = q.list();
        Integer a = solde();
        System.out.println("quantite stock="+a);

        if(mnt<= a){
            if (l.size() > 0) {
                solde2(mnt,a);
                String adresse1 = request.getParameter("adresse1");
                String adresse2 = request.getParameter("adresse2");
                String pays = request.getParameter("pays");
                String ville = request.getParameter("ville");
                int codepostale = Integer.parseInt(request.getParameter("codepostale"));
                Client clt = (Client) request.getSession().getAttribute("clt");
                clt = (Client) s.get(Client.class, clt.getIdc());

                Query q0 = s.createQuery("select max(a.id) from Adresse a ");
                int idmax = (int) q0.uniqueResult();
                Query q1 = s.createQuery("select  max(c.idcmd) from Commande c");
                int idmax1 = (int) q1.uniqueResult();
                Query q2 = s.createQuery("select  max(l.idlc) from Lignecommande l");
                int idmax2 = (int) q2.uniqueResult();

                Adresse ad = new Adresse(idmax+1,clt, adresse1, ville, codepostale, pays, null);
                Commande cmd = new Commande(idmax1+1, clt, ad, new Date(), "VISA", "en cours", new ArrayList<Lignecommande>(), null);

                HibernateUtil.beginTransaction();
                EntityManager em = HibernateUtil.beginTransaction();
                int i=1;

                for (LignePanier lp : p.getItems()) {
                    Lignecommande lc = new Lignecommande(idmax2+i, lp.getProduit(), cmd, lp.getQte(), lp.getProduit().getPrix());
                    cmd.getLignecommandesByIdcmd().add(lc);
                    s.save(lc);
                    i++;
                }

                s.save(ad);
                s.save(cmd);
                s.getTransaction().commit();
                s.close();
                qteSF();
                request.getSession().removeAttribute("panier");
                request.setAttribute("msg", "Votre commande est paye avec succes et sera expedier prochainement!");
                request.getRequestDispatcher("/view/mescommandes.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Informations de paiement incorrectes!");
                request.getRequestDispatcher("/view/paiement.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("msg", "Votre solde est insuffisant!");
            request.getRequestDispatcher("/view/paiement.jsp").forward(request, response);}
    }



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void qteSF (){
        HibernateUtil.beginTransaction();
        EntityManager em = HibernateUtil.beginTransaction();

        Query q1= (Query) em.createQuery("select max(idcmd) from Commande");
        int idcmd1 = (int) q1.uniqueResult();

        Query q2= (Query) em.createQuery("select produitByIdp.id from Lignecommande l where l.commandeByIdcmd.idcmd=:idcmd");
        q2.setParameter("idcmd",idcmd1);
        List<Integer> l = q2.list();
        //int id1 = (int) q3.uniqueResult();
        System.out.println(l.get(0));

        Map<Integer,Integer> l2 = new HashMap<>();
        for (int i=0; i<l.size(); i++) {
            Query q3= (Query) em.createQuery("select qte from Lignecommande where commandeByIdcmd.idcmd=:idcmd and produitByIdp.id=:idpro ");
            q3.setParameter("idcmd",idcmd1);
            q3.setParameter("idpro",l.get(i));
            int qte1 = (int) q3.uniqueResult();
            l2.put(l.get(i),qte1);
        }
        System.out.println("qte"+l2.get(0));

        Map<Integer,Integer> l3 = new HashMap<>();
        for (int i=0; i<l.size(); i++) {
            Query q4= (Query) em.createQuery("select qtestck from Produit where idP=:idprod");
            q4.setParameter("idprod",l.get(i));
            int qteS = (int) q4.uniqueResult();
            l3.put(l.get(i),qteS);
        }
        System.out.println("qte"+l3.get(0));
        System.out.println("qte"+l2.get(0));
        for(int i=0; i<l.size(); i++){
            int result = l3.get(l.get(i))-l2.get(l.get(i));
            Query q5= (Query) em.createQuery("update Produit set qtestck=:result1 where idP=:id1P");
            q5.setParameter("id1P",l.get(i));
            q5.setParameter("result1",result);
            int r = q5.executeUpdate();
        }
        HibernateUtil.commitTransaction(em);
        em.close();
    }

    public Integer solde(){
        Session s = HibernateSession.getSession();
        s.beginTransaction();
        Query q7 = s.createQuery("Select solde from Carte");
        Integer solde = (Integer) q7.uniqueResult();
        HibernateUtil.commitTransaction(s);
        s.close();
        return solde;
    }
    public void solde2(Integer mnt, Integer a){
        Session s = HibernateSession.getSession();
        s.beginTransaction();
        Query q8 = s.createQuery("update Carte set solde=:solde1");
        Integer solde2 = a - mnt;
        q8.setParameter("solde1",solde2);
        int result = q8.executeUpdate();
        System.out.println("Ca marche ?"+result);
        s.close();
    }

}