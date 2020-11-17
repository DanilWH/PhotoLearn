<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />
<#import "spring.ftl" as spring />

<@base.content title="Новый урок | Photo Learn">

	<!-- load the TinyMCE editor -->
	<@common.tutorial_editor header="Новый урок" action="/tutorial/new" tutorialDto=tutorialDto />

</@base.content>