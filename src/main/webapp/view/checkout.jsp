<%@ page import="com.ecommerce.metier.Client" %>
<%@ page import="com.ecommerce.metier.Panier" %>
<%@ page import="com.ecommerce.metier.LignePanier" %>
<%@ page import="com.ecommerce.metier.Produit" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
    if(session.getAttribute("clt")==null){
        request.setAttribute("msg","Il faut se connecter ou s'inscrire pour passer votre commande !");
        request.getRequestDispatcher("/views/formLogin.jsp").forward(request, response);
    }
    Client c=(Client)session.getAttribute("clt");
%>
<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="#">Acceuil</a></li>
                <li class="active">Gérer mes commandes</li>
            </ol>
        </div><!--/breadcrums-->

        <div class="step-one">
            <h2 class="heading">Etape 1</h2>
        </div>

        <form action="paiement.jsp" method="post">


            <div class="shopper-informations">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="shopper-info">
                            <p>Information du client</p>
                            <% if (c !=null){%>
                            <input type="text" value="<%=c.getNom()%>">
                            <input type="text" value="<%=c.getPrenom()%>">
                            <%}%>



                        </div>
                    </div>
                    <div class="col-sm-5 clearfix">
                        <div class="bill-to">
                            <p>Expedier a :</p>
                            <div class="form-one">
                                <input type="text" placeholder="Address 1 *" required name="adresse1">
                                <input type="text" placeholder="Address 2" name="adresse2">
                            </div>
                            <div class="form-two">

                                <input type="text" placeholder="Zip / Postal Code *" name="codepostale">
                                <select name="pays">
                                    <option>-- Pays --</option>
                                    <option value="France">France</option>
                                    <option value="Maroc">Maroc</option>
                                    <option value="United States">United States</option>
                                    <option value="Bangladesh">Bangladesh</option>
                                    <option value="UK">UK</option>
                                    <option value="India">India</option>
                                    <option value="Pakistan">Pakistan</option>
                                    <option value="Ucrane">Ucrane</option>
                                    <option value="Canada">Canada</option>
                                    <option value="Dubai">Dubai</option>
                                </select>
                                <select name="ville">
                                    <option>-- ville --</option>
                                    <option value="Cergy">Cergy</option>
                                    <option value="Paris">Paris</option>
                                    <option value="Lile">Lile</option>
                                    <option value="Nancy">Nancy</option>

                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="review-payment">
                <h2>Revision de votre panier & paiement</h2>
            </div>

            <div class="table-responsive cart_info">
                <table class="table table-condensed">
                    <thead>
                    <tr class="cart_menu">
                        <td class="image">Produit</td>
                        <td class="description">Description</td>
                        <td class="price">Prix</td>
                        <td class="quantity">Quantite</td>
                        <td class="total">Total</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        Panier  panier = (Panier) session.getAttribute("panier");

                        for (LignePanier lp : panier.getItems()) {

                            Produit  p = lp.getProduit();


                    %>

                    <tr>
                        <td class="cart_product">
                            <a href=""><img src="${pageContext.request.contextPath}/images/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" height="50px" width="50px"alt=""></a>
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
                            </div>َ
                        </td>
                        <td class="cart_total">
                            <p class="cart_total_price">$<%=p.getPrix()*lp.getQte()%></p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                        </td>
                    </tr>

                    <%}%>
                    </tbody>
                </table>
            </div>
            <div class="payment-options">

					<span>
						<label><input type="checkbox"> Carte Visa</label>
					</span>



                <button type="submit" class="btn btn-primary" href="">Continuer</button>
            </div>
        </form>
    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>