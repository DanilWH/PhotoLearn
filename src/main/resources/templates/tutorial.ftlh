<#import "parts/base.ftlh" as base />

<@base.content title="${tutorialDto.title} | Photo Learn">

	<h1>${tutorialDto.title}</h1>
	
	<div>
		${tutorialDto.content?no_esc}
		<hr />
		<div align="right">by <i>${tutorialDto.user.getUsername()}</i></div>
	</div>

</@base.content>