<#import "parts/base.ftlh" as base />

<@base.content title="Уроки | Photo Learn">

	<h1>Уроки</h1>
	
	<ul>
		<#list tutorials as tutorial>
			<li><h2><a href="/tutorial/${tutorial.id}">${tutorial.title}</a></h2></li>
		</#list>
	</ul>

</@base.content>
