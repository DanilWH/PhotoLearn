<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@base.content title="${tutorialDto.title} | Photo Learn">

	<!-- The 'toast' image editor css files. -->
	<#if tutorialDto.imgEditorIsInjected>
		<#include "parts/toast_image_editor/css_data.ftlh" />
	</#if>


	<@security.authorize access="hasRole('ROLE_TEACHER')">
		<div>
			<a href="/tutorial/${tutorialDto.id}/edit">Редактировать урок</a> |
			<a href="/tutorial/${tutorialDto.id}/delete">Удалить урок</a>
		</div>
	</@security.authorize>
	
	<!-- The content of the page. -->
	<h1>${tutorialDto.title}</h1>
	
	<div>
		${tutorialDto.content?no_esc}
		<hr />
		<div align="right">by <i>${tutorialDto.user.getUsername()}</i></div>
	</div>

	<!-- The "PhotoResult" form -->
	<form action="/tutorial/${tutorialDto.id}/photo-result/add" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />

		<div>
			<label for="description">Описание</label>
			<@spring.formTextarea "photoResultDto.description" />
			<@spring.showErrors "<br>" />
		</div>
		<div><input name="multipartFile" type="file" /></div>
		<div><button type="submit">Отправить</button></div>
	</form>
	
	
	<!-- The 'toast' editor js files. -->
	<#if tutorialDto.imgEditorIsInjected>
		<div id="tui-image-editor-container"></div>
		
        <#include "parts/toast_image_editor/js_scripts.ftlh" />
	</#if>

</@base.content>