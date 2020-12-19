<#import "parts/base.ftlh" as base />

<@base.content title="Удаление урока ${tutorialDto.title} | Photo Learn">

    <@base.header>Удаление</@base.header>

    <p class="lead">
        Вы действительно хотите удалить урок
        <a href="/tutorial/${tutorialDto.id}">${tutorialDto.title}</a>?
    </p>
    <form action="/tutorial/${tutorialDto.id}/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        
        <a href="/tutorial/${tutorialDto.id}" class="btn btn-info">Нет</a>
        <button type="submit" class="btn btn-danger">Да</button>
    </form>

</@base.content>