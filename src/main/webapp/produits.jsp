<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Produits</title>
</head>
<body>
<br>
<%@include file="header.jsp" %>
<div class="container col-md-10 col-md-offset-1">
  <div class="panel panel-primary">
    <div class="panel-heading">Recherche des produits</div>
    <div class="panel-body">
      <form action="chercher.ca" method="get">
        <label>Mot Clé:</label>
        <input type="text" name="motCle" value="${model.motCle}">
        <button type="submit" class="btn btn-primary">Chercher</button>
      </form>
      <table class="table table-striped">
        <tr>
          <th>ID</th><th>Designation</th><th>Prix</th><th>Quantité</th>
        </tr>
        <c:forEach items="${model.produits}" var="p">
          <tr>
            <td>${p.id}</td>
            <td>${p.designation}</td>
            <td>${p.prix}</td>
            <td>${p.quantite}</td>
            <td><a onclick="return confirm('Etes Vous sure?')" href="Supprimer.ca?id=${p.id}" >Supprimer</a></td>
            <td><a href="Editer.ca?id=${p.id}" >Editer</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>
</body>
</html>