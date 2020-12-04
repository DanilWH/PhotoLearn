<#macro tutorial_editor header action tutorialDto titleValue="" contentValue="">

    <h1>${header}</h1>

    <form action="${action}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <label for="title">Заголовок: </label>
            <@spring.formInput "tutorialDto.title" />
            <@spring.showErrors "<br>" />
        </div>
        <div>
            <label for="content">Содержимое: </label>
            <@spring.formTextarea "tutorialDto.content" />
            <@spring.showErrors "<br>" />
        </div>
        <div>
            <label for="multipartFile">Выберете фотографию для редактирования: </label>
            <input id="multipartFile" name="multipartFile" type="file" />
        </div>
        <div>
            <button type="submit">Добавить</button>
        </div>
    </form>

    <!-- Load the TinyMCE text editor. -->
    <#include "tinymce_load.ftlh" />

</#macro>