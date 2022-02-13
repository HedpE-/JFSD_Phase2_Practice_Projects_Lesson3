<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product to database</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.error {
	color: red;
	font-size: 20px;
}
</style>
</head>
<body>
	<h2 align="center">Add product details</h2>
	<div class="container">
		<form action="addProduct" method="post">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">

					<div class="form-group">
						<label for="name">Name:</label> <input type="text"
							class="form-control" id="pname" placeholder="Enter name"
							name="pname">
					</div>

					<div class="form-group">
						<label for="phone">Average Weight in Kg:</label> <input
							type="number" step="0.01" class="form-control" id="avgWeight"
							placeholder="Enter the average weight" name="avgWeight">
					</div>
					<div class="form-group">
						<label for="email">Price in EUR:</label> <input type="number"
							step="0.01" class="form-control" id="price"
							placeholder="Enter price" name="price">
					</div>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="Add Product" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>