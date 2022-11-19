<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.dao.HibernateUtil" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.ecommerce.metier.*" %>
<%@ page import="org.hibernate.query.Query" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%  Client clt=null;
    if(session.getAttribute("clt")!=null){
        clt=(Client) session.getAttribute("clt");
    }

    Session s= HibernateSession.getSession();
    List<Categorie> lc=s.createQuery("from Categorie").list();
    HibernateUtil.beginTransaction();
    EntityManager em = HibernateUtil.getEntityManager();
%>



<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Categorie</h2>
                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->


                        <%for(Categorie c : lc){	%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="${pageContext.request.contextPath}/view/rechercher.jsp?idc=<%=c.getIdcat()%>&mot="><%=c.getLibelle()%></a></h4>
                            </div>
                        </div>
                        <%}%>
                    </div><!--/category-products-->
                </div>
            </div>
            <%
                int idp=Integer.parseInt(request.getParameter("idp"));
                Produit p=(Produit)s.get(Produit.class, idp);
            %>
            <div class="col-sm-9 padding-right">
                <div class="product-details"><!--product-details-->
                    <div class="col-sm-5">
                        <div class="view-product">
                            <img src="${pageContext.request.contextPath}/images/produit/<%=p.getImagesByIdP().get(0).getUrl()%>" alt="" />
                            <h3>ZOOM</h3>
                        </div>
                        <div id="similar-product" class="carousel slide" data-ride="carousel">

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <%
                                    List<Image> im=p.getImagesByIdP();
                                    for(int j=0;j<im.size();j=j+3){
                                        Image img1=im.get(j);
                                        Image img2=null;
                                        Image img3=null;
                                        try{
                                            img2=im.get(j+1);
                                            img3=im.get(j+2);
                                        }catch(Exception e){

                                        }
                                %>

                                <div class="item <%=(j==0)?"active":""%>">
                                    <a href=""><img width="50px" height="50px" src="${pageContext.request.contextPath}/images/produit/<%=img1.getUrl()%>" alt=""></a>
                                    <%if(img2!=null){%>
                                    <a href=""><img width="50px" height="50px" src="${pageContext.request.contextPath}/images/produit/<%=img2.getUrl()%>" alt=""></a>
                                    <%}%>
                                    <%if(img3!=null){%>
                                    <a href=""><img width="50px" height="50px" src="${pageContext.request.contextPath}/images/produit/<%=img3.getUrl()%>" alt=""></a>
                                    <%}%>

                                </div>
                                <%}%>

                            </div>

                            <!-- Controls -->

                            <a class="left item-control" href="#similar-product" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a class="right item-control" href="#similar-product" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>

                    </div>
                    <div class="col-sm-7">
                        <div class="product-information"><!--/product-information-->
                            <form action="${pageContext.request.contextPath}/GererPanier" method="get">
                                <h2><%=p.getLibelle()%></h2>
                                <p><%=p.getCategorieByIdcat().getLibelle()%></p>
                                <img src="images/product-details/rating.png" alt="" />
                                <span>
									<span>US $<%=p.getPrix()%></span>
									<label>Quantite :</label>
									<input type="text" name="qte" value="3" />

                                                                        <button type="submit" class="btn btn-fefault cart">
										<i class="fa fa-shopping-cart"></i>
										Ajouter au panier
									</button>
								</span>
                                <input type="hidden" name="idp" value="<%=p.getIdP()%>">
                                <input type="hidden" name="action" value="ajouter">
                            </form>
                            <p><b>Quantite en stock</b> <%=p.getQtestck()%></p>
                            <p><b>Condition:</b> Nouveau</p>
                            <p><b>Marque</b> <%=p.getMarque()%></p>
                            <a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
                        </div><!--/product-information-->
                    </div>
                </div><!--/product-details-->

                <div class="category-tab shop-details-tab"><!--category-tab-->
                    <div class="col-sm-12">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#review" data-toggle="tab">Commentaire</a></li>
                        </ul>
                    </div>
                    <div class="tab-pane fade active in" id="review" >
                        <div class="col-sm-12">
                            <%
                                if(clt!=null && clt.getDroit().equals("null")){%>
                            <p><b>Laisser un commentaire</b></p>

                            <form action="#">
										<span>
											<input type="text" placeholder="Votre nom"/>
											<input type="email" placeholder="Votre adresse"/>
										</span>
                                <textarea name="" ></textarea>
                                <button type="button" class="btn btn-default pull-right">
                                    Envoyer
                                </button>
                            </form>
                            <%
                                }%>
                        </div>
                    </div>

                </div>
            </div><!--/category-tab-->

            <div class="recommended_items"><!--recommended_items-->
                <h2 class="title text-center">Article recommandé</h2>

                <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <%

                            List<Produit> lp=p.getCategorieByIdcat().getProduitsByIdcat();
                            for(int j=0;j<lp.size();j=j+3){

                                Produit pr1=lp.get(j);
                                Produit pr2=null;

                                Produit pr3=null;
                                try{
                                    pr2=lp.get(j+1);
                                    pr3=lp.get(j+2);
                                }catch(Exception e){}

                        %>
                        <div class="item <%=(j==0)? "active":""%>">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="${pageContext.request.contextPath}/images/produit/<%=pr1.getImagesByIdP().get(0).getUrl()%>" alt="" />
                                            <h2>$<%=pr1.getPrix()%></h2>
                                            <p><%=pr1.getLibelle()%></p>
                                            <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% if(pr2!=null) {%>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="${pageContext.request.contextPath}/images/produit/<%=pr2.getImagesByIdP().get(0).getUrl()%>" alt="" />
                                            <h2>$<%=pr2.getPrix()%></h2>
                                            <p><%=pr2.getLibelle()%></p>
                                            <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                            <% if(pr3!=null) {%>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="${pageContext.request.contextPath}/images/produit/<%=pr3.getImagesByIdP().get(0).getUrl()%>" alt="" />
                                            <h2>$<%=pr3.getPrix()%></h2>
                                            <p><%=pr3.getLibelle()%></p>
                                            <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Ajouter au panier</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>

                        </div>
                        <%}%>
                    </div>
                    <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                        <i class="fa fa-angle-right"></i>
                    </a>
                </div>
            </div><!--/recommended_items-->

        </div>
    </div>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
