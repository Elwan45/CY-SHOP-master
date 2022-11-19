<%@ page import="com.ecommerce.metier.Panier" %>
<%@ page import="com.ecommerce.metier.LignePanier" %>
<%@ page import="com.ecommerce.metier.Produit" %>
<%@ page import="com.ecommerce.metier.Client" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<% Client clt=null;
    if(session.getAttribute("clt")!=null){
        clt=(Client) session.getAttribute("clt");
    }
%>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="#">Accueil</a></li>
                <li class="active">Panier</li>
            </ol>
        </div>
        <%
            if(session.getAttribute("panier")==null){
        %>
        <h2>Votre panier est vide</h2>
        <%
        }else{
            Panier panier=(Panier)session.getAttribute("panier");

        %>
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Produit</td>
                    <td class="description"></td>
                    <td class="price">Prix</td>
                    <td class="quantity">Quantite</td>
                    <td class="total">Total</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <%for(LignePanier lp:panier.getItems()){

                    Produit p=lp.getProduit();
                %>
                <tr>
                    <td class="cart_product">
                        <a href=""><img src="${pageContext.request.contextPath}/images/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" width="50px" height="50px" alt=""></a>
                    </td>
                    <td class="cart_description">
                        <h4><a href=""><%=p.getLibelle()%></a></h4>

                    </td>
                    <td class="cart_price">
                        <p>$<%=p.getPrix()%></p>
                    </td>
                    <td class="cart_quantity">
                        <div class="cart_quantity_button">
                            <a class="cart_quantity_up" href="${pageContext.request.contextPath}/GererPanier?action=augqte&idp=<%=p.getIdP()%>"> + </a>
                            <input class="cart_quantity_input" type="text" name="quantity" value="<%=lp.getQte()%>" autocomplete="off" size="2">
                            <a class="cart_quantity_down" href="${pageContext.request.contextPath}/GererPanier?action=dimqte&idp=<%=p.getIdP()%>"> - </a>
                        </div>
                    </td>
                    <td class="cart_total">
                        <p class="cart_total_price">$<%=lp.getQte()*p.getPrix()%></p>
                    </td>
                    <td class="cart_delete">
                        <a class="cart_quantity_delete" href="${pageContext.request.contextPath}/GererPanier?action=supp&idp=<%=p.getIdP()%>"><i class="fa fa-times"></i></a>
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

            <div class="col-sm-6">
                <div class="total_area">
                    <ul>
                        <li>Le sous total du panier <span>$<%=panier.total()%></span></li>
                        <li>Frais d'expedition <span><%=panier.fraisexpedition()%></span></li>
                        <li>Total <span>$<%=panier.fraisexpedition()+panier.total()%></span></li>
                    </ul>
                    <%if(clt!=null){%>
                    <a href="${pageContext.request.contextPath}/view/checkout.jsp"class="btn btn-default check_out">Payer</a><%}%>
                </div>
            </div>
        </div>
    </div>
</section><!--/#do_action-->
<%}%>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>