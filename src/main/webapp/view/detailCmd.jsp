<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.ecommerce.dao.HibernateUtil" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="com.ecommerce.metier.Commande" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ecommerce.metier.Lignecommande" %>
<%@ page import="com.ecommerce.metier.Produit" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<%
    int idcmd=Integer.parseInt(request.getParameter("idcmd"));
    Session s= HibernateSession.getSession();
    Commande cmd=(Commande)s.get(Commande.class,idcmd);

%>

<section id="do_action">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="#">Accueil</a></li>
                <li class="active">Details de la commande</li>
            </ol>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <div class="chose_area">
                    <ul class="user_option">
                        <li>
                            <label>No cmd :</label>
                            <label><%=cmd.getIdcmd()%></label>
                        </li>
                        <li>
                            <label>Date : </label>
                            <label><%=cmd.getDatecmd()%></label>
                        </li>
                        <li>
                            <label>Date : </label>
                            <label><%=cmd.getEtat()%></label>
                        </li>
                        <li>
                            <label>Methode de paiement</label>
                            <label><%=cmd.getMethodePay()%></label>
                        </li>
                    </ul>

                </div>
            </div>

        </div>
    </div>
</section><!--/#do_action-->

<section id="cart_items">
    <div class="container">

        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Produit</td>
                    <td class="description"></td>
                    <td class="price">Prix</td>
                    <td class="quantity">Quantite</td>
                    <td class="total">Total</td>

                </tr>
                </thead>
                <tbody>
                <%for(Lignecommande lc:cmd.getLignecommandesByIdcmd()){

                    Produit p=lc.getProduitByIdp();
                %>
                <tr>
                    <td class="cart_product">
                        <img src="${pageContext.request.contextPath}/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" width="50px" height="50px" alt="">
                    </td>
                    <td class="cart_description">
                        <h4><a href=""><%=p.getLibelle()%></a></h4>

                    </td>
                    <td class="cart_price">
                        <p>$<%=p.getPrix()%></p>
                    </td>
                    <td class="cart_quantity">
                        <div class="cart_quantity_button">
                            <input class="cart_quantity_input" type="text" name="quantity" value="<%=lc.getQte()%>" autocomplete="off" size="2">

                        </div>
                    </td>
                    <td class="cart_total">
                        <p class="cart_total_price">$<%=lc.getQte()*p.getPrix()%></p>
                    </td>

                </tr>

                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->

<section id="do_action">
    <div class="container">

        <div class="row">
            <div class="col-sm-6">

            </div>
            <div class="col-sm-6">
                <div class="total_area">
                    <ul>
                        <li>Le total de la commande <span>$<%=cmd.total()%></span></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</section><!--/#do_action-->

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
