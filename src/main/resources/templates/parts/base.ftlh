<#macro content title>

<#include "user_security.ftlh" />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en">
    <head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

		<title>${title}</title>
    </head>
    <body>
		<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
			<a class="navbar-brand" href="/tutorials">PhotoLearn</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a class="nav-link mr-3" href="/tutorials">Уроки <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<form action="/tutorials" method="get" class="form-inline">
							<input class="form-control mr-sm-2" type="search" name="tutorial-search"
								   value="${tutorialsSearchValue?ifExists}" placeholder="Найти урок" aria-label="Search"/>
							<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Найти</button>
						</form>
					</li>
				</ul>
				<ul class="navbar-nav">
					<#if _user??>
						<!-- Greeting. -->
						<li class="nav-item">
							<a class="nav-link mr-1">Здравствуйте, ${_user.username}!</a>
						</li>

						<!-- The admin operations dropdown list. -->
						<@security.authorize access="hasRole('ROLE_ADMIN')">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Операции админа
								</a>
								<div class="dropdown-menu" aria-labelledby="adminDropdown">
									<a class="dropdown-item" href="/admin/users">Пользователи</a>
									<!-- Add some more links here. -->
								</div>
							</li>
						</@security.authorize>

						<!-- The "Create a new tutorial" link-->
						<@security.authorize access="hasRole('ROLE_TEACHER')">
							<li class="nav-item">
								<a class="btn btn-outline-info mx-3" href="/tutorial/new">Создать новый урок.</a>
							</li>
						</@security.authorize>

						<!-- The "Log out" button. -->
						<li class="nav-item">
							<form action="/logout" method="post">
								<input type="hidden" name="_csrf" value="${_csrf.token}" />
								<button type="submit" class="btn btn-outline-light my-2 my-sm-0">Выйти</button>
							</form>
						</li>
					<#else>
						<li class="nav-item">
							<a class="nav-link" href="/login">Войти</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/registration">Зарегестрироваться</a>
						</li>
					</#if>
				</ul>
			</div>
		</nav>

		<div class="container my-5" style="width: 100%; height: 90%;">
			<#nested />
		</div>

		<!-- JavaScript files.-->

		<!-- jQuery, Popper.js, and Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>

</#macro>

<#macro header>

<h1 class="display-4"><#nested /></h1>
<hr />

</#macro>