<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />
<#import "spring.ftl" as spring />

<@base.content title="Новый урок | Photo Learn">

	<@base.header>Новый урок</@base.header>

	<!-- load the TinyMCE editor -->
	<@common.tutorial_editor action="/tutorial/new" tutorialDto=tutorialDto />

</@base.content>