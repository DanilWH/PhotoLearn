<#import "parts/base.ftlh" as base />

<@base.content title="Удаление урока ${tutorialDto.title} | Photo Learn">

    <h1>Удаление</h1>
    <p>
        Вы действительно хотите удалить урок
        <a href="/tutorial/${tutorialDto.id}">${tutorialDto.title}</a>?
    </p>
    <form action="/tutorial/${tutorialDto.id}/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Да</button>
    </form>
    <a href="/tutorial/${tutorialDto.id}">Нет</a>

</@base.content>