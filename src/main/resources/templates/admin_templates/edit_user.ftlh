<#import "../spring.ftl" as spring />
<#import "../parts/base.ftlh" as base>

<@base.content title="Изменения пользователя ${userDto.username} | Photo Learn">

    <h1>Изменить пользователя.</h1>

    <form action="/admin/user/${userDto.id}/edit" method="post">
        <!-- hidden fields -->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <fieldset>
            <legend>Информация пользователя</legend>

            <div>ID пользователя: ${userDto.id}</div>
            <div>Имя пользователя: ${userDto.username}</div>
        </fieldset>
        <fieldset>
            <legend>Изменить роли пользователя</legend>

            <#list roles as role>
                <div>
                    <label for="${role}">${role}: </label>
                    <input id="${role}" type="radio" name="userRoles" value="${role}" ${(userDto.userRoles)?seq_contains(role)?string("checked", "")} />
                </div>
            </#list>
        </fieldset>

        <button type="submit">Сохранить</button>
    </form>

</@base.content>