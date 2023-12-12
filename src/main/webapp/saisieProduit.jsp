<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Saisie Produit</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container col-md-10 col-md-offset-1">
  <div class="panel panel-primary">
    <div class="panel-heading">Saisie d'un produit</div>
    <div class="panel-body">
      <form action="SaveProduit.ca" method="post">
        <div class="form-group">
          <label class="control-label">Désignation:</label>
          <input type="text" name="designation" class="form-control" value="${produit.designation}" required="required">
          <span></span>
        </div>
        
        <div class="form-group">
          <label class="control-label">Prix:</label>
          <input type="text" name="prix" class="form-control" value="${produit.prix}" required="required">
          <span></span>
        </div>
        
        <div class="form-group">
          <label class="control-label">Quantité:</label>
          <input type="text" name="quantite" class="form-control" value="${produit.quantite}" required="required">
          <span></span>
        </div>
        <div>
        <button type="submit" class="btn btn-primary">Save</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>