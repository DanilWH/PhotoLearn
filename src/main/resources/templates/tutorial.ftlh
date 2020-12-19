<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />
<#import "parts/toast_image_editor/js_scripts.ftlh" as js_scripts />
<#include "parts/user_security.ftlh" />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@base.content title="${tutorialDto.title} | Photo Learn">

	<!-- The 'toast' image editor css files. -->
	<#if tutorialDto.imgName??>
		<#include "parts/toast_image_editor/css_data.ftlh" />
	</#if>

	<!-- The content of the page. -->
	<@base.header>
		<div class="d-flex justify-content-between">
			${tutorialDto.title}
			<@security.authorize access="hasRole('ROLE_TEACHER')">
				<div class="btn-group btn-group-sm mt-4" role="group" aria-label="edit-delete" style="height: 100%;">
					<a href="/tutorial/${tutorialDto.id}/edit" class="btn btn-info">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen-fill" viewBox="0 0 16 16">
							<path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
						</svg>
						Редактировать
					</a>
					<a href="/tutorial/${tutorialDto.id}/delete" class="btn btn-danger">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash2-fill" viewBox="0 0 16 16">
							<path d="M2.037 3.225l1.684 10.104A2 2 0 0 0 5.694 15h4.612a2 2 0 0 0 1.973-1.671l1.684-10.104C13.627 4.224 11.085 5 8 5c-3.086 0-5.627-.776-5.963-1.775z"/>
							<path fill-rule="evenodd" d="M12.9 3c-.18-.14-.497-.307-.974-.466C10.967 2.214 9.58 2 8 2s-2.968.215-3.926.534c-.477.16-.795.327-.975.466.18.14.498.307.975.466C5.032 3.786 6.42 4 8 4s2.967-.215 3.926-.534c.477-.16.795-.327.975-.466zM8 5c3.314 0 6-.895 6-2s-2.686-2-6-2-6 .895-6 2 2.686 2 6 2z"/>
						</svg>
						Удалить
					</a>
				</div>
			</@security.authorize>
		</div>
	</@base.header>

	<#if _user??>
		<#if _user.isAdmin() || _user.isTeacher()>
			<div>
				<a href="/tutorial/${tutorialDto.id}/photo-results" class="btn btn-outline-info mb-3">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
						<path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z"/>
						<circle cx="3.5" cy="5.5" r=".5"/>
						<circle cx="3.5" cy="8" r=".5"/>
						<circle cx="3.5" cy="10.5" r=".5"/>
					</svg>
					Просмотреть результаты студентов
				</a>
			</div>
		</#if>
	</#if>

	<div>
		${tutorialDto.content?no_esc}
		<hr />
		<div align="right"><i>${tutorialDto.user.getUsername()}</i></div>
	</div>

	<!-- "TOAST UI Image Editor" JS. -->
	<#if (tutorialDto.imgName)??>
		<div id="tui-image-editor-container" class="my-5"></div>

		<@js_scripts.toastInit imgName="${tutorialDto.imgName}" />
	</#if>

	<!-- The "PhotoResult" form -->
	<#if _user??>
		<#if _user.isStudent()>
			<#if photoResultDto.id??>
				<!-- Show the description and the photo if the student has already uploaded a photo result -->

				<div align="center">
					<div class="card" style="max-width: 540px;">
						<div class="row no-gutters">
							<div class="col-md-4">
								<img src="/img/${photoResultDto.filename}" class="rounded card-img" alt="${photoResultDto.filename}">
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<p class="card-text">${photoResultDto.description}</p>
									<hr />

									<!-- Button trigger modal -->
									<button class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#confirmTutorialDeletion">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash2-fill" viewBox="0 0 16 16">
											<path d="M2.037 3.225l1.684 10.104A2 2 0 0 0 5.694 15h4.612a2 2 0 0 0 1.973-1.671l1.684-10.104C13.627 4.224 11.085 5 8 5c-3.086 0-5.627-.776-5.963-1.775z"/>
											<path fill-rule="evenodd" d="M12.9 3c-.18-.14-.497-.307-.974-.466C10.967 2.214 9.58 2 8 2s-2.968.215-3.926.534c-.477.16-.795.327-.975.466.18.14.498.307.975.466C5.032 3.786 6.42 4 8 4s2.967-.215 3.926-.534c.477-.16.795-.327.975-.466zM8 5c3.314 0 6-.895 6-2s-2.686-2-6-2-6 .895-6 2 2.686 2 6 2z"/>
										</svg>
										Удалить ответ.
									</button>

									<!-- Modal -->
									<div class="modal fade" id="confirmTutorialDeletion" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="confirmTutorialDeletionLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="confirmTutorialDeletionLabel">Удалить ваш результат?</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<p>Вы уверены что хотите удалить ваш ответ к уроку "${tutorialDto.title}"</p>
													<img src="/img/${photoResultDto.filename}" class="rounded card-img" alt="${photoResultDto.filename}">
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-dismiss="modal">Не удалять</button>
													<a href="/tutorial/${tutorialDto.id}/photo-result/${photoResultDto.id}/delete" class="btn btn-info">Удалить</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			<#else>
				<!-- Create a new form if the student hasn't uploaded a photo result. -->
				<form action="/tutorial/${tutorialDto.id}/photo-result/add" method="post" enctype="multipart/form-data">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />

					<!-- The description form. -->
					<div class="form-group">
						<label for="description">Описание</label>
						<@spring.bind "photoResultDto.description" />
						<@spring.formTextarea "photoResultDto.description" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='descriptionValidation'" />
						<div id="descriptionValidation" class="invalid-feedback">
							<@spring.showErrors "<br>" />
						</div>
					</div>

					<!-- The "File uploading" form. -->
					<div class="custom-file">
						<input id="multipartFile"
							   name="multipartFile"
							   class="custom-file-input"
							   type="file"
							   accept="image/*"
							   onchange="return imagePreview();"
						/>
						<label id="fileLabel" class="custom-file-label" for="multipartFile" data-browse="Найти">Выбрать файл</label>
					</div>

					<!-- Show the image on uploading. -->
					<div class="form-group">
						<div id="imagePreview" class=""></div>
						<script src="/js/image_preview.js"></script>
					</div>

					<!-- The "send" button. -->
					<button id="submitBtn" type="submit" class="btn btn-info mb-3" disabled="true">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cloud-upload-fill" viewBox="0 0 16 16">
							<path fill-rule="evenodd" d="M8 0a5.53 5.53 0 0 0-3.594 1.342c-.766.66-1.321 1.52-1.464 2.383C1.266 4.095 0 5.555 0 7.318 0 9.366 1.708 11 3.781 11H7.5V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11h4.188C14.502 11 16 9.57 16 7.773c0-1.636-1.242-2.969-2.834-3.194C12.923 1.999 10.69 0 8 0zm-.5 14.5V11h1v3.5a.5.5 0 0 1-1 0z"/>
						</svg>
						Отправить
					</button>
				</form>
			</#if>
		</#if>
	</#if>

</@base.content>