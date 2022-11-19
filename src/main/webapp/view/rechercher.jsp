<%@ page contentType="text/html; charset=ISO-8859-1"
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
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%  HibernateUtil.beginTransaction();
    EntityManager s= HibernateUtil.getEntityManager();
    List<Categorie> lc=s.createQuery("select c from Categorie c",Categorie.class).getResultList();
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
                                <h4 class="panel-title"><a href="rechercher.jsp?idc=<%=c.getIdcat()%>&mot="><%=c.getLibelle()%></a></h4>
                            </div>
                        </div>
                        <%}%>





                    </div><!--/category-products-->



                    <div class="price-range"><!--price-range-->
                        <h2>Prix</h2>
                        <div class="well text-center">
                            <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                            <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
                        </div>
                    </div><!--/price-range-->

                </div>
            </div>

            <div class="col-sm-9 padding-right">
                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Resultat de la recherche</h2>

                    <%
                        String mot=request.getParameter("mot");
                        if(mot==null){
                            mot="";
                        }
                        String req="Select p from Produit p where  lower(p.libelle) like '%"+mot.toLowerCase()+"%'";
                        if(request.getParameter("idc")!=null){
                            int idc=Integer.parseInt(request.getParameter("idc"));
                            req+=" and p.categorieByIdcat.idcat="+idc;
                        }
                        Query q=s.createQuery(req,Produit.class);

                        List<Produit>l=q.getResultList();
                        if(l.size()==0){
                    %>
                    <h2>Aucun resultat trouve !!</h2>
                    <%
                    }else{
                        for(Produit p:l){
                    %>

                    <div class="col-sm-4">
                        <div class="product-image-wrapper">
                            <div class="single-products">
                                <div class="productinfo text-center">
                                    <img src="${pageContext.request.contextPath}/images/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" alt="" />
                                    <h2>$<%=p.getPrix()%></h2>
                                    <p><%=p.getLibelle()%></p>
                                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</a>
                                </div>
                                <div class="product-overlay">
                                    <div class="overlay-content">
                                        <h2>$<%=p.getPrix()%></h2>
                                        <p><%=p.getLibelle()%></p>
                                        <a href="${pageContext.request.contextPath}/detailProduit.jsp?idp=<%=p.getIdP()%>" class="btn btn-default add-to-cart"><i class="fa fa-plus-square"></i>Detail du produit</a>
                                        <a href="${pageContext.request.contextPath}/GererPanier?action=ajouter&idp=<%=p.getIdP()%>&qte=1" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <%}

                    }
                        HibernateUtil.commitTransaction(s);%>

                </div><!--features_items-->



            </div>
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
