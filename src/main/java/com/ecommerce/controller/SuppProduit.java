package com.ecommerce.controller;

import com.ecommerce.dao.HibernateSession;
import com.ecommerce.dao.HibernateUtil;
import com.ecommerce.metier.Client;
import com.ecommerce.metier.Panier;
import com.ecommerce.metier.Produit;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SuppProduit", urlPatterns = {"/SuppProduit"})
public class SuppProduit extends HttpServlet {

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
            out.println("<title>Servlet GererPanier</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GererPanier at " + request.getContextPath() + "</h1>");
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

        String action=request.getParameter("action");

        if(action.equals("supp")) {
            /*HibernateUtil.beginTransaction();
            EntityManager s=HibernateUtil.getEntityManager();
            int idp1 = Integer.parseInt(request.getParameter("idp"));
            String hql = "DELETE FROM Produit p WHERE p.idP=:idp2";
            Query q = s.createQuery(hql);
            q.setParameter("idp2", idp1);
            HibernateUtil.commitTransaction(s);
            request.setAttribute("msg", "Le produit a été supprimer avec succés");
            request.getRequestDispatcher("/view/listArticle.jsp").forward(request, response);*/

            HibernateUtil.beginTransaction();
            EntityManager s=HibernateUtil.getEntityManager();
            try
            {
                int idp1 = Integer.parseInt(request.getParameter("idp"));
                Query q =s.createQuery("delete from Produit where idP=:userId");
                q.setParameter("userId",idp1);

                HibernateUtil.commitTransaction(s);
                request.getRequestDispatcher("/view/listArticle.jsp").forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                s.close();
            }
        }

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
        processRequest(request, response);
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

