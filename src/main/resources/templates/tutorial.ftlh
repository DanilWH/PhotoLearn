<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />
<#include "parts/user_security.ftlh" />
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
	<#if _user.isTeacher()>
		<a href="/tutorial/${tutorialDto.id}/photo-results">Просмотреть результаты студентов</a>
	<#elseif _user.isStudent()>
		<#if photoResultDto.id??>
			<!-- Show the description and the photo if the student has already uploaded a photo result -->
			<p>${photoResultDto.description}</p>
			<img src="/img/${photoResultDto.filename}" />
		<#else>
			<!-- Create a new form if the student hasn't uploaded a photo result. -->
			<form action="/tutorial/${tutorialDto.id}/photo-result/add" method="post" enctype="multipart/form-data">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />

				<div>
					<label for="description">Описание</label>
					<@spring.formTextarea "photoResultDto.description" />
					<@spring.showErrors "<br>" />
				</div>
				<div>
					<input id="multipartFile" name="multipartFile" type="file"
						   accept="image/*"
						   onchange="return fileValidation();"
					/>
					<div id="imagePreview"></div>
					<script>
						function fileValidation() {
							var fileInput =  document.getElementById('multipartFile');
							var filePath = fileInput.value;

							// Image preview
							if (fileInput.files && fileInput.files[0]) {
								var reader = new FileReader();
								reader.onload = function(e) {
									document.getElementById('imagePreview').innerHTML =
										'<img src="' + e.target.result + '"/>';
								};

								reader.readAsDataURL(fileInput.files[0]);
								document.getElementById('submitBtn').disabled = false;
							}
							else {
								document.getElementById('submitBtn').disabled = true;
							}
						}
					</script>
				</div>
				<div><button id="submitBtn" type="submit" disabled="true">Отправить</button></div>
			</form>
		</#if>
	</#if>
	
	
	<!-- The 'toast' editor js files. -->
	<#if tutorialDto.imgEditorIsInjected>
		<div id="tui-image-editor-container"></div>
		
        <#include "parts/toast_image_editor/js_scripts.ftlh" />
	</#if>

</@base.content>