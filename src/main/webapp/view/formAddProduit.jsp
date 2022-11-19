<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ecommerce.dao.HibernateSession" %>
<%@ page import="com.ecommerce.metier.Categorie" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <a href="${pageContext.request.contextPath}/view/consulterProduit.jsp"><i class="fa fa-user"></i>Consulter les produits</a>

    <%
        if(request.getAttribute("msg")!=null){
    %>
    <h3 style="color: red"><%=request.getAttribute("msg")%></h3>
    <%
        }
    %>
    <form class="was-validated"  action="${pageContext.request.contextPath}/AddProduit" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="validationTextarea">Libelle</label>
            <input type="text" name="libelle" required="" class="form-control">
        </div>
        <div class="mb-3">
            <label for="validationTextarea">Marque</label>
            <input type="text" name="marque" required="" class="form-control">
        </div>
        <div class="mb-3">
            <label for="validationTextarea">prix</label>
            <input type="text" name="prix" required="" class="form-control">
        </div>
        <div class="mb-3">
            <label for="validationTextarea">Frais d'expedition</label>
            <input type="text" name="frais" required="" class="form-control">
        </div>

        <div class="mb-3">
            <label for="validationTextarea">Description</label>
            <textarea class="form-control is-invalid" name="description" id="validationTextarea" placeholder="Required example textarea" required></textarea>
            <div class="invalid-feedback">
                Entrer une description.
            </div>
        </div>

        <div class="custom-control custom-checkbox mb-3">
            <input type="checkbox" class="custom-control-input" id="customControlValidation1" name="disponible">
            <label class="custom-control-label" for="customControlValidation1">Disponible</label>

        </div>
        <div class="mb-3">
            <label for="validationTextarea">Quantite en stock</label>
            <input type="text" name="qte" required="" class="form-control">
        </div>



        <div class="mb-3">
            <select class="custom-select" required name="cat">
                <option value="">Choisir une categorie...</option>

                <%
                    Session s= HibernateSession.getSession();
                    List<Categorie> cats=s.createQuery("From Categorie").list();
                    for(Categorie c:cats){
                %>
                <option value="<%=c.getIdcat()%>"><%=c.getLibelle()%></option>
                <%}%>
            </select>

        </div>

        <div class="custom-file mb-3">
            <input type="file" class="custom-file-input" name="img1" id="validatedCustomFile" required>
            <label class="custom-file-label" for="validatedCustomFile">Choisir l'image principale de votre produit...</label>

        </div>





        <div class="input-group is-invalid">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="validatedInputGroupCustomFile2" name="img2">
                <label class="custom-file-label" for="validatedInputGroupCustomFile2">Choisir Image 2 (facultatif)...</label>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="validatedInputGroupCustomFile3" name="img3">
                <label class="custom-file-label" for="validatedInputGroupCustomFile3">Choisir Image  3 (facultatif)...</label>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="validatedInputGroupCustomFile4" name="img4">
                <label class="custom-file-label" for="validatedInputGroupCustomFile4">Choisir Image 4 (facultatif)...</label>
            </div>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Sauvegarder</button>
            </div>
        </div>

    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
