<#import "parts/base.ftlh" as base />

<@base.content title="Вход | Photo Learn">

        <form action="/login" method="post">
        	<input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><label> Имя пользователя : <input type="text" name="username"/> </label></div>
            <div><label> Пароль: <input type="password" name="password"/> </label></div>
            <div><button type="submit">Войти</button></div>
        </form>
        
</@base.content>