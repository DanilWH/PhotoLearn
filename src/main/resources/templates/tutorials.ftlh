<#import "parts/base.ftlh" as base />

<@base.content title="Уроки | Photo Learn">

	<h1>Уроки</h1>
	
	<#list tutorials as tutorial>
		<h3>${tutorial.title}</h3>
		<div>${tutorial.content?no_esc}</div>
		<hr />
	</#list>

</@base.content>
