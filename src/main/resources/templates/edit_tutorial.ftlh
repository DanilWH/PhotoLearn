<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />
<#import "spring.ftl" as spring />

<@base.content title="Редактировать урок | Photo Learn">

    <@base.header>Редактировать урок</@base.header>

    <!-- load the TinyMCE editor -->
    <@common.tutorial_editor
        action="/tutorial/${tutorialDto.id}/edit"
        tutorialDto=tutorialDto
        titleValue="${tutorialDto.title}"
        contentValue="${tutorialDto.content}"
    />

</@base.content>