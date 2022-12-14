<%@ page import="com.ecommerce.metier.Client" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="com.ecommerce.metier.Commande" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>

<%
    if(session.getAttribute("clt")==null){
        request.setAttribute("msg","Il faut se connecter ou s'inscrire pour passer votre commande!");
        request.getRequestDispatcher("/view/formLogin.jsp").forward(request, response);
    }
    Client c=(Client)session.getAttribute("clt");

%>


<jsp:include page="header.jsp"></jsp:include>


<%
    Session s= HibernateSession.getSession();
    Query q=s.createQuery("Select c from Commande c where c.clientByIdc.idc=:idc");
    q.setInteger("idc", c.getIdc());
    List<Commande> cmds=q.list();
%>


<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="#">Accueil</a></li>
                <li class="active">Liste de mes commandes</li>
            </ol>
        </div>
        <%
            if(request.getAttribute("msg")!=null){
        %>
        <h3 style="color: red"><%=request.getAttribute("msg")%></h3>
        <%
            }
        %>
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">No decommande</td>

                    <td class="price">Date</td>
                    <td class="quantity">etat</td>
                    <td class="total">Total</td>

                    <td></td>
                </tr>
                </thead>
                <tbody>
                <%for(Commande cmd:cmds){


                %>
                <tr>
                    <td class="cart_product">
                        <%=cmd.getIdcmd()%>
                    </td>

                    <td class="cart_price">
                        <%=cmd.getDatecmd()%>
                    </td>
                    <td class="cart_quantity">
                        <div class="cart_quantity_button">
                            <p><%=cmd.getEtat()%></p>
                        </div>
                    </td>
                    <td class="cart_total">
                        <p class="cart_total_price">$<%=cmd.total()%></p>
                    </td>
                    <td class="cart_delete">
                        <a class="cart_quantity_delete" href="${pageContext.request.contextPath}/view/detailCmd.jsp?idcmd=<%=cmd.getIdcmd()%>"><i class="fa fa-eye"></i></a>
                    </td>
                </tr>

                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

