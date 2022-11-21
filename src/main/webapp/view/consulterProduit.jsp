<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ecommerce.metier.Produit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 18/11/2022
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<section id="cart_items">
<div class="table-responsive cart_info">
    <table class="table table-condensed">
        <thead>
        <tr class="cart_menu">
            <td class="image">Produit</td>
            <td class="description">Libelle</td>
            <td class="price">Prix</td>

            <td></td>
        </tr>
        </thead>
        <tbody>
        <%
            Session s= HibernateSession.getSession();
            List<Produit> l=s.createQuery("From Produit").list();
            for(Produit p:l){
        %>
        <tr>
            <td class="cart_product">
            </td>
            <td class="cart_description">
                <h4><a href=""><%=p.getLibelle()%></a></h4>

            </td>
            <td class="cart_price">
                <p>$<%=p.getPrix()%></p>
            </td>
        </tr>
        <%s.close();}%>
        </tbody>
    </table>
</div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
