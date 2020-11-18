<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />
<#import "spring.ftl" as spring />

<@base.content title="Редактировать урок | Photo Learn">

    <!-- load the TinyMCE editor -->
    <@common.tutorial_editor
        header="Редактировать урок"
        action="/tutorial/${tutorialDto.id}/edit"
        tutorialDto=tutorialDto
        titleValue="${tutorialDto.title}"
        contentValue="${tutorialDto.content}"
        imgEditorIsInjectedValue=tutorialDto.imgEditorIsInjected
    />

</@base.content>