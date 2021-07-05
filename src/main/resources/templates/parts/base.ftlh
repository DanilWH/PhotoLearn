<#macro content title>

<#include "user_security.ftlh" />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en">
    <head>
		<!-- Required meta tags -->
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous" />

		<title>${title}</title>

		 <script src="https://www.google.com/recaptcha/api.js"></script>
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
							<button class="btn btn-outline-success my-2 my-sm-0" type="submit">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
									<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
									<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
								</svg>
								Найти
							</button>
						</form>
					</li>
				</ul>
				<ul class="navbar-nav">
					<#if _user??>
						<!-- Greeting. -->
						<li class="nav-item">
							<a id="greeting" class="nav-link mr-1">Здравствуйте, ${_user.username}!</a>
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
								<a class="btn btn-outline-info mx-3" href="/tutorial/new" id="create-new-tutorial-button">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen-fill" viewBox="0 0 16 16">
										<path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
									</svg>
									Создать новый урок.
								</a>
							</li>
						</@security.authorize>

						<!-- The "Log out" button. -->
						<li class="nav-item">
							<form action="/logout" method="post">
								<input type="hidden" name="_csrf" value="${_csrf.token}" />
								<button type="submit" class="btn btn-outline-light my-2 my-sm-0">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
										<path fill-rule="evenodd" d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2v13h1V2.5a.5.5 0 0 0-.5-.5H11zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z" />
									</svg>
									Выйти
								</button>
							</form>
						</li>
					<#else>
						<li class="nav-item">
							<a class="nav-link" href="/login">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed-fill" viewBox="0 0 16 16">
									<path fill-rule="evenodd" d="M4 1a1 1 0 0 0-1 1v13H1.5a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2a1 1 0 0 0-1-1H4zm2 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
								</svg>
								Войти
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/registration">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus-fill" viewBox="0 0 16 16">
									<path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
								</svg>
								Зарегистрироваться
							</a>
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