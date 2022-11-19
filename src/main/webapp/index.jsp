<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.metier.Produit" %>
<%@ page import="com.ecommerce.metier.Image" %>
<%@ page import="com.ecommerce.dao.HibernateUtil" %>
<%@ page import="com.ecommerce.metier.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.persistence.Query" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<!DOCTYPE html>
<html>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="view/header.jsp"></jsp:include>

<%  HibernateUtil.beginTransaction();
    EntityManager s=HibernateUtil.getEntityManager();
    Query q=s.createQuery("Select p From Produit p");
    List<Produit> l=q.getResultList();
    List<Categorie> lc=s.createQuery("select c from Categorie c").getResultList();
%>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Categories</h2>
                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                        <%for(Categorie c : lc){	%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="view/rechercher.jsp?idc=<%=c.getIdcat()%>&mot="><%=c.getLibelle()%></a></h4>
                            </div>
                        </div>
                        <%}
                        %>
                    </div>  <!--/category-products-->
                    <div class="price-range"><!--price-range-->
                        <h2>Price Range</h2>
                        <div class="well text-center">
                            <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                            <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
                        </div>
                    </div><!--/price-range-->
                </div>
            </div>

            <div class="col-sm-9  padding-right">
                <div class="features_items "><!--features_items-->
                    <h2 class="title text-center">Nouvelle collection</h2>

                    <%
                        q=s.createQuery("Select p from Produit p order by p.datePub desc");
                        q.setFirstResult(0);
                        q.setMaxResults(9);
                        l=q.getResultList();
                        for(Produit p:l){
                    %>
                    <div class="col-sm-4">
                        <div class="product-image-wrapper">
                            <div class="single-products">
                                <div class="productinfo text-center">
                                    <img src="images/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" alt="" />
                                    <h2>$<%=p.getPrix()%></h2>
                                    <p><%=p.getLibelle()%></p>
                                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</a>
                                </div>
                                <div class="product-overlay">
                                    <div class="overlay-content">
                                        <h2>$<%=p.getPrix()%></h2>
                                        <p><%=p.getLibelle()%></p>
                                        <a href="view/detailProduit.jsp?idp=<%=p.getIdP()%>" class="btn btn-default add-to-cart"><i class="fa fa-plus-square"></i>Detail du produit</a>
                                        <a href="GererPanier?action=ajouter&idp=<%=p.getIdP()%>&qte=1" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <%
                        }
                        HibernateUtil.commitTransaction(s);%>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="view/footer.jsp"></jsp:include>
</body>
</html>