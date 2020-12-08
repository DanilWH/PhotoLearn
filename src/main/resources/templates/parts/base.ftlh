<#macro content title>

<#include "user_security.ftlh" />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html>
    <head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

		<title>${title}</title>
    </head>
    <body>
	<a href="/tutorials">Уроки</a> -
		<#if _user??>

			<@security.authorize access="hasRole('ROLE_TEACHER')">
				<a href="/tutorial/new">Создать новый урок.</a> -
			</@security.authorize>
    		
    		Здравствуйте, ${_user.username}! -
			<@security.authorize access="hasRole('ROLE_ADMIN')">
				<a href="/admin/users">Пользователи</a>
			</@security.authorize>
    		<form action="/logout" method="post">
    			<input type="hidden" name="_csrf" value="${_csrf.token}" />
    			<button type="submit">Выйти</button>
    		</form>
    	<#else>
    		<a href="/login">Войти</a> -
    		<a href="/registration">Зарегестрироваться</a>
    	</#if>
    	<hr />

        <#nested />

		<!-- Option 2: jQuery, Popper.js, and Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>

</#macro>