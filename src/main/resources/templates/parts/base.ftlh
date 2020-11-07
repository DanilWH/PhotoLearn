<#macro content title>

<#include "user_security.ftlh" />

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <script
        		src="https://cdn.tiny.cloud/1/q0kqyzka6qc7uvpaz9yl7hki9vlqhs4snpkw05o6t9lqjkq2/tinymce/5/tinymce.min.js"
        		referrerpolicy="origin"
        ></script>
        <script>
        tinymce.init({
            selector: 'textarea',
            plugins: 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
            toolbar: 'a11ycheck casechange checklist code pageembed permanentpen table',
            toolbar_mode: 'floating',
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
          });
	  </script>
    </head>
    <body>
    	<#if _user??>
    		Здравствуйте, ${_user.username}! -
    		<a href="/tutorial/new">Создать новый урок.</a> -
    		<form action="/logout" method="post">
    			<input type="hidden" name="_csrf" value="${_csrf.token}" />
    			<button type="submit">Выйти</button>
    		</form>
    	<#else>
    		<a href="/login">Войти</a> -
    		<a href="/registration">Зарегестрироваться</a>
    	</#if>
    	
        <#nested />
    </body>
</html>

</#macro>