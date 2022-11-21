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
import java.util.Date;

@WebServlet(name = "Inscription", urlPatterns = {"/Inscription"})
public class Inscription extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inscription</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inscription at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String email=request.getParameter("email");
        String mdp=request.getParameter("mdp");
        HibernateUtil.beginTransaction();
        EntityManager em = HibernateUtil.beginTransaction();
        //Session s=HibernateSession.getSession();

        /*Session s= HibernateSession.getSession();
        s.beginTransaction();*/

        Query q= (Query) em.createQuery("Select max(c.idc) from Client c");
        int idmax = (int) q.uniqueResult();

        Compte cpt=new Compte(email, mdp, "client", null, null, null, null);
        Client clt=new Client(idmax+1, cpt , "null", prenom, email, null,null,new Date(), null, null);

        em.persist(cpt);
        em.persist(clt);
        request.setAttribute("msg", "Vous etes inscrit avec succes. Connectez vous maintenant.");
        request.getRequestDispatcher("/view/formLogin.jsp").forward(request, response);
        HibernateUtil.commitTransaction(em);
        em.close();
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


