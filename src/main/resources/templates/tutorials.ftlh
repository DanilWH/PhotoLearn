<#import "parts/base.ftlh" as base />
<#import "parts/pager.ftlh" as pager />

<@base.content title="Уроки | Photo Learn">

	<@base.header>Уроки</@base.header>

	<div class="card-columns" id="tutorials-list">
		<#list page.content as tutorial>
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
					<div class="d-flex justify-content-between">
						<a href="/tutorial/${tutorial.id}" class="btn btn-info">Читать</a>
						<small class="text-muted">Нравится: ${tutorial.likes}
							<#if tutorial.meLiked>
								<i class="fas fa-caret-square-up"></i>
							<#else>
								<i class="far fa-caret-square-up"></i>
							</#if>
						</small>
					</div>
				</div>
			</div>
		<#else>
			<h3><b>Ничего не найдено.</b></h3>
		</#list>
	</div>

	<@pager.pager url filter page />

</@base.content>
