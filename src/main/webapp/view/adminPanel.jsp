<%@ page import="com.ecommerce.metier.Produit" %>
<%@ page import="com.ecommerce.metier.Image" %>
<%@ page import="com.ecommerce.dao.HibernateUtil" %>
<%@ page import="com.ecommerce.metier.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.persistence.Query" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="com.ecommerce.metier.Client" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSP Page</title>
</head><!--/head-->

<body>
<%
	HibernateUtil.beginTransaction();
	EntityManager s=HibernateUtil.getEntityManager();
	Query q= s.createQuery("Select c From Client c");
	List<Client> l=q.getResultList();
%>
<jsp:include page="header.jsp"></jsp:include>

<section id="cart_items">
	<div class="container">
		<div class="breadcrumbs">
			<div class="row">
				<div class="col-sm-2">
					<div class="card">
						<i class="fa fa-users  mb-2" style="font-size: 70px;"></i>
						<a href="">
							<h4 style="color:#A427FDFF;">Total Users: <%=l.size()%></h4></a>
						<h5 style="color:white;">
						</h5>
					</div>
				</div>
			</div>
			<%
				if(request.getAttribute("msg")!=null){
			%>
			<h3 style="color: red"><%=request.getAttribute("msg")%></h3>
			<%
				}
			%>
		</div>
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
				<tr class="cart_menu">
					<td class="image">Id</td>
					<td class="price">Nom</td>
					<td class="total">Droit</td>
					<td class="total">Date d'inscription</td>
				</tr>
				</thead>
				<tbody>
				<%
					for(Client c : l){%>
				<tr>
					<td class="cart_total">
						<b><p><%=c.getIdc()%></p></b>
					</td>
					<td class="cart_total">
						<b><p><%=c.getNom()%></p></b>
					</td>
					<td class="cart_total">
						<b><p><%=c.getDroit()%></p></b>
					</td>
					<td class="cart_total">
						<b><p><%=c.getRegDate()%></p></b>
					</td>
				</tr>
				<%s.close();}%>
				</tbody>
			</table>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/DroitClient">
			<div class="text_box pull-left">
				<input type="text" placeholder="ID client" name="IDC"/>
			</div>
			<input type="hidden" name="droit" value="restricted">&nbsp;&nbsp;&nbsp;
			<button type="submit" class="button_admin">Restreindre droit</button><br>
		</form>
		<br>
		<form method="post" action="${pageContext.request.contextPath}/DroitClient">
			<div class="text_box pull-left">
				<input type="text" placeholder="ID client" name="IDCUn"/>
			</div>
			<input type="hidden" name="droit" value="Unrestricted">&nbsp;&nbsp;&nbsp;
			<button type="submit" class="button_admin">Accorder droit</button>
		</form>
	</div>

</section> <!--/#cart_items-->
<br><br>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
