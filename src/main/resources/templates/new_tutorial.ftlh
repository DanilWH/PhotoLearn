<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />

<@base.content title="Новый урок | Photo Learn">

	<h1>Новый урок</h1>
	
	<form action="/tutorial/new" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div>
			<label for="title">Заголовок: </label>
			<@spring.formInput "tutorialDto.title" />
			<@spring.showErrors "<br>" />
		</div>
		<div>
			<label for="content">Содержимое: </label>
			<!-- <textarea rows="50"></textarea> -->
			<@spring.formTextarea "tutorialDto.content" />
			<@spring.showErrors "<br>" />
		</div>
		<div>
			<button type="submit">Добавить</button>
		</div>
	</form>

</@base.content>