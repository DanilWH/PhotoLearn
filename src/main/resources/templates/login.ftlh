<#import "parts/base.ftlh" as base />

<@base.content title="Вход | Photo Learn">

    <@base.header>Вход</@base.header>

    <#if Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

    <form action="/login" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div class="form-group">
            <label for="username_id">Имя пользователя</label>
            <input id="username_id" type="text" class="form-control" name="username" required/>
        </div>
        <div class="form-group">
            <label for="password_id">Пароль</label>
            <input id="password_id" type="password" class="form-control" name="password" required/>
        </div>
        <button type="submit" class="btn btn-info">Войти</button>
    </form>
        
</@base.content>