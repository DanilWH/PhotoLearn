<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />

<@base.content title="Новый урок | Photo Learn">

	<script>
		function show() {
			document.getElementById("content").value = tinymce.get("content").getContent({ format: "text" });
			alert(document.getElementById("content").value)
		}
	</script>

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
			<@spring.formTextarea "tutorialDto.content" />
			<@spring.showErrors "<br>" />
		</div>
		<div>
			<button type="submit">Добавить</button>
		</div>
	</form>

</@base.content>