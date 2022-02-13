<%@page import="jfsd.assessments.phase2.entities.Product" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Product</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<% Product prod = (Product)request.getAttribute("product"); %>
	<h2 align="center">Product details</h2><br/>
	<div class="container" style="max-width:200px;">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="form-group">
					ID: <%=prod.getPid()%> 
				</div>
				<div class="form-group">
					Name: <%=prod.getPname()%>
				</div>
				<div class="form-group">
					Average Weight: <%= String.format("%.2f", prod.getAvgWeight()) %> kg
				</div>
				<div class="form-group">
					Price: <%= String.format("%.2f", prod.getPrice()) %> EUR
				</div>
			</div>
		</div><br/>
	</div>
</body>
</html>