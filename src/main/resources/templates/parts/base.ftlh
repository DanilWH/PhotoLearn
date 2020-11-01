<#macro content title>

<#include "user_security.ftlh" />

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
    </head>
    <body>
    	<#if _user??>
    		Здравствуйте, ${user.username}! -
    		<form action="/logout" method="post">
    			<input type="hidden" name="_csrf" value="${_csrf.token}" />
    			<button type="submit">Выйти</button>
    		</form>
    	<#else>
    		<a href="/login">Войти</a>
    	</#if>
        <#nested />
    </body>
</html>

</#macro>