<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<title>Formulário do Aluno</title>
</head>
<body>
	<header>

        	</header>
	<div class="container">
		<div class="card">
			<h3 class="card-header">Formulário de Aluno:</h3>
			<form:form action="${s:mvcUrl('AC#create').build()}" method="POST"
				modelAttribute="autor" enctype="multipart/form-data">
				<div class="card-body">
					<label class="col-sm-2 col-form-label"
						for="validationServerUsername"><b>Nome</b></label>
					<form:input path="nome" class="form-control" />
					<p style="color: green;">${msgm}</p>
					<form:errors path="nome" style="color: red;" />
				</div>
				<div class="card-body">
					<button type="submit" class="btn btn-outline-primary">CADASTRAR</button>
				</div>
			</form:form>
		</div>
	</div>

	<footer class="card text-bg-dark mb-3"
		style="max-width: 80rem; margin-top: 240px;">
		<div class="card-header" style="padding-left: 557px;">
			<h4>SYSTEMGYM</h4>
		</div>
		<div class="card-body">
			<h5 class="card-title" style="padding-left: 535px;">Producted By</h5>
			<p class="card-text" style="padding-left: 500px;">Caio Rocha</p>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>