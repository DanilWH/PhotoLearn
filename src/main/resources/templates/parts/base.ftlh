<#macro content title>

<#include "user_security.ftlh" />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
    </head>
    <body>
    	<#if _user??>
    		<a href="/tutorials">Уроки</a> -

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
    </body>
</html>

</#macro>