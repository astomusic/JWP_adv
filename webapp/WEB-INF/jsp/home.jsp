<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JWP_adv</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/layerpopup.css" rel="stylesheet">
<link href="/css/tumbler.css" rel="stylesheet">


</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">ASTOMUSIC</a>
			</div>
			<div class="navbar-collapse collapse">
				<c:if test="${empty sessionScope.email}">
					<form:form modelAttribute="authenticate"
						cssClass="navbar-form navbar-right" action="/users/login"
						method="post">
						<div class="form-group">
							<c:if test="${not empty errorMessage}">
								<span style="color: red">${errorMessage}</span>
							</c:if>
							<form:input path="email" class="form-control" placeholder="Email" />
							<form:errors path="email" cssClass="error" />
						</div>
						<div class="form-group">
							<form:password path="password" class="form-control"
								placeholder="Password" />
							<form:errors path="password" cssClass="error" />
						</div>
						<button type="submit" class="btn btn-success">Sign in</button>
						<button type="button" class="btn btn-primary" id="layer_open1">Sign
							up</button>
					</form:form>
				</c:if>
				<c:if test="${not empty sessionScope.email}">
					<form:form modelAttribute="authenticate"
						cssClass="navbar-form navbar-right" action="/users/logout"
						method="post">
						<c:if test="${not empty errorMessage}">
							<span style="color: red">${errorMessage}</span>
						</c:if>
						<button type="button" class="btn btn-success" id="layer_open2">${sessionScope.email}</button>
						<button type="submit" class="btn btn-danger">Logout</button>
					</form:form>
				</c:if>
			</div>
		</div>
	</div>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, world!</h1>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<p>
				<a class="btn btn-primary btn-lg" role="button">Learn more
					&raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>Water In Tumbler</h2>
				<div id="testwarp">
					<div id="test"></div>
				</div>
				
				<p>
					<a class="btn btn-default" href="#" role="button">refresh
						&raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>Heading</h2>
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa justo sit amet risus.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
		</div>

		<hr>

		<footer>
			<p>&copy; Company 2014</p>
		</footer>
	</div>

	<div class="layer" style="display: none;">
		<div class="bg"></div>
		<div class="layer_area" id="layer1">	
			<c:choose>
				<c:when test="${empty sessionScope.email}">
					<h3>Welcome! Sign up</h3>
					<form:form modelAttribute="user" cssClass="form-horizontal" action="/users" method="POST">
						<div class="form-group">
							<form:input path="email" class="form-control" placeholder="Email" />
							<form:errors path="email" cssClass="error" />
						</div>
						<div class="form-group">
							<form:password path="password" class="form-control" placeholder="Password" />
							<form:errors path="password" cssClass="error" />
						</div>
						<div class="form-group">
							<form:password path="passwordConfirm" class="form-control" placeholder="Password Confirm" />
							<form:errors path="passwordConfirm" cssClass="error" />
						</div>
						<br>
						<div class="form-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="button" class="btn btn-danger" id="layer1_close">Close</button>
							</div>
						</div>
					</form:form>
				</c:when>
				<c:otherwise>
					<h3>User Information</h3>
					<form:form modelAttribute="ChangeUser" cssClass="form-horizontal" action="/users/modify/${sessionScope.email}" method="PUT">
						<div class="form-group">
							${sessionScope.email}
						</div>
						<div class="form-group">
							<form:password path="oldPassword" class="form-control" placeholder="Old Password" />
							<form:errors path="oldPassword" cssClass="error" />
						</div>
						<div class="form-group">
							<form:password path="newPassword" class="form-control" placeholder="New Password" />
							<form:errors path="newPassword" cssClass="error" />
						</div>
						<div class="form-group">
							<form:password path="newPasswordConfirm" class="form-control" placeholder="New Password Confirm" />
							<form:errors path="newPasswordConfirm" cssClass="error" />
						</div>
						<br>
						<div class="form-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="button" class="btn btn-danger" id="layer2_close">Close</button>
							</div>
						</div>
					</form:form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/layerpopup.js"></script>
	<script src="/js/tumbler.js"></script>
	</script>
</body>
</html>