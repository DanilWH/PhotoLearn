<#import "parts/base.ftlh" as base />

<@base.content title="${tutorialDto.title} | Photo Learn">

	<!-- The 'toast' image editor css files. -->
	<#if tutorialDto.imgEditorIsInjected>
		<#include "parts/toast_image_editor/css_data.ftlh" />
	</#if>
	

	<!-- The content of the page. -->
	<h1>${tutorialDto.title}</h1>
	
	<div>
		${tutorialDto.content?no_esc}
		<hr />
		<div align="right">by <i>${tutorialDto.user.getUsername()}</i></div>
	</div>
	
	
	<!-- The 'toast' editor js files. -->
	<#if tutorialDto.imgEditorIsInjected>
		<div id="tui-image-editor-container"></div>
		
        <#include "parts/toast_image_editor/js_scripts.ftlh" />
	</#if>

</@base.content>