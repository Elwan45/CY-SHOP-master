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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        long nocarte = Long.parseLong(request.getParameter("nocarte"));
        String datecarte = request.getParameter("datecarte");
        int crypto = Integer.parseInt(request.getParameter("crypto"));
        //if(nocarte.equals("123456789")&&datecarte.equals("12/21")&&crypto.equals("123")){
        Panier p = (Panier) request.getSession().getAttribute("panier");
        float mnt = 0;
        for (LignePanier lp2 : p.getItems()) {
            mnt += lp2.getQte() * lp2.getProduit().getPrix();
        }

        Query q = s.createQuery("Select c from Carte c where  c.dateExpiration=:datecarte and c.numero=:nocarte and c.crypto=:crypto");
        q.setLong("nocarte", nocarte);
        q.setString("datecarte", datecarte);
        q.setInteger("crypto", crypto);
        List<Carte> l = q.list();
        if (l.size() > 0) {

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
                /*Query q4 = s.createQuery("Select p.qtestck from Produit p where p.idP=:idPr");
                q4.setParameter("idPr",lp.getProduit().getIdP());
                int idmax4 = (int) q4.uniqueResult();
                Query q3 = s.createQuery("UPDATE Produit p set p.qtestck=:deltaS WHERE p.idP =:idPr ");
                int result = idmax4 - lp.getQte();
                q3.setParameter("deltaS",result);
                q3.setParameter("idPr",lp.getProduit().getIdP());
                HibernateUtil.commitTransaction(s);*/
            }

            s.beginTransaction();
            s.save(ad);
            s.save(cmd);
            s.getTransaction().commit();
            request.getSession().removeAttribute("panier");
            request.setAttribute("msg", "Votre commande est paye avec succes et sera expedier prochainement!");
            request.getRequestDispatcher("/view/mescommandes.jsp").forward(request, response);
            s.close();
        } else {
            request.setAttribute("msg", "Informations de paiement incorrectes!");
            request.getRequestDispatcher("/view/paiement.jsp").forward(request, response);
        }
        //HibernateUtil.commitTransaction(em);
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
}