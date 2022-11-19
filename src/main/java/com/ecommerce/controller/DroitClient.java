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
import java.util.List;

@WebServlet(name = "DroitClient", urlPatterns = {"/DroitClient"})
public class DroitClient extends HttpServlet {

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

        String a = request.getParameter("droit");
        HibernateUtil.beginTransaction();
        EntityManager em = HibernateUtil.beginTransaction();

        if (a.equals("restricted")){
            int id = Integer.parseInt(request.getParameter("IDC"));

            //Session s=HibernateSession.getSession();
            String hql = "UPDATE Client set droit = :droit1 " +
                    "WHERE id = :employee_id";
            Query query = (Query) em.createQuery(hql);
            query.setParameter("droit1", "restricted");
            query.setParameter("employee_id", id);
            int result = query.executeUpdate();
            HibernateUtil.commitTransaction(em);
            request.getRequestDispatcher("/view/adminPanel.jsp").forward(request, response);
        }
        else{
            int id1 = Integer.parseInt(request.getParameter("IDCUn"));
            //Session s=HibernateSession.getSession();
            String hql = "UPDATE Client set droit = :droit1 "  +
                    "WHERE id = :employee_id";
            Query query = (Query) em.createQuery(hql);
            query.setParameter("droit1", "null");
            query.setParameter("employee_id", id1);
            int result = query.executeUpdate();
            HibernateUtil.commitTransaction(em);

            request.getRequestDispatcher("/view/adminPanel.jsp").forward(request, response);
        }
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





