<#import "parts/base.ftlh" as base />

<@base.content title="Уроки | Photo Learn">

	<@base.header>Уроки</@base.header>

	<div class="card-columns" id="tutorials-list">
		<#list tutorials as tutorial>
			<div class="card" data-id="${tutorial.id}">
				<#if tutorial.imgName??>
					<img src="/img/${tutorial.imgName}" class="card-img-top" alt="${tutorial.imgName}" />
				</#if>
				<div class="card-body">
					<h5 class="card-title">${tutorial.title}</h5>
					<p class="card-text">
						<div><small class="text-muted">Создан: ${tutorial.getFormattedCreatedOn(tutorial.createdOn)}</small></div>
						<div><small class="text-muted">Обнавлён: ${tutorial.getFormattedCreatedOn(tutorial.updatedOn)}</small></div>
					</p>
					<a href="/tutorial/${tutorial.id}" class="btn btn-info">Читать</a>
				</div>
			</div>
		<#else>
			<h3><b>Ничего не найдено.</b></h3>
		</#list>
	</div>

</@base.content>
