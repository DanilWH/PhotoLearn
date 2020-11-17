<#macro tutorial_editor header action tutorialDto titleValue="" contentValue="" imgEditorIsInjectedValue=false>

    <h1>${header}</h1>

    <form action="${action}" method="post">
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
            <label for="imgEditorIsInjected">Добавить встроенный редактор изображения?</label>
            <input id="imgEditorIsInjected" type="checkbox"
                   name="imgEditorIsInjected" value="true" ${imgEditorIsInjectedValue?string("checked", "")} />
        </div>
        <div>
            <button type="submit">Добавить</button>
        </div>
    </form>

    <!-- Load the TinyMCE text editor. -->
    <#include "tinymce_load.ftlh" />

</#macro>